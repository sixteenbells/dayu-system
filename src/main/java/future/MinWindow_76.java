package future;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/17 10:10 PM
 * @description :最小覆盖子串
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class MinWindow_76 {

    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> tMap = new HashMap<>();
        for (Character c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int left = 0;
        int right = 0;
        Map<Character, Integer> sMap = new HashMap<>();
        // 满足条件的字符串格式
        int match = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (tMap.containsKey(c)) {
                sMap.put(c, sMap.getOrDefault(c, 0) + 1);
                if (sMap.get(c).equals(tMap.get(c))) {
                    match++;
                }
            }
            right++;
            while (match == tMap.size()) {
                // 更新最短字符串
                if (right - left< minLen) {
                    minLen = right - left;
                    start = left;
                }

                char leftChar = s.charAt(left);
                if (sMap.containsKey(leftChar)) {
                    sMap.put(leftChar, Math.max(0, sMap.getOrDefault(leftChar,0) - 1));
                    if (sMap.get(leftChar) < tMap.get(leftChar)) {
                        match--;
                    }
                }
                left++;

            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        String s = "a";
        String t = "b";
        System.out.println(minWindow(s, t));
    }
}
