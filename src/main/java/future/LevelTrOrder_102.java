package future;

import com.zhc.lt.base.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/2 5:10 PM
 * @description :二叉树的层次遍历
 */
public class LevelTrOrder_102 {

    /**
     * 迭代，单队列
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new LinkedList<>();
        if (root == null) {
            return resultList;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);

        while (!queue.isEmpty()) {
            LinkedList<TreeNode> tempQueue = new LinkedList<>();
            List<Integer> levelList = new LinkedList<>();
            resultList.add(levelList);
            while (!queue.isEmpty()) {
                TreeNode pNode = queue.removeLast();
                levelList.add(pNode.val);
                if (pNode.left != null) {
                    tempQueue.push(pNode.left);
                }
                if (pNode.right != null) {
                    tempQueue.push(pNode.right);
                }
            }
            queue = tempQueue;
        }
        return resultList;
    }

    public static List<List<Integer>> levels = new LinkedList<>();

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null) {
            return levels;
        }
        helper(root, 0);
        return levels;
    }

    public static void helper(TreeNode node, int level) {
        if (levels.size() == level) {
            levels.add(new LinkedList<>());
        }
        levels.get(level).add(node.val);

        if (node.left != null) {
            helper(node.left, level + 1);
        }
        if (node.right != null) {
            helper(node.right, level + 1);
        }
    }


    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{5, 3, 6, 2, 4, 9, 7};
        TreeNode root = TreeNode.buildTree(array);
        List<List<Integer>> resultList = levelOrder(root);
        System.out.println(resultList.toString());
    }
}
