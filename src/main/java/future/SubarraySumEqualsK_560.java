package future;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/12/9 2:00 PM
 * @description :
 */
public class SubarraySumEqualsK_560 {

    /**
     * 暴力
     * Time complexity : O(n^2)
     * Space complexity : O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * hashMap实现
     * Time complexity : O(n)
     * Space complexity : O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum2(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int diff = sum - k;
            if (sumMap.containsKey(diff)) {
                count += sumMap.get(diff);
            }
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[]{1, -1, 0, 1, 2};
        int result = subarraySum2(array, 2);
        System.out.println(result);
    }
}
