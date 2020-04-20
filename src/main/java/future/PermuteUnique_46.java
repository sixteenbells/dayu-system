package future;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/10 11:37 AM
 * @description :全排列，没有重复
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class PermuteUnique_46 {
    static LinkedList<List<Integer>> result = new LinkedList<>();

    public static List<List<Integer>> permute(int[] nums) {
        boolean[] uses = new boolean[nums.length];
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
            uses[i] = true;
            arrangeList.add(nums[i]);

            backTrack(nums, uses, arrangeList);

            uses[i] = false;
            arrangeList.removeLast();
        }
    }

    public static void main(String[] args) throws Exception {
        int[] array = new int[]{1, 2, 3};
        System.out.println(permute(array).toString());
    }

}
