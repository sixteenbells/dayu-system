package future;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/4 9:56 PM
 * @description : 判断是不是二部图
 * 注意：1.图可能不是联通的，不是联通的看成两个图
 *      2.一个节点也是二分图
 */
public class IsBipartite_785 {

    public static int[] colors;

    public static boolean isBipartite(int[][] graph) {
        colors = new int[graph.length];
        Arrays.fill(colors, -1);
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == -1) {
                if (!dfs(graph, i, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean dfs(int[][] graph, int node, int lastColor) {
        if (colors[node] == -1) {
            colors[node] = lastColor ^ 1;
            for (int i = 0; i < graph[node].length; i++) {
                if (!dfs(graph, graph[node][i], colors[node])) {
                    return false;
                }
            }
        } else if (colors[node] == lastColor) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {1,2,3}, {0,2}, {0,1,3}, {0,2}
        };
        System.out.println(isBipartite(graph));
        LinkedList<Integer> list = new LinkedList<>();
    }
}
