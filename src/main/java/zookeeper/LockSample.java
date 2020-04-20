package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/1 4:40 PM
 * @description : 分布式锁实现
 * 原理：
 * https://blog.csdn.net/liyiming2017/article/details/83786331
 */
public class LockSample {

    private ZooKeeper zkClient;
    // 锁根路径
    private static final String LOCK_ROOT_PATH = "/LOCKS";
    // 锁前缀
    private static final String LOCK_PREFIX_PATH = "Lock_";
    private String lockPath;
    /**
     * 监听前一个节点的watcher
     */
    private PreLockWatcher watcher = new PreLockWatcher();

    public LockSample() throws IOException {
        this.zkClient = new ZooKeeper("localhost:2181", 1000, watchedEvent -> {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.Disconnected) {
                System.out.println(Thread.currentThread().getName() + "连接断开");
            }
        });
    }

    /**
     * 加锁
     *
     * @throws Exception
     */
    public void acquire() throws Exception {
        // 创建锁的临时节点
        createLock();
        // 尝试获取锁
        attemptLock();
    }

    /**
     * 创建锁的临时节点路径
     *
     * @throws Exception
     */
    public void createLock() throws Exception {
        // 如果根节点不存在，则创建
        Stat stat = zkClient.exists(LOCK_ROOT_PATH, false);
        if (stat == null) {
            zkClient.create(LOCK_ROOT_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        // 创建EPHEMERAL_SEQUENTIAL的节点
        String lockPath = zkClient.create(LOCK_ROOT_PATH + "/" + LOCK_PREFIX_PATH,
                Thread.currentThread().getName().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(Thread.currentThread().getName() + " 锁创建：" + lockPath);
        this.lockPath = lockPath;
    }

    /**
     * 尝试获取锁
     *
     * @throws Exception
     */
    public void attemptLock() throws Exception {
        // 获取lock节点下的所有子节点
        List<String> lockPaths = zkClient.getChildren(LOCK_ROOT_PATH, false);
        Collections.sort(lockPaths);
        // 找到当前锁位于的位置
        int index = lockPaths.indexOf(lockPath.substring(LOCK_ROOT_PATH.length() + 1));
        // 如果当前锁时最小的节点，则获取锁
        if (index == 0) {
            System.out.println(Thread.currentThread().getName() + "获得锁，lockPath:" + lockPath);
        } else {
            String preLockPath = lockPaths.get(index - 1);
            Stat stat = zkClient.exists(LOCK_ROOT_PATH + "/" + preLockPath, watcher);
            if (stat == null) {
                // 前一个节点不存在，比如在这期间执行完毕，或者掉线等，重新获取锁
                attemptLock();
            } else {
                System.out.println(Thread.currentThread().getName() + "等待前锁释放，preLockPath:" + preLockPath);
                // 等待watcher唤醒
                synchronized (watcher) {
                    watcher.wait();
                }
                // 重新尝试获取锁
                attemptLock();
            }
        }
    }

    /**
     * 释放锁
     */
    public void release() throws Exception{
        zkClient.delete(lockPath, -1);
        zkClient.close();
        System.out.println("释放锁：" + lockPath);
    }

}

/**
 * 监听前面路径锁释放释放
 */
class PreLockWatcher implements Watcher {

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.getPath() + "：前锁释放");
        synchronized (this) {
            // 调用notifyAll方法必须先获得锁
            notifyAll();
        }
    }
}
