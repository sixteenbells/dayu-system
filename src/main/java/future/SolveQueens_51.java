package future;

import java.util.*;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/10 2:13 PM
 * @description : N皇后
 * https://leetcode-cn.com/problems/n-queens/submissions/
 */
public class SolveQueens_51 {

    public static List<List<String>> result = new LinkedList<>();

    public static List<List<Integer[]>> resultPositionList = new LinkedList<>();
    // 上方, y相同
    public static Set<Integer> upperSet = new HashSet<>();
    // 左上方, y - x = 常数
    public static Set<Integer> upperLeftSet = new HashSet<>();
    // 右上方，x + y = 常数
    public static Set<Integer> upperRightSet = new HashSet<>();

    public static int[] solution;


    public static List<List<String>> solveNQueens(int n) {
        solution = new int[n];
        backTrack(n, 0, solution);

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(result.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println(resultPositionList.size());
        return result;
    }

    public static void backTrack(int N, int x, int[] solution) {
        if (x == N) {
            addSolution(solution, N);
        }

        for (int y = 0; y < N; y++) {
            // 判断该位置是否能选择
            if (upperSet.contains(y) || upperLeftSet.contains((y - x)) || upperRightSet.contains((x + y))) {
                continue;
            }
            upperSet.add(y);
            upperLeftSet.add(y - x);
            upperRightSet.add(x + y);
            solution[x] = y;

            backTrack(N, x + 1, solution);

            upperSet.remove(y);
            upperLeftSet.remove(y - x);
            upperRightSet.remove(x + y);
        }

    }

    public static void addSolution(int[] solution, int N) {
        List<String> path = new LinkedList<>();
        for (int i = 0; i < solution.length; i++) {
            StringBuffer answer = new StringBuffer();
            for (int j = 0; j < N; j++) {
                if (j == solution[i]) {
                    answer.append("Q");
                } else {
                    answer.append(".");
                }
            }
            path.add(answer.toString());
        }
        result.add(path);
    }

    public static void main(String[] args) throws Exception {
        solveNQueens(4);
    }
}
