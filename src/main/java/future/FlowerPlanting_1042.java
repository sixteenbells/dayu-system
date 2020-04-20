package future;

import java.util.*;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/5 11:56 PM
 * @description :https://leetcode.com/problems/flower-planting-with-no-adjacent/
 */
public class FlowerPlanting_1042 {

    public static int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new LinkedList<>());
        }
        for (int i = 0; i < paths.length; i++) {
            graph.get(paths[i][0]).add(paths[i][1]);
            graph.get(paths[i][1]).add(paths[i][0]);
        }

        Map<Integer, Integer> colorMap = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            dfs(i, graph, colorMap);
        }

        int[] result = new int[N];
        for (Integer key : colorMap.keySet()) {
            result[key - 1] = colorMap.get(key);
        }

        return result;
    }

    public static void dfs(int n, Map<Integer, List<Integer>> graph, Map<Integer, Integer> colorMap) {
        if (colorMap.containsKey(n)) {
            return;
        }

        List<Integer> nbList = graph.get(n);
        if (nbList == null) {
            return ;
        }
        Set<Integer> exists = new HashSet<>();
        for (int i = 0; i < nbList.size(); i++) {
           if (colorMap.get(nbList.get(i)) != null) {
               exists.add(colorMap.get(nbList.get(i)));
           }
        }

        int color = 0;
        for (int i = 1; i <= 4; i++) {
            if (!exists.contains(i)) {
                color = i;
                break;
            }
        }

        colorMap.put(n, color);

        for (int i = 0; i < nbList.size(); i++) {
            if (colorMap.get(nbList.get(i)) == null) {
                dfs(nbList.get(i), graph, colorMap);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        int N = 4;
        int[][] input = new int[][]{
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 1},
                {1, 3},
                {2, 4}
        };
        int[] answer = gardenNoAdj(N, input);
        System.out.println(Arrays.toString(answer));
    }
}
