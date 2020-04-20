package future;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/9 5:21 PM
 * @description : 解码字符串
 */
public class DecodeLetter_91 {


    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int length = s.length();

        int[] dp = new int[length + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 1; i < length; i++) {
            if ('0' == s.charAt(i)) {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    dp[i + 1] = dp[i - 1];
                } else {
                    return 0;
                }
            } else {
                if ('1' == s.charAt(i - 1) || ('2' == s.charAt(i - 1) && '1' <= s.charAt(i - 1) && s.charAt(i - 1) <= '6')) {
                    dp[i + 1] = dp[i] + dp[i - 1];
                } else {
                    dp[i + 1] = dp[i];
                }
            }
        }

        return dp[length];
    }

    /**
     * 打印dp
     *
     * @param dp
     * @param capacity
     */
    public static void printDp(int[] dp, int capacity) {
        for (int k = 0; k <= capacity; k++) {
            if (dp[k] < 0) {
                System.out.print(-1 + "\t");
            } else {
                System.out.print(dp[k] + "\t");
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) throws Exception {
        String input = "12203";
        System.out.println(numDecodings(input));

    }

}
