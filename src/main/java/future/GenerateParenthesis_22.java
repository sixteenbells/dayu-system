package future;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/19 10:16 AM
 * @description : 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
 */
public class GenerateParenthesis_22 {
    public static List<String> resultList = new LinkedList<>();

    public static List<String> generateParenthesis(int n) {
        if (n == 0) {
            return resultList;
        }

        dfs("", n, n);
        return resultList;
    }

    public static void dfs(String curStr, int left, int right) {
        if (left == 0 && right == 0) {
            resultList.add(curStr);
            return;
        }
        // 剪枝
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left -1, right);
        }
        if (right > 0) {
            dfs(curStr + ")", left, right - 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(1).toString());
    }

}
