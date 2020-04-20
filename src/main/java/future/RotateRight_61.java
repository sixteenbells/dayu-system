package future;

import com.zhc.lt.base.ListNode;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/22 11:01 PM
 * @description :选择链表
 * https://leetcode-cn.com/problems/rotate-list/solution/ji-bai-liao-91de-javayong-hu-qing-xi-yi-dong-by-2/
 */
public class RotateRight_61 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        // 链表长度
        int len = 0;
        ListNode pNode = head;
        while (pNode != null) {
            len++;
            pNode = pNode.next;
        }

        k %= len;
        if (k == 0) {
            return head;
        }
        // 快慢指针
        // 1->2->3->4->5，此时fast在3，slow在1
        ListNode slow = head;
        ListNode fast = head;
        while (k > 0) {
            k--;
            fast = fast.next;
        }
        // 此时fast在5、slow在3
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 记录新的头节点
        ListNode newHead = slow.next;
        // 后一段指向前一段的开头
        fast.next = head;
        // 中间断开
        slow.next = null;
        return newHead;
    }

    public static void main(String[] args) {

    }
}
