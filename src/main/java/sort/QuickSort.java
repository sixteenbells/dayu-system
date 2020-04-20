package sort;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/3 8:20 PM
 * @description :快排
 * https://www.runoob.com/w3cnote/quick-sort.html
 */
public class QuickSort {


    public static void quickSort(int[] array, int l, int r) {
        if (l < r) {
            int middle = partition(array, l, r);
            quickSort(array, l, middle - 1);
            quickSort(array, middle + 1, r);
        }
    }

    /**
     * 返回调整后基准数的位置
     * 填坑法
     *
     * @param array
     * @param l
     * @param r
     * @return
     */
    public static int partition(int[] array, int l, int r) {
        int i = l, j = r;
        // 第一个元素为第一个坑
        int x = array[l];
        while (i < j) {
            // 从右往左找出小于x的数来填array[i]的坑
            while (i < j && array[j] >= x) {
                j--;
            }
            if (i < j) {
                // 将array[j]填到array[i]中，则形成新的坑array[j]
                array[i] = array[j];
                i++;
            }

            // 从左往右找出大于x的数来填array[j]的坑
            while (i < j && array[i] < x) {
                i++;
            }
            if (i < j) {
                // 将array[i]填到array[j]中，则形成新的坑array[i]
                array[j] = array[i];
                j--;
            }
        }
        // 最后i=j，i为基准数的位置，将x填入这个坑
        array[i] = x;
        return i;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 1, 5, 10, 2, 4};
        System.out.println("排序前：" + Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
