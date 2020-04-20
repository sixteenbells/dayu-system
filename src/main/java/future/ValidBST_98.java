package future;

import com.zhc.lt.base.TreeNode;

import java.util.LinkedList;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/12 6:55 PM
 * @description : 验证是否是二叉搜索树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 核心：二叉搜索树中序遍历是有序的
 */
public class ValidBST_98 {

    public static boolean isValidBST(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        Integer preNumber = null;
        while(pNode != null || !stack.isEmpty()) {
            while(pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            }
            pNode = stack.pop();
            if (preNumber != null && pNode.val <= preNumber) {
                return false;
            }
            preNumber = pNode.val;
            System.out.println(pNode.val);
            pNode = pNode.right;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{1, 1};
        TreeNode root = TreeNode.buildTree(array);
        System.out.println(isValidBST(root));
    }
}
