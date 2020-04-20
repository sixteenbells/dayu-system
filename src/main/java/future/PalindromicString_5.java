package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/7 7:51 PM
 * @description : 最长回文子串
 * https://leetcode.com/problems/longest-palindromic-substring/
 * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
 */
public class PalindromicString_5 {

    /**
     * 中心扩散法
     * 注意：中心有可能在一个字符上aba；也可能在两个字符中间baab
     * @param s
     * @return
     */
    public static String longestPalindromeCenter(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int maxLen = 1;
        String resultString = s.substring(0, maxLen);
        // 第一个和最后一个不用扩散
        for (int i = 1; i < s.length() - 1; i++) {
            String oddStr = centerSpread(s, i, i);
            String evenStr = centerSpread(s, i, i+1);
            String maxStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLen < maxStr.length()) {
                maxLen = maxStr.length();
                resultString = maxStr;
            }
        }
        return resultString;
    }

    public static String centerSpread(String s, int l, int r) {
        // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            } else {
                break;
            }
        }
        // 这里要小心，跳出 while 循环时，恰好满足 s.charAt(i) != s.charAt(j)，因此不能取 i，不能取 j
        return s.substring(l + 1, r);
    }

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        int start = 0;
        int maxLen = 1;
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }

                if (dp[i][j]) {
                    int len = j - i + 1;
                    if (len > maxLen) {
                        start = i;
                        maxLen = len;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }


    public static void main(String[] args) throws Exception {
        String s = "babbad";
        System.out.println(longestPalindromeCenter(s));
    }
}
