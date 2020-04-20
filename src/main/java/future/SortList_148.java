package future;

import com.zhc.lt.base.ListNode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/22 11:13 PM
 * @description :排序链表（归并排序链表）
 * https://www.cnblogs.com/morethink/p/8452914.html
 */
public class SortList_148 {

    /**
     * 归并排序链表
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = getMiddle(head);
        ListNode right = middle.next;
        middle.next = null;
        return merge(sortList(head), sortList(right));
    }

    /**
     * 获取中间节点
     *
     * @param head
     * @return
     */
    public static ListNode getMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 1->2->3->4->5: slow在3，fast在5
        // 1->2->3->4: slow在2，fast在3
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 合并两个链表
     *
     * @param left
     * @param right
     * @return
     */
    public static ListNode merge(ListNode left, ListNode right) {
        ListNode head = new ListNode(-1);
        ListNode pNode = head;
        while (left != null && right != null) {
            if (left.val < right.val) {
                pNode.next = left;
                pNode = pNode.next;
                left = left.next;
            } else {
                pNode.next = right;
                pNode = pNode.next;
                right = right.next;
            }
        }
        if (left != null) {
            pNode.next = left;
        }
        if (right != null) {
            pNode.next = right;
        }
        ListNode newHead = head.next;
        head.next = null;
        return newHead;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node4.next = node2;
        node2.next = node1;
        node1.next = node3;
        ListNode resultNode = sortList(node4);

        while (resultNode != null) {
            System.out.print(resultNode.val + "\t");
            resultNode = resultNode.next;
        }
    }

}
