package others;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/17 10:29 AM
 * @description : 迪杰斯特拉
 * 基本思想：
 *  1.引进两个集合S和U。S记录了已求出的最短路径的节点以及对应的最短路径。U记录了剩余的还为求出的节点以及对应的路径
 *  2.初始时S(s)中只有起点s，然后从U中找出距离最近的节点s1，加入S，然后使用新加入的节点来更新U中剩余节点的最短路径
 *  3.此时S(s,s1)，步骤2，知道U中没有节点
 *
 *  相关文章：
 *  https://blog.csdn.net/heroacool/article/details/51014824
 *  https://blog.csdn.net/weixin_39799208/article/details/90046348
 */
public class Dijkstra {

    public static void main(String[] args) {
//        int[][] graph = new int[][] {
//                {0, 2, 6, 4},
//                {Integer.MAX_VALUE, 0, 3, Integer.MAX_VALUE},
//                {7, Integer.MAX_VALUE, 0, 1},
//                {5, Integer.MAX_VALUE, 12, 0}
//        };

        int[][] graph = new int[][] {
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
        int length = graph.length;
        // 起点到其余各点的距离
        int[] dis = Arrays.copyOf(graph[start], length);

        // 是否已经是最短距离
        boolean[] visited = new boolean[length];
        visited[start] = true;

        // n-1趟
        for (int i = 0; i < length - 1; i++) {
            int minIndex = -1;
            // 查找最新值的下标
            for (int j = 0; j < length; j++) {
                if (!visited[j] && (minIndex < 0 || dis[j] < dis[minIndex])) {
                    minIndex = j;
                }
            }
            // 将这一趟找到的最近节点加入集合S
            visited[minIndex] = true;
            // 通过当前节点，更新其余节点的距离
            for (int j = 0; j < length; j++) {
                if (!visited[j] && graph[minIndex][j] != Integer.MAX_VALUE && dis[minIndex] + graph[minIndex][j] < dis[j]) {
                    dis[j] = dis[minIndex] + graph[minIndex][j];
                }
            }
        }

        return dis;
    }

}
