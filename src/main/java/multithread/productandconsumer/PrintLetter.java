package multithread.productandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/12 7:16 PM
 * @description : 线程交替打印
 */
public class PrintLetter {

    public static void main(String[] args) {
        final LetterPrint print = new LetterPrint();
        long startTime = System.currentTimeMillis();
        ThreadA threadA = new ThreadA(print);
        ThreadB threadB = new ThreadB(print);
        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n小写线程耗时：" + threadA.getCostTime());
        System.out.println("大写线程耗时：" + threadB.getCostTime());
        System.out.println("总耗时：" + (System.currentTimeMillis() - startTime));
    }

    static class ThreadA extends Thread {
        long costTime = 0;
        LetterPrint print;

        public ThreadA(LetterPrint print) {
            this.print = print;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (print.lowerFlag) {
                print.printLetterUpper();
            }
            costTime = System.currentTimeMillis() - startTime;
        }

        public long getCostTime() {
            return costTime;
        }
    }

    static class ThreadB extends Thread {
        long costTime = 0;
        LetterPrint print;

        public ThreadB(LetterPrint print) {
            this.print = print;
        }

        public long getCostTime() {
            return costTime;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (print.upperFlag) {
                print.printLetterLower();
            }
            costTime = System.currentTimeMillis() - startTime;
        }
    }

    static class LetterPrint {
        public boolean lowerFlag = true;
        public boolean upperFlag = true;
        // 小写
        int letterLower = 97;
        // 大写
        int letterUpper = 65;
        boolean flag = true;
        Lock lock = new ReentrantLock();
        Condition condLetter = lock.newCondition();
        Condition condNum = lock.newCondition();

        public void printLetterUpper() {
            if (letterUpper >= 91) {
                lowerFlag = false;
                return;
            }
            lock.lock();
            try {
                if (flag) {
                    condLetter.await();
                }
                System.out.print((char) letterUpper);
                Thread.sleep(50);
                letterUpper++;
                flag = true;
                condNum.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void printLetterLower() {
            if (letterLower >= 123) {
                upperFlag = false;
                return;
            }
            lock.lock();
            try {
                if (!flag) {
                    condNum.await();
                }
                System.out.print((char) letterLower);
                Thread.sleep(50);
                letterLower++;
                flag = false;
                condLetter.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
