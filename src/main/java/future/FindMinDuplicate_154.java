package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/6 8:50 PM
 * @description : 寻找旋转数组的最小值，有重复值
 * 当nums[mid] == nums[right] 时，right = right - 1
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/154-find-minimum-in-rotated-sorted-array-ii-by-jyd/
 */
public class FindMinDuplicate_154 {
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] == nums[right]) {
                right = right - 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,2,2,2,2};
        System.out.println(findMin(nums));
    }
}
