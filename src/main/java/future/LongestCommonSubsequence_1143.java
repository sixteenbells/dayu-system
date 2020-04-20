package future;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/7 10:32 PM
 * @description : 最长公共子序列
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * https://blog.csdn.net/afei__/article/details/83153399
 * http://www.kancloud.cn:8080/digest/pieces-algorithm/163624
 * https://juejin.im/post/5c1e06655188254eaa5c3b58
 */
public class LongestCommonSubsequence_1143 {
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1 == "" || text2 == null || text2 == "") {
            return 0;
        }

        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        Arrays.fill(dp[0], 0);
        for (int i = 0; i < text1.length(); i++) {
            dp[i][0] = 0;
        }

        int maxLen = 0;
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] >= maxLen) {
                        maxLen = dp[i][j];
                    }
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] , dp[i][j - 1]);
                }
            }
        }

        return maxLen;
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
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence(text1, text2));
    }
}
