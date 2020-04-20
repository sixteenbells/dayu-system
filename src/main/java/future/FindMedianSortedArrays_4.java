package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/19 11:29 PM
 * @description : 寻找两个有序数组的中位数
 * https://leetcode.com/submissions/detail/109357612/
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
 */
public class FindMedianSortedArrays_4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if ((length % 2) == 0) {
            return (findKth(nums1, nums2, 0, 0, length / 2) + findKth(nums1, nums2, 0, 0, length / 2 + 1)) / 2.0;
        }
        return findKth(nums1, nums2, 0, 0, length / 2 + 1);
    }

    public static double findKth(int[] nums1, int[] nums2, int s1, int s2, int k) {
        if (s1 >= nums1.length) {
            return nums2[s2 + k - 1];
        }
        if (s2 >= nums2.length) {
            return nums1[s1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[s1], nums2[s2]);
        }

        int mid1 = s1 + k/2 - 1;
        int mid2 = s2 + k/2 - 1;
        int midValue1 = mid1 < nums1.length ? nums1[mid1] : Integer.MAX_VALUE;
        int midValue2 = mid2 < nums2.length ? nums2[mid2] : Integer.MAX_VALUE;
        if (midValue1 < midValue2) {
            return findKth(nums1, nums2, mid1 + 1, s2, k - k / 2);
        } else {
            return findKth(nums1, nums2, s1, mid2 + 1, k - k / 2);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

}
