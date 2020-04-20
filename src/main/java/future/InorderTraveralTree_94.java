package future;

import com.zhc.lt.base.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/1/12 6:39 PM
 * @description :二叉树的中序遍历
 */
public class InorderTraveralTree_94 {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while(pNode != null || !stack.isEmpty()) {
            while(pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            }
            pNode = stack.pop();
            result.add(pNode.val);
            pNode = pNode.right;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

    }
}
