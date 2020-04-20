package future;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/5 9:24 PM
 * @description :
 */
public class PairsOfSongs_1010 {
    public static int numPairsDivisibleBy60(int[] time) {
        if (time == null || time.length < 2) {
            return 0;
        }
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < time.length; i++) {
            if (time[i] % 60 > 0) {
                count += map.getOrDefault(60 - time[i] % 60, 0);
            } else {
                count += map.getOrDefault(0 - time[i] % 60, 0);
            }
            map.put(time[i] % 60, map.getOrDefault(time[i] % 60, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[]{30,20,150,100,40};
        System.out.println(numPairsDivisibleBy60(array));
    }
}
