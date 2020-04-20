package multithread.productandconsumer;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/27 12:08 PM
 * @description : 使用reentrantLock、condition实现
 * https://www.cnblogs.com/fengfuwanliu/p/10147953.html
 */
public class UseLock {
    public static void main(String[] args) {
        WareHouse wareHouse = new WareHouse4();
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer(wareHouse);
            producer.start();
            Consumer consumer = new Consumer(wareHouse);
            consumer.start();
        }
    }
}

/**
 * 生产者线程
 */
class Producer extends Thread {
    private WareHouse wareHouse;

    public Producer(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
                wareHouse.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wareHouse.product();
        }
    }
}

/**
 * 消费者线程
 */
class Consumer extends Thread {
    private WareHouse wareHouse;

    public Consumer(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wareHouse.consume();
        }
    }
}
