package gof;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/6 9:28 PM
 * @description : 类适配器
 * http://c.biancheng.net/view/1361.html
 * 分为类适配器、对象适配器、接口适配器
 * 重要3要素：
 * 目标（Target）：当前系统所期待的接口，它是接口或抽象类
 * 适配者（Adaptee）：系统中已存在的就接口，需要被适配成新的接口
 * 适配器（Adapter）：将适配器转换成目标接口的调用
 */
public class AdapterMethod {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        ClassAdapter classAdapter = new ClassAdapter();
        ObjectAdapter objectAdapter = new ObjectAdapter(adaptee);
        classAdapter.request();
        objectAdapter.request();
    }
}

/**
 * 目标
 */
interface Target {
    void request();
}

/**
 * 适配者
 */
class Adaptee {
    public void specialRequest() {
        System.out.println("目标方法被调用");
    }
}

/**
 * 类适配器
 */
class ClassAdapter extends Adaptee implements Target {

    @Override
    public void request() {
        specialRequest();
    }
}

/**
 * 对象适配器
 */
class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specialRequest();
    }
}






