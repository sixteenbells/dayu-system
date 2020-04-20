package test;

import com.sun.jdi.CharType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/23 7:30 PM
 * @description :
 */
public class AliTest {
    public static Map<String, Integer> calculate(String str) {
        Map<String, Integer> resultMap = new HashMap<>();
        if (str == null || str.length() == 0) {
            return resultMap;
        }
        char[] array = str.toCharArray();
        for (int i = 0;i < array.length; i++) {
            char c = array[i];
            int type = Character.getType(c);
            switch (type) {
                case Character.LOWERCASE_LETTER:
                    resultMap.put("英文字符", resultMap.getOrDefault("英文字符", 0) + 1);
                    continue;
                case Character.UPPERCASE_LETTER:
                    resultMap.put("英文字符", resultMap.getOrDefault("英文字符", 0) + 1);
                    continue;
                case Character.OTHER_LETTER:
                    resultMap.put("中文", resultMap.getOrDefault("中文", 0) + 1);
                    continue;
                case Character.DECIMAL_DIGIT_NUMBER:
                    resultMap.put("数字", resultMap.getOrDefault("数字", 0) + 1);
                    continue;
            }
        }
        return resultMap;
    }

}
