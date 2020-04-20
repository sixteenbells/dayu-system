package others;

import com.zhc.lt.base.TreeNode;

import java.util.LinkedList;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/12/9 5:29 PM
 * @description :
 */
public class TraverseTree {

    /**
     * 前序遍历,不推荐记忆
     *
     * @param root
     */
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            System.out.println(node.val);
            if (node.right != null) {
                queue.push(node.right);
            }
            if (node.left != null) {
                queue.push(node.left);
            }
        }
    }

    /**
     * 前序遍历，记忆
     *
     * @param root
     */
    public static void preOrder2(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            while (pNode != null) {
                System.out.println(pNode.val);
                stack.push(pNode);
                pNode = pNode.left;
            }
            pNode = stack.pop();
            pNode = pNode.right;
        }

    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            while (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            }
            pNode = stack.pop();
            System.out.println(pNode.val);
            pNode = pNode.right;
        }

    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        // 遍历节点
        TreeNode pNode = root;
        // 前一个输出节点
        TreeNode prev = pNode;
        while (pNode != null || !stack.isEmpty()) {
            while (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            }
            if (!stack.isEmpty()) {
                TreeNode right = stack.peek().right;
                // 右节点为空，或者已经遍历过了，则输出父节点
                if (right == null || right == prev) {
                    pNode = stack.pop();
                    System.out.println(pNode.val);
                    prev = pNode;
                    // 让遍历节点为空，继续弹出
                    pNode = null;
                } else {
                    // 否则，继续入栈右边节点
                    pNode = right;
                }
            }
        }
    }

    /**
     * 广度遍历
     *
     * @param root
     */
    public static void breathTraver(TreeNode root) {
        if (root == null) {
            return ;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode pNode = queue.remove();
            System.out.println(pNode.val);
            if (pNode.left != null) {
                queue.offer(pNode.left);
            }
            if (pNode.right != null) {
                queue.offer(pNode.right);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{5, 3, 6, 2, 4, 9, 7};
        TreeNode root = TreeNode.buildTree(array);
//        postOrder(root);
        breathTraver(root);
    }

}
