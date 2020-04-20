package future;

import com.zhc.lt.base.ListNode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/17 11:24 PM
 * @description :反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
 */
public class ReverseList_206 {
    // -------------------------------------------------------------------------------
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        return reverse(head);
    }

    /**
     * 反转链表模板
     *
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    // -------------------------------------------------------------------------------

    public static ListNode successor;

    /**
     * 反转前n个节点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    // -------------------------------------------------------------------------------

    /**
     * 反转从m到n的链表
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        ListNode node9 = new ListNode(9);
//        ListNode node10 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

//        ListNode resultNode = reverse(node1);
//        ListNode resultNode = reverseN(node1, 3);
        ListNode resultNode = reverseBetween(node1, 3, 5);

        while (resultNode != null) {
            System.out.print(resultNode.val + "\t");
            resultNode = resultNode.next;
        }
    }
}
