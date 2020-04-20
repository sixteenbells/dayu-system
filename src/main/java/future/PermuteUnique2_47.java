package future;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/10 11:37 AM
 * @description :全排列，有重复
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class PermuteUnique2_47 {
    static LinkedList<List<Integer>> result = new LinkedList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] uses = new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(nums, uses, new LinkedList<>());
        return result;
    }

    public static void backTrack(int[] nums, boolean[] uses, LinkedList<Integer> arrangeList) {
        if (nums.length == arrangeList.size()) {
            result.add(new LinkedList<>(arrangeList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (uses[i]) {
                continue;
            }
            // 判断上一个有没有使用，如果没有，则说明使用过，但是回溯了
            if (i > 0 && nums[i] == nums[i - 1] && uses[i - 1]) {
                continue;
            }
            uses[i] = true;
            arrangeList.add(nums[i]);

            backTrack(nums, uses, arrangeList);

            uses[i] = false;
            arrangeList.removeLast();
        }
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[]{1, 1, 3};
        System.out.println(permuteUnique(array).toString());
    }

}
