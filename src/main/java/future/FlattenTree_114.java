package future;

import com.zhc.lt.base.TreeNode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/13 10:47 PM
 * @description :
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
 * 1.将左子树插入到右子树的地方
 * 2.将原来的右子树接到左子树的最右边节点
 * 3.考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
 */
public class FlattenTree_114 {

    public static void flatten(TreeNode root) {
        if (root == null) {
            return ;
        }

        if (root.left == null) {
            flatten(root.right);
            return ;
        }
        TreeNode pNode = root.left;
        while(pNode.right != null) {
            pNode = pNode.right;
        }

        pNode.right = root.right;
        root.right = root.left;
        flatten(root.right);
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{1, 2, 5, 3, 4, null, 6};
        TreeNode root = TreeNode.buildTree(array);
        flatten(root);

        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }
}
