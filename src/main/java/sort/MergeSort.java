package sort;

import org.apache.zookeeper.data.Id;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/3 9:03 PM
 * @description : 归并排序
 * https://blog.csdn.net/weixin_41190227/article/details/86600821
 */
public class MergeSort {

    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int middle = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] < right[j]) {
                result[index] = left[i++];
            } else {
                result[index] = right[j++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 1, 5, 7, 2, 7, 9};
        System.out.println("排序前：" + Arrays.toString(array));
        int[] result = mergeSort(array);
        System.out.println("排序后：" + Arrays.toString(result));
    }

}
