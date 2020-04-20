import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/23 6:24 PM
 * @description :
 */
public class App {

    public static class MyThread extends Thread {
        public static ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            method1(this.getName());
        }

        public static void method1(String name) {
            try {
                lock.lock();
                System.out.println(name + " do method1");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public synchronized static void method2(String name) {
            try {
                System.out.println(name + " do method2");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        MyThread myThread1 = new MyThread();
//        myThread1.setName("a");
//
//        MyThread myThread2 = new MyThread();
//        myThread2.setName("b");
//
//        myThread1.start();
//        myThread2.start();
//        Thread.sleep(500);
//        System.out.println("b 线程的状态时：" + myThread2.getState());
        System.out.println('a' + 0);
        System.out.println('z' + 0);
        System.out.println('A' + 0);
        System.out.println('Z' + 0);
    }


}
