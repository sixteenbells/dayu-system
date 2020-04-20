package future;

import java.util.*;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/3 2:19 PM
 * @description :课程表拓扑排序
 * https://leetcode-cn.com/problems/course-schedule/
 * 即判断有向无环图是否有环
 */
public class CoursesCanFinish_207 {
    /**
     * 入度表（广度优先）
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // 入度表
        int[] indegrees = new int[numCourses];
        Arrays.fill(indegrees, 0);
        for (int i = 0; i < prerequisites.length; i++) {
            indegrees[prerequisites[i][1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        // 入度为0的入队列
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int number = queue.remove();
            numCourses--;
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][0] == number) {
                    indegrees[prerequisites[i][1]]--;
                    if (indegrees[prerequisites[i][1]] == 0) {
                        queue.add(prerequisites[i][1]);
                    }
                }
            }
        }
        return numCourses == 0;
    }

    public static void main(String[] args) throws Exception {
        int[][] input = new int[][]{
                {0, 1},
                {1, 2},
                {2, 3},
                {1, 0}
        };
        System.out.println(canFinish(4, input));
    }
}
