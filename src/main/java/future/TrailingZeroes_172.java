package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/19 11:17 PM
 * @description :阶乘后的零
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/xiang-xi-tong-su-de-si-lu-fen-xi-by-windliang-3/
 */
public class TrailingZeroes_172 {
    public static int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n = n / 5;
        }
        return count;
    }
}
