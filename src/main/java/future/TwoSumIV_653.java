package future;


import com.zhc.lt.base.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/12/9 3:50 PM
 * @description :
 */
public class TwoSumIV_653 {

    /**
     * Using HashSet[Accepted]
     * Time complexity : O(n)
     * Space complexity : O(n)
     * @param root
     * @param k
     * @return
     */
    public static boolean findTarget(TreeNode root, int k) {
        return recursiveFind(root, k, new HashSet<Integer>());
    }

    public static boolean recursiveFind(TreeNode node, int k, Set<Integer> nodeSet) {
        if (node == null) {
            return false;
        }
        if (nodeSet.contains(k - node.val)) {
            return true;
        }
        nodeSet.add(node.val);
        return recursiveFind(node.left, k, nodeSet) || recursiveFind(node.right, k, nodeSet);
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{5, 3, 6, 2, 4, null, 7};
        TreeNode root = TreeNode.buildTree(array);
        boolean result = findTarget(root, 90);
        System.out.println(result);
    }
}
