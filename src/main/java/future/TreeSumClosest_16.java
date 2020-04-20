package future;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/12/6 5:09 PM
 * @description :
 */
public class TreeSumClosest_16 {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                closest = Math.abs(target - closest) > Math.abs(target - sum) ? sum : closest;
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closest;
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[] {-1, 2, 1, -4};
        int result = threeSumClosest(array, 2);
        System.out.println(result);
    }
}
