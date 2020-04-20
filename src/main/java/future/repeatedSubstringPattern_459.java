package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/16 10:14 AM
 * @description :
 */
public class repeatedSubstringPattern_459 {
    public static boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.length() % i == 0) {
                if (judge(s.substring(0, i), s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean judge(String sub, String S) {
        int len = sub.length();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != sub.charAt(i % len)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abacaba";
        System.out.println(repeatedSubstringPattern(s));
    }
}
