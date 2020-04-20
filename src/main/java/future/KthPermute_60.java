package future;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/11 11:44 PM
 * @description : 第k个排序
 * https://leetcode-cn.com/problems/permutation-sequence/
 * https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
 */
public class KthPermute_60 {

    public static boolean isContinue = true;
    public static int number = 0;
    public static boolean[] use;
    public static String result;

    public static String getPermutation(int n, int k) {
        use = new boolean[n + 1];
        backTrack(n, k, use, new StringBuffer());
        return result;
    }

    public static void backTrack(int n, int k, boolean[] use, StringBuffer permute) {
        if (permute.length() == n) {
            number++;
            if (number == k) {
                result = permute.toString();
                isContinue = false;
            }
            return;
        }

        for (int i = 1; i <= n && isContinue; i++) {
            if (use[i]) {
                continue;
            }
            use[i] = true;
            permute.append(i);
            backTrack(n, k, use, permute);
            use[i] = false;
            permute.deleteCharAt(permute.length() - 1);
        }

    }

    public static String getPermutation2(int n, int k) {
        use = new boolean[n + 1];
        return recursive(n, k, use);
    }

    public static String recursive(int n, int k, boolean[] use) {
        if (n <= 0) {
            return "";
        }

        int factorial = factorial(n - 1);
        int currentNumber = k == 0 ? 0 : (k / factorial) - ((k % factorial) > 0 ? 0 : 1);
        int i = 1;
        for (; i < use.length; i++) {
            if (use[i]) {
                continue;
            }
            currentNumber--;
            if (currentNumber < 0) {
                break;
            }
        }
        String number = String.valueOf(i);
        use[i] = true;
        String rightNumber = recursive(n - 1, k % factorial, use);
        return number + rightNumber;
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * 双链表模拟，直接取值，推荐
     *
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation3(int n, int k) {
        // 注意：相当于在 n 个数字的全排列中找到索引为 k - 1 的那个数，因此 k 先减 1
        k--;

        int[] factorial = new int[n];
        factorial[0] = 1;
        // 先算出所有的阶乘值
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // 因为要频繁做删除，使用链表
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        StringBuilder stringBuilder = new StringBuilder();

        // i 表示剩余的数字个数，初始化为 n - 1
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorial[i];
            stringBuilder.append(nums.remove(index));
            k %= factorial[i];
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        int n = 4;
        int k = 9;
//        System.out.println(getPermutation(n, k));
        System.out.println(getPermutation3(n, k));
    }
}
