package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/12 11:26 PM
 * @description :
 */
public class PrintLetter {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static boolean flag = true;

    public static class LowerLetterThread extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 26; i++) {
                    while (!flag) {
                        conditionA.await();
                    }
                    System.out.print((char) ('a' + i));
                    flag = false;
                    conditionB.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class UpperLetterThread extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 26; i++) {
                    while (flag) {
                        conditionB.await();
                    }
                    System.out.print((char) ('A' + i));
                    flag = true;
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
        LowerLetterThread lowerLetterThread = new LowerLetterThread();
        UpperLetterThread upperLetterThread = new UpperLetterThread();
        lowerLetterThread.start();
        upperLetterThread.start();
    }

}
