package future;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/8 11:28 PM
 * @description :买卖股票的最佳时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class MaxProfit_121 {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int length = prices.length;
        int[] dp = new int[length];

        // 初始化，第一行和对角线为0
        Arrays.fill(dp, 0);

        int max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(dp[j - 1], prices[j] - prices[i]);
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }

    /**
     * 模板，k=1
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     * <p>
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/
     *
     * @param prices
     * @return
     */
    public static int maxProfit_k_1(int[] prices) {
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            // 状态转换：当前没有持有，可能是昨天就没有持有，今天保持；或者昨天持有，今天卖了
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // 状态转换：当前持有，可能是昨天持有，今天保持；状态没有持有，今天买入（状态没有持有就没有收益，为0）
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    /**
     * k=n的情况
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     *
     * @param prices
     * @return
     */
    public static int maxProfit_k_inf(int[] prices) {
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int temp = dp_i_0;
            // 当前不持有
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // 当前持有
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

    /**
     * k=n，并且每次sell后必须等一天才能交易
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][0], dp[i-2][0] - prices[i])
     *
     * @param prices
     * @return
     */
    public static int maxProfit_with_cool(int[] prices) {
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        // 代表dp[i-2][0]
        int dp_pre_0 = 0;
        for (int i = 0; i < prices.length; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }

    /**
     * 每次交易需要手续费
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][0], dp[i-2][0] - prices[i] - fee)
     * 解释：相当于买入股票的价格升高了。
     * 在第一个式子里减也是一样的，相当于卖出股票的价格减小了。
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit_with_fee(int[] prices, int fee) {
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int temp = dp_i_0;
            // 当前不持有
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // 当前持有
            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }


    public static void main(String[] args) throws Exception {
        int[] array = new int[]{1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(array));
        System.out.println(maxProfit_k_1(array));
        System.out.println(maxProfit_k_inf(array));
        System.out.println(maxProfit_with_cool(array));
        System.out.println(maxProfit_with_fee(array, 2));
    }
}
