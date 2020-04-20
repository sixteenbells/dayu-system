package future;

import java.util.*;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/11 11:48 PM
 * @description :组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class CombineSum_39 {

    public static List<List<Integer>> resultList = new LinkedList<>();

    /**
     * 回溯法
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSumBack(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, new LinkedList<>(), 0, target);
        return resultList;
    }

    private static void backtrack(int[] candidates, LinkedList<Integer> track, int index, int target) {
        if (target == 0) {
            resultList.add(track);
            return ;
        }
        for (int i = index; i < candidates.length; i++) {
            // 剪枝
            if (target - candidates[i] < 0) {
                break;
            }
            track.add(candidates[i]);
            backtrack(candidates, track, i, target - candidates[i]);
            track.removeLast();
        }
    }

    /**
     * 动态规划
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, 0);
        List<List<Integer>>[] pathsArray = new LinkedList[target + 1];
        for (int i = 0; i <= target; i++) {
            pathsArray[i] = new LinkedList<>();
        }

        dp[0] = 1;

        for (int i = 0; i < candidates.length; i++) {
            for (int j = candidates[i]; j <= target; j++) {
                if (dp[j - candidates[i]] > 0) {
                    List<List<Integer>> paths = new LinkedList<>(pathsArray[j - candidates[i]]);
                    if (paths.isEmpty()) {
                        List<Integer> list = new LinkedList<>();
                        list.add(candidates[i]);
                        paths.add(list);
                    } else {
                        for (int k = 0; k < paths.size(); k++) {
                            List<Integer> path = new LinkedList<>(paths.get(k));
                            path.add(candidates[i]);
                            paths.set(k, path);
                        }
                    }
                    pathsArray[j].addAll(paths);
                }

                dp[j] = dp[j] + dp[j - candidates[i]];
            }
            printDp(dp, target);
        }

        System.out.println("count:" + dp[target]);
        return pathsArray[target];
    }

    /**
     * 打印dp
     *
     * @param dp
     * @param capacity
     */
    public static void printDp(int[] dp, int capacity) {
        for (int k = 0; k <= capacity; k++) {
            if (dp[k] < 0) {
                System.out.print(-1 + "\t");
            } else {
                System.out.print(dp[k] + "\t");
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) throws Exception {
        int[] candidates = new int[]{2, 3, 8, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }
}
