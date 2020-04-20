package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/22 11:47 PM
 * @description : 一年中的第几天
 * https://www.cnblogs.com/xiaochuan94/archive/2019/09/21/11561361.html
 */
public class DayOfYear_1154 {
    public static int dayOfYear(String date) {
        int[] array = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        for (int i = 0; i < month - 1; i++) {
            day += array[i];
        }
        return isLeapYear(year) ? day + 1 : day;
    }

    public static boolean isLeapYear(int year) {
        // 普通闰年，能被4整除，但是后两位不以00结尾
        if (year%4 == 0 && year%100 != 0) {
            return true;
        }
        // 世纪闰年，后两位以00结尾，且能被400整除
        if (year%100 == 0 && year%400 == 0) {
            return true;
        }
        return false;
    }
}
