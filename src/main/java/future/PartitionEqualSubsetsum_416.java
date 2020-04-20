package future;

import java.util.Arrays;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/8 5:15 PM
 * @description : 分隔等和子集
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
 */
public class PartitionEqualSubsetsum_416 {

    /**
     * 也可以定义dp[i][j]在0...i元素之间，能否相加大于j
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int N = nums.length;
        int[] dp = new int[target + 1];
        Arrays.fill(dp, 0);

        for (int i = 0; i < N; i++) {
            for (int j = target; j >= nums[i]; j--) {
                if (nums[i] > target) {
                    return false;
                }
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] == target ? true : false;
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

    public static void main(String[] args) throws Exception {
        int[] array = new int[]{1, 2, 3, 5};
        System.out.println(canPartition(array));
    }
}
