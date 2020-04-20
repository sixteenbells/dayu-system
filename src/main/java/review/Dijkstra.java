package review;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/10 4:54 PM
 * @description :
 */
public class Dijkstra {

    public static void main(String[] args) {
//        int[][] graph = new int[][] {
//                {0, 2, 6, 4},
//                {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
//                {7, Integer.MAX_VALUE, 0, 1},
//                {5, Integer.MAX_VALUE, 12, 0}
//        };

        int[][] graph = new int[][]{
                {0, 12, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 16, 14},
                {12, 0, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, 7, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 10, 0, 3, 5, 6, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 3, 0, 4, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 5, 4, 0, 2, 8},
                {16, 7, 6, Integer.MAX_VALUE, 2, 0, 9},
                {14, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 8, 9, 0}
        };
        int[] dis = dijsktra(graph, 3);
        System.out.println(Arrays.toString(dis));
    }

    public static int[] dijsktra(int[][] graph, int start) {
        int length = graph[0].length;
        // 起点到其他节点的最短距离
        int[] ds = Arrays.copyOf(graph[start], length);
        boolean[] visited = new boolean[length];
        visited[start] = true;

        // n-1趟，每次加入一个节点
        for (int i = 1; i < length; i++) {
            int minIndex = -1;
            for (int k = 0; k < length; k++) {
                if (!visited[k] && (minIndex < 0 || ds[k] < ds[minIndex])) {
                    minIndex = k;
                }
            }
            // 将最近节点加入
            visited[minIndex] = true;
            // 用新加入的节点更新最短路径
            for (int k = 0; k < length; k++) {
                if (!visited[k] && graph[minIndex][k] != Integer.MAX_VALUE && (ds[minIndex] + graph[minIndex][k] < ds[k])) {
                    ds[k] = ds[minIndex] + graph[minIndex][k];
                }
            }
        }
        return ds;
    }
}
