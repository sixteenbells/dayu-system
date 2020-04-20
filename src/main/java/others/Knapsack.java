package others;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/12/10 3:43 PM
 * @description :背包问题
 */
public class Knapsack {

    /**
     * 0-1背包问题
     * https://zhuanlan.zhihu.com/p/85780471
     * 公式：f[i][j] = f[i-1][j]                               j < w[i]
     *      f[i][j] = max(f[i-1][j], f[i-1][j-w[i]] + v[i])   j >= w[i]
     */

    /**
     * 0-1背包，二维数组实现
     *
     * @param num
     * @param capacity
     * @param weights
     * @param values
     * @return
     */
    public static int knapsack1(int num, int capacity, int[] weights, int[] values) {
        int[][] dp = new int[num + 1][capacity + 1];
        Arrays.fill(dp[0], 0);
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[num][capacity];
    }

    /**
     * 0-1背包，一维数组实现
     * 一维必须是逆序的，从右到左
     *
     * @param num
     * @param capacity
     * @param weights
     * @param values
     * @return
     */
    public static int knapsack2(int num, int capacity, int[] weights, int[] values) {
        int[] dp = new int[capacity + 1];
        Arrays.fill(dp, 0);
        for (int i = 0; i < num; i++) {
            // 从后向前遍历
            for (int j = capacity; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[capacity];
    }

    /**
     * 01背包，处理一件物品
     *
     * @param dp
     * @param capacity
     * @param weight
     * @param value
     */
    public static void ZeroOnePack(int[] dp, int capacity, int weight, int value) {
        for (int j = capacity; j >= weight; j--) {
            dp[j] = Math.max(dp[j], dp[j - weight] + value);
        }
    }

    /**
     * 完全背包，处理一件物品
     *
     * @param dp
     * @param capacity
     * @param weight
     * @param value
     */
    public static void CompletePack(int[] dp, int capacity, int weight, int value) {
        for (int j = weight; j <= capacity; j++) {
            dp[j] = Math.max(dp[j], dp[j - weight] + value);
        }
    }

    /**
     * 多重背包，处理一件物品
     * https://zhuanlan.zhihu.com/p/85780471
     *
     * @param dp
     * @param capacity
     * @param weight
     * @param value
     * @param mi       个数
     */
    public static void MultiplePack(int[] dp, int capacity, int weight, int value, int mi) {
        if (mi * weight > capacity) {
            // 如果当前物品总重量大于背包容量，则可以全部拿完，相当于完全背包
            CompletePack(dp, capacity, weight, value);
            return;
        }
        int k = 1;
        // 1, 2, ..., 2^(k-1)
        while (k < mi) {
            ZeroOnePack(dp, capacity, weight, value);
            mi -= k;
            k *= 2;
        }
        // 此时mi已经变成了mi - (2^k - 1) = mi - 2^k + 1
        ZeroOnePack(dp, capacity, mi * weight, mi * value);
    }

    /**
     * 完全背包问题
     * https://zhuanlan.zhihu.com/p/85780471
     * 公式：f[i][j] = f[i-1][j]                               j < w[i]
     * f[i][j] = max(f[i-1][j], f[i][j-w[i]] + v[i])     j >= w[i]
     * 注意：从左到右
     */

    public static int completeKnapsack(int num, int capacity, int[] weights, int[] values) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < num; i++) {
            // 从左到右
//                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            CompletePack(dp, capacity, weights[i], values[i]);
            printDp(dp, capacity);
        }
        return dp[capacity];
    }

    /**
     * 多重背包
     *
     * @param num
     * @param capacity
     * @param weights
     * @param values
     * @param MI       物品个数
     * @return
     */
    public static int multipleKnapsack(int num, int capacity, int[] weights, int[] values, int[] MI) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < num; i++) {
            MultiplePack(dp, capacity, weights[i], values[i], MI[i]);
            printDp(dp, capacity);
        }
        return dp[capacity];
    }

    /**
     * 0-1 背包必须装满
     * 关键是初始化时除了F[0]=0,其他都为-inf
     * https://www.cnblogs.com/buddho/p/7867920.html
     *
     * @param num
     * @param capacity
     * @param weights
     * @param values
     * @return
     */
    public static int knapsackFull(int num, int capacity, int[] weights, int[] values) {
        int[] dp = new int[capacity + 1];
        // 初始化状态为-inf
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 0; i < num; i++) {
            for (int j = capacity; j >= weights[i]; j--) {
                if (dp[j - weights[i]] == Integer.MIN_VALUE) {
                    dp[j] = Integer.MIN_VALUE;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
            // 打印
            printDp(dp, capacity);
        }
        return dp[capacity];
    }

    /**
     * 多重背包可行性
     * http://www.cielyang.com/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98%E5%AD%A6%E4%B9%A0%E4%B9%8B%E5%A4%9A%E9%87%8D%E8%83%8C%E5%8C%85/
     * https://blog.csdn.net/ctsas/article/details/53708712
     *
     * @param num
     * @param capacity
     * @param weights
     * @param MI
     */
    public static void CanMultiplePack(int num, int capacity, int[] weights, int[] MI) {
        int[][] dp = new int[num + 1][capacity + 1];
        Arrays.fill(dp[0], -1);
        dp[0][0] = 0;
        for (int i = 1; i <= num; i++) {
            for(int j = 0; j <= capacity;j++) {
                if (dp[i - 1][j] >= 0) {
                    dp[i][j] = MI[i];
                } else {
                    dp[i][j] = -1;
                }
            }

            for(int j = 0; j <= capacity - weights[i];j++) {
                if (dp[i][j] >= 0) {
                    dp[i][j+weights[i]] = Math.max(dp[i][j+weights[i]], dp[i][j] - 1);
                }
            }
        }

        for (int i = 0; i <= num;i++) {
            for(int j = 0; j <= capacity;j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("");
        }
    }


    public static void main(String[] args) {
//        int num = 5;
//        int capacity = 10;
//        int[] weights = new int[]{2, 2, 6, 5, 4};
//        int[] values = new int[]{6, 3, 5, 4, 6};
        int num = 5;
        int capacity = 12;
        int[] weights = new int[]{5, 4, 7, 2, 6};
        int[] values = new int[]{12, 3, 10, 3, 6};
        int[] MI = new int[]{100, 100, 100, 100, 100};
//        int maxValue = knapsack2(num, capacity, weights, values);
//        int maxValue = completeKnapsack(num, capacity, weights, values);
//        int maxValue = multipleKnapsack(num, capacity, weights, values, MI);
//        System.out.println("maxValue:" + maxValue);

        weights = new int[]{0, 23, 2, 6, 4, 7};
        MI = new int[]{0, 1, 1, 1, 1, 1};
        CanMultiplePack(num, capacity, weights, MI);
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
}
