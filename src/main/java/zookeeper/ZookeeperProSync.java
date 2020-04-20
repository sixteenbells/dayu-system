package zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/1 2:19 PM
 * @description : 分布式配置中心demo（原生zookeeper客户端）
 * https://blog.csdn.net/java_66666/article/details/81015302
 */
public class ZookeeperProSync implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception{
        // zookerrper配置数据存放路径
        String path = "/zkPro";
        // 连接zk，并注册一个监听器
        zk = new ZooKeeper("127.0.0.1", 2182, new ZookeeperProSync());
        // 等待zookeeper连接成功的通知
        countDownLatch.await();
        System.out.println(new String(zk.getData(path, true, stat)));
        Thread.sleep(60 * 1000);
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            } else if (Event.EventType.NodeDataChanged.equals(watchedEvent.getType())) {
                // zk目录节点数据变化通知事件
                try {
                    // watch触发后失效，需要重新设置
                    System.out.println("配置已修改，新值为：" + new String(zk.getData(watchedEvent.getPath(), true, stat)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
