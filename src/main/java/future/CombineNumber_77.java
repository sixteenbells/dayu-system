package future;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/12 12:06 AM
 * @description :组合
 * https://leetcode-cn.com/problems/combinations/
 */
public class CombineNumber_77 {
    public static List<List<Integer>> resultList = new LinkedList<>();

    public static List<List<Integer>> combine(int n, int k) {
        boolean[] use = new boolean[n + 1];
        backTrack(n, k, 1, new LinkedList<>());
        return resultList;
    }

    public static void backTrack(int n, int k, int x, LinkedList<Integer> selectList) {
        if (selectList.size() == k) {
            resultList.add(new LinkedList<>(selectList));
            return ;
        }

        for (int i = x; i <= n; i++) {
            selectList.add(i);
            backTrack(n, k, i + 1, selectList);
            selectList.removeLast();
        }
    }

    public static void main(String[] args) throws Exception {
        int n = 4;
        int k = 2;

        System.out.println(combine(n, k).toString());
    }
}
