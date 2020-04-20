package zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.Random;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/1 5:17 PM
 * @description : 测试分布式锁
 */
public class LockTest extends Thread {

    private void sell() {
        System.out.println(Thread.currentThread().getName() + "售票开始");
        int sleepMillis = (int)(Math.random() * 10000);
        try {
            System.out.println(Thread.currentThread().getName() + "售票");
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "售票结束");
    }

    public void sellTicketWithLock() throws Exception {
        LockSample lockSample = new LockSample();
        lockSample.acquire();
        sell();
        lockSample.release();
    }

    @Override
    public void run() {
        for (int i = 0;i < 10; i++) {
            try {
                sellTicketWithLock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i < 5; i++) {
            LockTest lockTest1 = new LockTest();
            lockTest1.setName("lockThread1");
            lockTest1.start();
        }
    }
}
