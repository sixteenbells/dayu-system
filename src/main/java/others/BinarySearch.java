package others;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/10 8:22 PM
 * @description :
 */
public class BinarySearch {

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (array[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return array[left];
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 4, 6, 10};
        System.out.println(binarySearch(array, 12));
    }
}
