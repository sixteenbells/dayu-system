package future;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/6 10:45 PM
 * @description : 串联所有单词的字串
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 */
public class SubStringAllWords_30 {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> resultList = new LinkedList<>();
        if (s == null || s == "" || words == null || words.length == 0) {
            return resultList;
        }

        int wordLength = words[0].length();
        int totalCount = 0;
        Map<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordMap.put(words[i], wordMap.getOrDefault(words[i], 0) + 1);
            totalCount += words[i].length();
        }

        for (int i = 0; i <= s.length() - totalCount; i++) {
            Map<String, Integer> subWordMap = new HashMap<>();
            int j = i;
            for (; j < i + totalCount; j += wordLength) {
                String word = s.substring(j, j + wordLength);
                int wordCount = subWordMap.getOrDefault(word, 0) + 1;
                if (!wordMap.containsKey(word) || wordCount > wordMap.get(word)) {
                    break;
                }
                subWordMap.put(word, wordCount);
            }
            if (j >= i + totalCount) {
                resultList.add(i);
            }
        }
        return resultList;
    }

    public static void main(String[] args) throws Exception {
        String input = "abfoobarabbarbarfoofoobarbarcb";
        String[] words = new String[]{"foo", "bar", "bar"};
        System.out.println(findSubstring(input, words));
    }
}
