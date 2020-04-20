package future;

import java.util.Arrays;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/8 7:44 PM
 * @description :
 */
public class CoinChange_322 {

    public static int coinChange(int[] coins, int amount) {
        if (amount < 0 || coins == null || (coins.length == 0 && amount > 0)) {
            return -1;
        }

        int N = coins.length;
        int[] dp = new int[amount + 1];

        // 初始化
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
            printDp(dp, amount);
        }

        return dp[amount] == Integer.MAX_VALUE / 2 ? -1 : dp[amount];
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
        int amount = 11;
        int[] array = new int[]{1, 2, 5};
        System.out.println(coinChange(array, amount));
    }
}
