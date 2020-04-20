package future;

import com.zhc.lt.base.ListNode;

import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/19 10:59 PM
 * @description :
 */
public class MergeTwoList_21 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode pNode = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pNode.next = l1;
                l1 = l1.next;
            } else {
               pNode.next = l2;
               l2 = l2.next;
            }
            pNode = pNode.next;
        }
        pNode.next = l1 == null ? l2 : l1;
        return preHead.next;
    }

    public static void main(String[] args) {

    }
}
