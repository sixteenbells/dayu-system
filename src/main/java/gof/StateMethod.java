package gof;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/7 12:01 PM
 * @description : 状态模式
 *
 */
public class StateMethod {
    public static void main(String[] args) {
        StartState startState = new StartState();
        StopState stopState = new StopState();
        Context context = new Context();
        startState.doAction(context);
        System.out.println("当前环境：" + context.getState());
        stopState.doAction(context);
        System.out.println("当前环境：" + context.getState());
    }

}


/**
 * 抽象状态
 */
interface State {
    void doAction(Context context);
}

class StartState implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("当前是开始状态");
        context.setState(this);
    }
}

class StopState implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("当前是结束状态");
        context.setState(this);
    }
}

/**
 * 环境
 */
class Context {
    private State state;

    public Context() {
        this.state = null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
