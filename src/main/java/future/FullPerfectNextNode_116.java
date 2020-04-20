package future;

import com.zhc.lt.base.Node;
import com.zhc.lt.base.TreeNode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/2 2:54 PM
 * @description :填充完美二叉树每个节点的下一个右侧节点指针
 */
public class FullPerfectNextNode_116 {

    public static Node connect(Node root) {
        Node head = root;
        while (head != null) {
            Node pNode = head;
            head = pNode.left;
            if (head == null) {
                return root;
            }
            Node preNode = null;
            while(pNode != null) {
                if (preNode != null) {
                    preNode.next = pNode.left;
                }
                pNode.left.next = pNode.right;
                preNode = pNode.right;
                pNode = pNode.next;
            }
        }
        return root;
    }

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        TreeNode p = TreeNode.buildTree(array);
    }
}
