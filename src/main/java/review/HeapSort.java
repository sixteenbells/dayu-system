package review;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/11 6:34 PM
 * @description :
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{3, 1, 5, 7, 2, 7, 9};
        System.out.println("排序前：" + Arrays.toString(array));
        heapSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void heapSort(int[] array) {
        if (array == null || array.length < 2) {
            return ;
        }

        int len = array.length;
        makeMaxHeap(array);

        while (len > 1) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0, len);
        }

    }

    public static void makeMaxHeap(int[] array) {
        int len = array.length;
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustHeap(array, i, len);
        }
    }

    public static void adjustHeap(int[] array, int parent, int len) {
        int maxIndex = parent;
        int leftChildren = parent * 2 + 1;
        int rightChildren = parent * 2 + 2;
        if (leftChildren < len && array[leftChildren] > array[parent]) {
            maxIndex = leftChildren;
        }
        if (rightChildren < len && array[rightChildren] > array[parent]) {
            maxIndex = rightChildren;
        }
        if (maxIndex != parent) {
            swap(array, parent, maxIndex);
            adjustHeap(array, maxIndex, len);
        }
    }

    public static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
