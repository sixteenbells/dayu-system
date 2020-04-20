package future;

import com.zhc.lt.base.Node;
import com.zhc.lt.base.TreeNode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/2 2:54 PM
 * @description :填充任意二叉树每个节点的下一个右侧节点指针
 */
public class FullNextNode_116 {
    /**
     * 当前层的最左节点
     */
    public static Node head;
    /**
     * 下一层的节点遍历时的前一个节点
     */
    public static Node preNode;

    public static Node connect(Node root) {
        head = root;
        while (head != null) {
            // pNode：当前层的遍历节点
            Node pNode = head;
            head = null;
            preNode = null;
            while (pNode != null) {
                connectChildren(pNode.left);
                connectChildren(pNode.right);
                pNode = pNode.next;
            }
        }
        return root;
    }

    public static void connectChildren(Node node) {
        if (node != null) {
            head = head == null ? node : head;
            if (preNode != null) {
                preNode.next = node;
            }
            preNode = node;
        }
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        TreeNode p = TreeNode.buildTree(array);
    }
}
