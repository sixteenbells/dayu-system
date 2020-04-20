package future;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/6 9:09 PM
 * @description : 最长子串
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstring_3 {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s == "") {
            return 0;
        }
        int count = 0;
        Map<Character, Integer> positionMap = new HashMap<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            count = Math.max(count, (i - start));
            if (positionMap.get(s.charAt(i)) != null && positionMap.get(s.charAt(i)) >= start) {
                start = positionMap.get(s.charAt(i)) + 1;
            }
            positionMap.put(s.charAt(i), i);
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        String input = "abcdefcha";
        System.out.println(lengthOfLongestSubstring(input));
    }
}
