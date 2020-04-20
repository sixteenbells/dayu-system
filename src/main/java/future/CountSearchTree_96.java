package future;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/12 6:00 PM
 * @description : 不同的二叉搜索树
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 * 核心点：长度为n的序列的不同二叉搜索树的个数与序列无关，只有长度有关
 * G[n] 代表n个数字的不同二叉搜索树的个数
 * f[i,n] 代表以i为跟节点时，n个数字的二叉搜索树的个数
 * f[i,n] = G[i - 1] * G[n - i] 即左子树的个数乘以右子树的个数
 * G[n] = f[1, n] + f[2, n] + ... + f[n, n]
 * 最终 G[n] = G[1 - 1]*G[n - 1] + G[2 - 1]*G[n - 2] + ... + G[n - 1]*G[n - n]
 */
public class CountSearchTree_96 {
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int j = 2; j <= n; j++) {
            for (int i = 1; i <= j; i++) {
                dp[j] += dp[i - 1] * dp[j - i];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) throws Exception {
        System.out.println(numTrees(4));
    }

}
