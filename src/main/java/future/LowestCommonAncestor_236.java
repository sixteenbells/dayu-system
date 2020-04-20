package future;

import com.zhc.lt.base.TreeNode;

import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/2 9:23 PM
 * @description :二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/java-dfs-jian-zhi-9ms9244-by-lava-4/
 */
public class LowestCommonAncestor_236 {

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if (left != null && left != q && left != q) {
            return left;
        }
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeNode.buildTree(array);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        TreeNode node = lowestCommonAncestor(root, p, q);
        System.out.println(node.val);
    }
}
