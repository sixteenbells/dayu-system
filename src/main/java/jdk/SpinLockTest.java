package jdk;

import java.security.acl.Owner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/10 4:14 PM
 * @description : 自旋锁
 * https://coderbee.net/index.php/concurrent/20131115/577/comment-page-1
 */
public class SpinLockTest {

    public static void main(String[] args) {
//        Runnable runnable = new MyThread(new SpinLock());
        Runnable runnable = new MyThread2(new TicketLock());
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable, String.valueOf(i));
            thread.start();
        }
    }

    public static class MyThread implements Runnable {

        private SpinLock spinLock;

        public MyThread(SpinLock spinLock) {
            this.spinLock = spinLock;
        }

        @Override
        public void run() {
            try {
                spinLock.lock();
                Thread.sleep(1000);
                System.out.println("线程:" + Thread.currentThread().getName() + "执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        }
    }

    public static class MyThread2 implements Runnable {

        private TicketLock ticketLock;

        public MyThread2(TicketLock ticketLock) {
            this.ticketLock = ticketLock;
        }

        @Override
        public void run() {
            int tick = ticketLock.lock();
            try {
                Thread.sleep(1000);
                System.out.println("线程:" + Thread.currentThread().getName() + "执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                ticketLock.unlock(tick);
            }
        }
    }


}

interface MyLock {
    void lock();

    void unlock();
}

/**
 * 普通自旋锁
 * 缺点：
 * 1.非公平竞争
 * 2.各cpu之间同步缓存开销大
 */
class SpinLock implements MyLock {
    private AtomicReference<Thread> owner = new AtomicReference<>();

    @Override
    public void lock() {
        Thread thread = Thread.currentThread();
        // 如果锁空闲，则设置当前线程为锁拥有者
        while (!owner.compareAndSet(null, thread)) {
        }
        System.out.println("线程:" + thread.getName() + "拥有锁");
    }

    @Override
    public void unlock() {
        Thread thread = Thread.currentThread();
        System.out.println("线程:" + thread.getName() + "释放锁");
        // 只有锁拥有才能释放锁
        owner.compareAndSet(thread, null);
    }
}

/**
 * Ticket lock，解决公平性问题
 */
class TicketLock {
    /**
     * 正在服务的号码
     */
    private AtomicInteger serviceNum = new AtomicInteger();
    /**
     * 排队号
     */
    private AtomicInteger ticketNum = new AtomicInteger();

    public int lock() {
        Thread thread = Thread.currentThread();
        // 获取排队号
        int myTicket = ticketNum.getAndIncrement();
        while (serviceNum.get() != myTicket) {
        }
        System.out.println("线程:" + thread.getName() + "拥有锁");
        return myTicket;
    }

    public void unlock(int myTicket) {
        Thread thread = Thread.currentThread();
        int next = myTicket + 1;
        serviceNum.compareAndSet(myTicket, next);
        System.out.println("线程:" + thread.getName() + "释放锁");
    }

}

/**
 * CLH锁
 */
class CLHLock {
    public static class CLHNode {
        volatile boolean isLocked = true;
    }

    /**
     * 当前锁最后一个节点
     */
    private CLHNode tail;
    /**
     * 更新锁最后一个节点
     */
    public static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    /**
     * 加锁步骤：
     * 1.将当前节点加入队尾，并获取前驱节点
     * 2.如果前驱节点为空，则直接持有锁
     * 2.否则在前驱节点的状态上自旋
     *
     * @param currentNode
     */
    public void lock(CLHNode currentNode) {
        CLHNode preNode = UPDATER.getAndSet(this, currentNode);
        if (preNode != null) {
            // 在前驱节点上自旋
            while (preNode.isLocked) {

            }
        }
    }

    /**
     * 释放锁：
     * 1.判断当前节点是否是最后一个节点，是的话设置队列为空
     * 2.否则改变自己的状态，让下一个节点结束自旋
     *
     * @param currentNode
     */
    public void unlock(CLHNode currentNode) {
        // 如果当前节点为最后一个节点，则释放对当前节点的引用
        if (!UPDATER.compareAndSet(this, currentNode, null)) {
            // 不成功说明后面还有节点，则改变状态，然后后面节点结束自旋
            currentNode.isLocked = false;
        }
    }
}

/**
 * MCS锁
 */
class MCSLock {
    static class MCSNode {
        /**
         * 后驱节点
         */
        volatile MCSNode next;
        /**
         * 是否等待锁
         */
        volatile boolean isBlock;
    }

    /**
     * 当前锁最后一个节点
     */
    public MCSNode tail;

    /**
     * 更新最后一个节点
     */
    public static final AtomicReferenceFieldUpdater<MCSLock, MCSNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(MCSLock.class, MCSNode.class, "tail");

    /**
     * 加锁：
     * 1.将当前节点插入对尾，并获取前驱节点
     * 2.如果前驱节点为空，直接获取锁
     * 3.否则，在自己本地状态上自旋
     *
     * @param currentNode
     */
    public void lock(MCSNode currentNode) {
        MCSNode preNode = UPDATER.getAndSet(this, currentNode);
        // step 1
        if (preNode != null) {
            // step 2
            preNode.next = currentNode;
            // 在自己本地变量自旋
            while (currentNode.isBlock) {

            }
        } else {
            // 只有一个线程，当前线程为最后一个线程，则直接获取锁
            currentNode.isBlock = false;
        }
    }

    public void unlock(MCSNode currentNode) {
        // 锁拥有者才能释放锁
        if (currentNode.isBlock) {
            return;
        }

        // 如果当前节点为最后一个节点
        if (currentNode.next == null) {
            // 尝试设置tail为null
            if (UPDATER.compareAndSet(this, currentNode, null)) {
                return;
            } else {
                // 有节点突然插入到当前节点后面，可能不知道是谁，所以一直等到
                // 之所以不知道是谁，有可能是后面节点刚执行完step1，还没执行步骤2
                while (currentNode.next == null) {

                }
            }
        }

        // 下一个节点持有锁
        currentNode.next.isBlock = false;
        // for gc
        currentNode.next = null;
    }

}

/**
 * CLH锁和MCS锁区别
 *      1.CLH在前驱节点的状态上自旋，MCS在当前节点的状态上自旋
 *      2.都是链式实现，但是CLH是隐式的，MCS是实际存在的
 *      3.释放锁时，CLH只改变自身状态，MCS改变后期节点状态
 */

