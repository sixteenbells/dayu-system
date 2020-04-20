package sort;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/3 9:20 PM
 * @description : 堆排序
 * https://blog.csdn.net/weixin_41190227/article/details/86600821
 * https://jingyan.baidu.com/article/5225f26b057d5de6fa0908f3.html
 */
public class HeapSort {


    public static void heapSort(int[] array) {
        int len = array.length;
        // 构建大顶堆
        makeMaxHeap(array, len);
        /**
         *  堆顶元素为最大值，将其与当前堆的最后一个元素交换，然后再调整堆，得到第二大的元素，然后再与
         *  当前堆的最后一个元素交换，。。。。。。不断建堆，交换元素，建堆，交换元素，直到堆只有一个元素时，
         *  数组有序
         */
        while (len > 1) {
            // 堆顶元素与堆最后一个元素交换
            swap(array, 0, len - 1);
            len--;
            // 交换后不是大顶堆，调整堆
            adjustHeap(array, 0, len);
        }

    }

    /**
     * 构建大顶堆
     *
     * @param array
     */
    public static void makeMaxHeap(int[] array, int len) {
        // 从最后一个非叶子节点开始调整堆
        // i的左右节点分别为2*i+1, 2*i+2
        // 一定是i >= 0
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustHeap(array, i, len);
        }
    }

    /**
     * 调整堆
     *
     * @param array
     * @param parent
     */
    public static void adjustHeap(int[] array, int parent, int len) {
        // 最大数的index
        int maxIndex = parent;
        // 左节点存在，且大于最大值，则左节点为最大值
        if (parent * 2 + 1 < len && array[parent * 2 + 1] > array[maxIndex]) {
            maxIndex = parent * 2 + 1;
        }
        // 右节点存在，且大于最大值，则右节点为最大值
        if (parent * 2 + 2 < len && array[parent * 2 + 2] > array[maxIndex]) {
            maxIndex = parent * 2 + 2;
        }
        // 如果父节点不是最大值，则将父节点与最大值交换，并自顶向下
        // 递归调整交换过的位置
        if (maxIndex != parent) {
            swap(array, maxIndex, parent);
            adjustHeap(array, maxIndex, len);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 1, 5, 7, 2, 7, 9};
        System.out.println("排序前：" + Arrays.toString(array));
        heapSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
