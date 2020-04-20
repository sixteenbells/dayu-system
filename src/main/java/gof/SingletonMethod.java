package gof;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/7 1:50 PM
 * @description : 单例模式
 */
public class SingletonMethod {
    public static void main(String[] args) {

    }
}

/**
 * 懒汉模式
 */
class Singleton_1 {
    private static Singleton_1 instance;

    private Singleton_1() {}

    public static Singleton_1 getInstance() {
        synchronized (Singleton_1.class) {
            if (instance == null) {
                instance = new Singleton_1();
            }
        }
        return instance;
    }
}

/**
 * 双校验，减少加锁
 */
class Singleton_2 {
    private static Singleton_2 instance;

    private Singleton_2() {}

    public static Singleton_2 getInstance() {
        if (instance == null) {
            synchronized (Singleton_2.class) {
                if (instance == null) {
                    instance = new Singleton_2();
                }
            }
        }
        return instance;
    }
}

/**
 * 饿汉模式
 */
class Singleton_3 {
    private static Singleton_3 instance = new Singleton_3();

    private Singleton_3() {
    }

    public Object getInstance() {
        return instance;
    }
}

/**
 * 静态内部类
 */
class Singleton_4 {

    private static class InnerClass {
        public static Singleton_4 instance = new Singleton_4();
    }

    private Singleton_4() {
    }

    public Object getInstance() {
        return InnerClass.instance;
    }
}

/**
 * 枚举，在使用枚举类时会自动调用构造方法
 */
class Singleton_5 {

    enum MyEnumHandler {
        INSTANCE;
        private Singleton_5 instance;

        MyEnumHandler() {
            instance = new Singleton_5();
        }

        public Singleton_5 getInstance() {
            return instance;
        }
    }

    private Singleton_5() {
    }

    public Object getSingle() {
        return MyEnumHandler.INSTANCE.getInstance();
    }
}
