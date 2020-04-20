package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/16 11:30 AM
 * @description :
 */
public class SubarraySum_560 {

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= nums.length; j++) {
                if (sums[j] - sums[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,3,2};
        System.out.println(subarraySum(nums, 5));
    }
}
