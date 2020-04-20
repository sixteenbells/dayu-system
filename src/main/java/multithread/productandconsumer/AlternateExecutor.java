package multithread.productandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/12 11:13 PM
 * @description : ABC线程交替执行
 */
public class AlternateExecutor {
    private static ReentrantLock lock = new ReentrantLock();
    private static int count = 0;
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static class ThreadA extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 9; i++) {
                    while (count % 3 != 0) {
                        conditionA.await();
                    }
                    System.out.println("A");
                    count++;
                    conditionB.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadB extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 1) {
                        conditionB.await();
                    }
                    System.out.println("B");
                    count++;
                    conditionC.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadC extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 2) {
                        conditionC.await();
                    }
                    System.out.println("C");
                    count++;
                    conditionA.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadC threadC = new ThreadC();
        threadA.start();
        threadB.start();
        threadC.start();
    }

}
