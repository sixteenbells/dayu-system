package review;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/11 5:37 PM
 * @description :
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{4, 1, 5, 10, 2, 4};
        System.out.println("排序前：" + Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void quickSort(int[] array, int l, int r) {
        if (l < r) {
            int mid = partition(array, l, r);
            quickSort(array, l, mid - 1);
            quickSort(array, mid + 1, r);
        }
    }

    public static int partition(int[] array, int l, int r) {
        int i = l;
        int j = r;
        int temp = array[i];
        while (i < j) {
            while (i < j && array[j] >= temp) {
                j--;
            }
            if (i < j) {
                array[i] = array[j];
                i++;
            }

            while (i < j && array[i] < temp) {
                i++;
            }
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = temp;
        return i;
    }
}
