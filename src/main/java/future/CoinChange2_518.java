package future;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/8 7:38 PM
 * @description :零钱兑换2
 * https://leetcode-cn.com/problems/coin-change-2/
 */
public class CoinChange2_518 {

    public static int change(int amount, int[] coins) {
        if (amount == 0 && coins.length == 1) {
            return 1;
        }

        int N = coins.length;
        int[] dp = new int[amount + 1];

        // 初始化
        Arrays.fill(dp, 0);
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
            printDp(dp, amount);
        }

        return dp[amount];
    }

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

    public static void main(String[] args) throws Exception {
        int amount = 10;
        int[] array = new int[]{10};
        System.out.println(change(amount, array));
    }

}
