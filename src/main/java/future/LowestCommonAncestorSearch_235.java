package future;

import com.zhc.lt.base.TreeNode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/2 9:23 PM
 * @description :二叉搜索树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/javadi-gui-san-xing-dai-ma-by-get996/
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solution/javaji-bai-975de-yong-hu-by-bage111/
 */
public class LowestCommonAncestorSearch_235 {

    /**
     * 1.根节点的值小于两个节点的值，则在右子树找
     * 2.根节点的值大于两个节点的值，则在左子树找
     * 3.否则返回根节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
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
