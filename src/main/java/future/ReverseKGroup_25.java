package future;

import com.zhc.lt.base.ListNode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/17 11:49 PM
 * @description : K 个一组翻转链表
 * 参考：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
 */
public class ReverseKGroup_25 {
    // 反转时的后继节点
    public static ListNode successor;
    // 反转后，后继节点的前一个节点
    public static ListNode preNode;
    public static int K;

    public static ListNode reverseKGroup(ListNode head, int k) {
        K = k;
        ListNode pNode = head;
        head = reverseN(pNode, k);
        pNode = successor;
        while (pNode != null) {
            ListNode temPreNode = preNode;
            ListNode subHead = reverseN(pNode, k);
            temPreNode.next = subHead;
            pNode = successor;
        }
        return head;
    }

    public static ListNode reverseN(ListNode head, int n) {
        if (head == null) {
            successor = null;
            return null;
        }
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        if (last != null) {
            head.next.next = head;
            head.next = successor;
            preNode = head;
            return last;
        } else {
            if (n < K) {
                return null;
            } else {
                return head;
            }
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(6);
//        ListNode node7 = new ListNode(7);
//        ListNode node8 = new ListNode(8);
//        ListNode node9 = new ListNode(9);
//        ListNode node10 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
//        node7.next = node8;
//        node8.next = node9;

//        ListNode resultNode = reverse(node1);
//        ListNode resultNode = reverseN(node1, 3);
        ListNode resultNode = reverseKGroup(node1, 3);

        while (resultNode != null) {
            System.out.print(resultNode.val + "\t");
            resultNode = resultNode.next;
        }
    }

}
