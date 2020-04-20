package future;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/4 8:17 PM
 * @description :最小高度树
 * 分层删除叶子节点
 */
public class FindMinHeightTrees_310 {

    /**
     * 广度优先，分层删除叶子节点，最后剩下的一个或两个叶子节点就是根节点
     *
     * @param n
     * @param edges
     * @return
     */
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new LinkedList<>();
        if (n == 1) {
            result.add(0);
            return result;
        }

        int[] degrees = new int[n];
        Arrays.fill(degrees, 0);
        // 统计各个节点的度
        for (int i = 0; i < edges.length; i++) {
            degrees[edges[i][0]]++;
            degrees[edges[i][1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int nodeCount = n;
        // 将叶子节点入队
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) {
                queue.offer(i);
                nodeCount--;
            }
        }
        // 当队列里的叶子节点个数与剩下的节点个数一致时，得到最终结果
        while (nodeCount > 0) {
            Queue<Integer> tempQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                int number = queue.remove();
                for (int i = 0; i < edges.length; i++) {
                    // 这样避免出错
                    int n1 = edges[i][0];
                    int n2 = edges[i][1];
                    if (n1 == number || n2 == number) {
                        degrees[n1]--;
                        if (degrees[n1] == 1) {
                            tempQueue.offer(n1);
                            nodeCount--;
                        }
                        degrees[n2]--;
                        if (degrees[n2] == 1) {
                            tempQueue.offer(n2);
                            nodeCount--;
                        }
                    }
                }
            }
            queue.addAll(tempQueue);
        }
        result.addAll(queue);
        return result;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = new int[][]{
                {3, 0},
                {3, 1},
                {3, 2},
                {3, 4},
                {5, 4}
        };
        List<Integer> result = findMinHeightTrees(n, edges);
        System.out.println(result.toString());
    }
}
