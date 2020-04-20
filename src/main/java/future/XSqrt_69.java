package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/6 5:08 PM
 * @description :x的平方根
 * https://leetcode-cn.com/problems/sqrtx/
 */
public class XSqrt_69 {
    /**
     * 注意点，int输入的x，对其中的点进行求平方，可能会超过Integer的最大值21亿
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long left = 1;
        long right = x / 2;
        while (left < right) {
            long mid = (left + right + 1) >>> 1;
            long square = mid * mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int) left;
    }

    public static void main(String[] args) {
        System.out.println("sqrt:" + mySqrt(2147395599));
        System.out.println(Integer.MAX_VALUE);
    }
}
