package future;

import com.zhc.lt.base.TreeNode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/13 10:32 PM
 * @description :相同的树
 * https://leetcode-cn.com/problems/same-tree/
 *
 */
public class SameTree_100 {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) throws Exception {
        Integer[] array1 = new Integer[]{1, 2, 3};
        TreeNode p = TreeNode.buildTree(array1);

        Integer[] array2 = new Integer[]{1, 2};
        TreeNode q = TreeNode.buildTree(array2);

        System.out.println(isSameTree(p, q));
    }
}
