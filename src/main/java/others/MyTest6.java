package others;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/28 10:36 AM
 * @description :
 */
public class MyTest6 {
    public static void main(String[] args) {
        Single instance = Single.getInstance();
        System.out.println("count1:" + Single.count1);
        System.out.println("count2:" + Single.count2);
        System.out.println("count2:" + instance.count2);
    }
}
class Single {

    private static Single single = new Single();

    private Single() {
        count1++;
        count2++;
        System.out.println("构造方法count1:" + count1);
        System.out.println("构造方法count2:" + count2);
    }

    public static int count1;
    public static int count2 = 0;

    public static Single getInstance() {
        return single;
    }
}
