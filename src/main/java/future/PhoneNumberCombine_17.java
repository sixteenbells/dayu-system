package future;

import java.util.*;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/11 10:26 PM
 * @description :电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class PhoneNumberCombine_17 {

    public static Map<Character, String> numberMap = new HashMap<>();

    static {
        numberMap.put('2', "abc");
        numberMap.put('3', "def");
        numberMap.put('4', "ghi");
        numberMap.put('5', "jkl");
        numberMap.put('6', "mno");
        numberMap.put('7', "pqrs");
        numberMap.put('8', "tuv");
        numberMap.put('9', "wxyz");
    }

    public static List<String> letterCombinations(String digits) {
        List<String> resultList = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return resultList;
        }

        resultList.add("");
        for (int i = 0; i < digits.length(); i++) {
            List<String> tempList = new LinkedList<>();
            String numberString = numberMap.get(digits.charAt(i));
            for (int j = 0; j < resultList.size(); j++) {
                for (int k = 0; k < numberString.length(); k++) {
                    tempList.add(resultList.get(j) + numberString.charAt(k));
                }
            }
            resultList = tempList;
        }

        return resultList;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(letterCombinations(null));
    }
}
