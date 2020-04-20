package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/6 5:06 PM
 * @description : 标准二分查找
 * https://leetcode-cn.com/problems/binary-search/
 */
public class BinarySearch_704 {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,3,5,9,12};
        int target = 9;
        System.out.println("index:" + search(nums, target));
    }
}
