package multithread.productandconsumer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/27 9:13 PM
 * @description :
 *
 */
public interface WareHouse {
    void product();

    void consume();
}

/**
 * 仓库类，使用ReentrantLock，condition实现
 */
class WareHouse1 implements WareHouse {
    private static final int MAX_SIZE = 10;
    private LinkedList<Product> list = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition productCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    public int getSize() {
        return list.size();
    }

    @Override
    public void product() {
        lock.lock();
        try {
            while (list.size() >= MAX_SIZE) {
                try {
                    productCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Product product = new Product(UUID.randomUUID());
            list.add(product);
            System.out.println("生产者" + Thread.currentThread().getName() + "生产了产品" + product.getCode());
            consumerCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void consume() {
        lock.lock();
        try {
            while (list.size() <= 0) {
                try {
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Product product = list.remove();
            System.out.println("消费者" + Thread.currentThread().getName() + "消费了产品" + product.getCode());
            productCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

// --------------------------------------------------------------------------------------------

/**
 * 使用Synchronized实现
 */
class WareHouse2 implements WareHouse {
    private static final int MAX_SIZE = 10;
    private LinkedList<Product> list = new LinkedList<>();

    @Override
    public void product() {
        synchronized (list) {
            while (list.size() >= MAX_SIZE) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Product product = new Product(UUID.randomUUID());
            list.add(product);
            System.out.println("生产者" + Thread.currentThread().getName() + "生产了产品" + product.getCode());
            list.notifyAll();
        }
    }

    @Override
    public void consume() {
        synchronized (list) {
            while (list.size() <= 0) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Product product = list.remove();
            System.out.println("消费者" + Thread.currentThread().getName() + "消费了产品" + product.getCode());
            list.notifyAll();
        }
    }
}

// --------------------------------------------------------------------------------------------

class WareHouse3 implements WareHouse {
    private static final int MAX_SIZE = 10;
    BlockingQueue<Product> list = new LinkedBlockingQueue<>(10);

    @Override
    public void product() {
        if (list.size() >= MAX_SIZE) {
            System.out.println("产品列表已满，不再生产！");
        } else {
            Product product = new Product(UUID.randomUUID());
            System.out.println("生产者" + Thread.currentThread().getName() + "生产了产品" + product.getCode());
            try {
                list.put(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void consume() {
        if (list.size() <= 0) {
            System.out.println("产品列表为空，等待产品！");
        } else {
            try {
                Product product = list.take();
                System.out.println("消费者" + Thread.currentThread().getName() + "消费了产品" + product.getCode());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


// --------------------------------------------------------------------------------------------
/**
 * 实现4，Sempahore实现
 * https://www.cnblogs.com/leodaxin/p/7703721.html
 */
class WareHouse4 implements WareHouse {
    private static final int MAX_SIZE = 10;
    private LinkedList<Product> list = new LinkedList<>();
    // 产品池空位的个数，生产者使用
    Semaphore empty;
    // 产品池产品个数，消费者使用
    Semaphore full;
    // 信号量，控制有多少个线程可以同时进入产品池
    Semaphore mutex;

    public WareHouse4() {
        // 初始为产品池最大数量
        empty = new Semaphore(MAX_SIZE);
        full = new Semaphore(0);
        // 同时允许两个线程进入产品池
        mutex = new Semaphore(2);
    }

    @Override
    public void product() {
        try {
            // 请求锁，锁status - 1
            empty.acquire();
            mutex.acquire();
            Product product = new Product(UUID.randomUUID());
            System.out.println("生产者" + Thread.currentThread().getName() + "生产了产品" + product.getCode());
            list.add(product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // release方法使semaphore的status+1
            mutex.release();
            // 放入一个产品
            full.release();
        }
    }

    @Override
    public void consume() {
        try {
            // 请求锁，锁status - 1
            full.acquire();
            mutex.acquire();
            Product product = list.remove();
            System.out.println("消费者" + Thread.currentThread().getName() + "消费了产品" + product.getCode());
            list.add(product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // release方法使semaphore的status+1
            mutex.release();
            // 放入一个产品
            empty.release();
        }
    }
}
