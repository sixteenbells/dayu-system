package future;

import com.zhc.lt.base.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/2 4:38 PM
 * @description : 二叉树的右视图
 */
public class TreeRightView_199 {

    /**
     * 单队列
     *
     * @param root
     * @return
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> resultList = new LinkedList<>();
        if (root == null) {
            return resultList;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);

        while (!queue.isEmpty()) {
            LinkedList<TreeNode> tempQueue = new LinkedList<>();
            resultList.add(queue.peek().val);
            while (!queue.isEmpty()) {
                TreeNode pNode = queue.removeLast();
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

    /**
     * 双队列，不推荐
     *
     * @param root
     * @return
     */
    public static List<Integer> rightSideView2(TreeNode root) {
        List<Integer> resultList = new LinkedList<>();
        if (root == null) {
            return resultList;
        }
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();

        queue1.push(root);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            LinkedList<TreeNode> currentQueue = !queue1.isEmpty() ? queue1 : queue2;
            LinkedList<TreeNode> otherQueue = queue1.isEmpty() ? queue1 : queue2;
            resultList.add(currentQueue.peek().val);
            while (!currentQueue.isEmpty()) {
                TreeNode pNode = currentQueue.removeLast();
                if (pNode.left != null) {
                    otherQueue.push(pNode.left);
                }
                if (pNode.right != null) {
                    otherQueue.push(pNode.right);
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{1, 2, null, 4, 5};
        TreeNode p = TreeNode.buildTree(array);
        List<Integer> result = rightSideView(p);
        System.out.println(result.toString());
    }
}
