package future;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/16 11:07 PM
 * @description :合并区间
 * https://leetcode-cn.com/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode/
 * https://leetcode-cn.com/problems/merge-intervals/solution/fang-fa-2de-java-by-linus-9/
 */
public class MergeRange_56 {

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return intervals;
        }
        // 先排序
        Arrays.sort(intervals, (array1, array2) -> Integer.compare(array1[0], array2[0]));
        List<int[]> resultList = new LinkedList<>();
        int[] currentInterval = intervals[0];
        resultList.add(currentInterval);
        for (int[] array : intervals) {
            // 如果当前区间的end小于后一个区间的start，则直接加入后一个区间
            if (currentInterval[1] < array[0]) {
                resultList.add(array);
                currentInterval = array;
            } else {
                // 否则则修改当前区间的end
                currentInterval[1] = Math.max(currentInterval[1], array[1]);
            }
        }
        return resultList.toArray(new int[resultList.size()][]);
    }

    public static void main(String[] args) {
        int[][] array = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        int[][] resultList = merge(array);
        System.out.println(resultList);
    }
}
