package future;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/7 8:56 PM
 * @description : 最长公共子串
 * https://blog.csdn.net/afei__/article/details/83153399
 * http://www.kancloud.cn:8080/digest/pieces-algorithm/163624
 * https://juejin.im/post/5c1e06655188254eaa5c3b58
 */
public class LongCommonSubStr {

    public static String longCommonSubStr(String s1, String s2) {
        if (s1 == null || s1 == "" || s2 == null || s2 == "") {
            return "";
        }

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        Arrays.fill(dp[0], 0);
        // 初始化base
        for (int i = 0; i < s1.length(); i++) {
            dp[i][0] = 0;
        }

        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] >= maxLen) {
                        maxLen = dp[i][j];
                        maxEnd = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        printDp(dp, s1, s2);

        return s1.substring(maxEnd - maxLen, Math.min(maxEnd + 1, s1.length()));

    }

    public static void printDp(int[][] dp, String s1, String s2) {
        System.out.print("    ");
        for (int i = 0; i < s2.length(); i++) {
            System.out.print(s2.charAt(i) + " ");
        }
        System.out.println();
        for (int i = 0; i <= s1.length(); i++) {
            if (i != 0) {
                System.out.print(s1.charAt(i - 1) + " ");
            } else {
                System.out.print("  ");
            }
            for (int j = 0; j <= s2.length(); j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        String s1 = "hish";
        String s2 = "fish";
        System.out.println(longCommonSubStr(s1, s2));
    }
}
