package future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/12/6 3:39 PM
 * @description :
 */
public class TreeSum_15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] + nums[i] == 0) {
                    List<Integer> sumList = new ArrayList<>();
                    sumList.add(nums[i]);
                    sumList.add(nums[left]);
                    sumList.add(nums[right]);
                    if (!result.contains(sumList)) {
                        result.add(sumList);
                    }
                    left++;
                } else if (nums[left] + nums[right] + nums[i] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[]{-2, 0, 1, 1, 2};
        List<List<Integer>> result = threeSum(array);
        System.out.println(result.toString());
    }
}
