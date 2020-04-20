package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/10/29 4:43 PM
 * @description : 8题：String to Integer
 */
public class Atoi_8 {
    public static int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str == "") {
            return 0;
        }

        char[] numberChars = str.split(" ")[0].toCharArray();
        boolean isNeagtive = false;
        int i = 0;
        if ('-' == numberChars[0]) {
            isNeagtive = true;
            i = 1;
        } else if ('+' == numberChars[0]) {
            i = 1;
        }

        long value = 0;
        for (; i < numberChars.length; i++) {
            if (numberChars[i] >= '0' && numberChars[i] <= '9') {
                if (isNeagtive) {
                    value = value * 10 - (numberChars[i] - '0');
                    if (value < Integer.MIN_VALUE) {
                        value = Integer.MIN_VALUE;
                        break;
                    }
                } else {
                    value = value * 10 + (numberChars[i] - '0');
                    if (value > Integer.MAX_VALUE) {
                        value = Integer.MAX_VALUE;
                        break;
                    }
                }
            } else {
                break;
            }
        }

        return Long.valueOf(value).intValue();
    }

    public static void main(String[] args) {
        String test = "";
        System.out.println(myAtoi(test));
    }

}
