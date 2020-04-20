package gof;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/6 2:49 PM
 * @description :观察者模式
 * https://www.runoob.com/design-pattern/observer-pattern.html
 * http://c.biancheng.net/view/1390.html
 */
public class ObserverMethod {

    public static void main(String[] args) {
        ConcreteSubject concreteSubject = new ConcreteSubject();
        Observer observerA = new ObserverA(concreteSubject);
        Observer observerB = new ObserverB(concreteSubject);

        concreteSubject.setData(1);
        concreteSubject.setData(2);
    }

}

/**
 * 抽象主题
 */
abstract class Subject<E> {
    protected LinkedBlockingQueue<Observer> observers = new LinkedBlockingQueue<>();
    private E data;

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
        notifyObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        System.out.println("观察对象发生变化:" + data);
        for (Observer observer : observers) {
            observer.response();
        }
    }
}

/**
 * 具体主题
 */
class ConcreteSubject extends Subject<Integer> {

}

/**
 * 观察者
 */
abstract class Observer {
    public Subject subject;
    abstract void response();
}

class ObserverA extends Observer {

    public ObserverA(Subject subject) {
        subject.attach(this);
        this.subject = subject;
    }

    @Override
    public void response() {
        System.out.println("观察者A做出反应:" + subject.getData());
    }
}

class ObserverB extends Observer {

    public ObserverB(Subject subject) {
        subject.attach(this);
        this.subject = subject;
    }

    @Override
    public void response() {
        System.out.println("观察者B做出反应:" + subject.getData());
    }
}
