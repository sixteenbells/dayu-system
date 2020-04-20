package future;

import com.zhc.lt.base.ListNode;


/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/17 8:54 PM
 * @description : 1->1->2->2->3->4  =>  3->4 去除所有重复的节点
 */
public class RemoveDuplicatNodes {
    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode preHead = newHead;
        while (preHead.next != null) {
            boolean flag = false;
            ListNode pNode = preHead.next;
            int value = pNode.val;
            pNode = pNode.next;
            while (pNode != null && pNode.val == value) {
                flag = true;
                pNode = pNode.next;
            }
            if (flag) {
                preHead.next = pNode;
            } else {
                preHead = preHead.next;
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(4);
        ListNode node8 = new ListNode(5);
        ListNode node9 = new ListNode(6);
//        ListNode node10 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
//        node9.next = node10;

        ListNode resultNode = removeDuplicateNodes(node1);

        while (resultNode != null) {
            System.out.println(resultNode.val);
            resultNode = resultNode.next;
        }
    }

}
