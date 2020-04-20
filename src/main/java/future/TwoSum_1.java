package future;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/12/6 11:03 AM
 * @description :
 */
public class TwoSum_1 {

    /**
     * two pass
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> positionMap = new HashMap<Integer, Integer>(nums.length);
        for (int i = 0;i < nums.length; i++) {
            positionMap.put(nums[i], i);
        }

        for (int i = 0;i < nums.length;i++) {
            if (positionMap.get(target - nums[i]) != null) {
                result[0] = i;
                result[1] = positionMap.get(target - nums[i]);
                return result;
            }
        }
        return result;
    }

    /**
     * one pass
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> positionMap = new HashMap<Integer, Integer>(nums.length);
        for (int i = 0;i < nums.length;i++) {
            if (positionMap.get(target - nums[i]) != null) {
                return new int[]{i , positionMap.get(target - nums[i])};
            }
            positionMap.put(i, nums[i]);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[] {2, 7, 11, 15};
        int[] result = twoSum2(array, 9);
        System.out.println(Arrays.toString(result));
    }
}
