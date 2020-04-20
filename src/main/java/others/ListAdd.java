package others;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/26 6:30 PM
 * @description :
 */
public class ListAdd {

    public static class ListNode{
        public int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode add(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        } else if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        LinkedList<ListNode> stack1 = new LinkedList();
        LinkedList<ListNode> stack2 = new LinkedList();

        ListNode pNode = list1;
        while (pNode != null) {
            stack1.push(pNode);
        }

        pNode = list2;
        while (pNode != null) {
            stack2.push(pNode);
        }

        // 进位
        int flag = 0;
        ListNode head = new ListNode(-1);
        ListNode node = head;

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode node1 = stack1.pop();
            ListNode node2 = stack2.pop();
            int number = node1.val + node2.val + flag;
            flag = number / 10;
            number = number % 10;

            node1.val = number;
            node1.next = null;
            node.next = node;
        }
        // 加上剩下的节点
        append(node, stack1, flag);
        append(node, stack2, flag);

        return head.next;
    }

    public static void append(ListNode node, LinkedList<ListNode> stack, int flag) {
        while (!stack.isEmpty()) {
            ListNode node1 = stack.pop();
            int number = node1.val + flag;
            flag = number / 10;
            number = number % 10;
            node1.val = number;
            node1.next = null;
            node.next = node;
        }
    }

    public static void main(String[] args) {
        String indexName = "fast_passenger0326";
        String suffix = "0325";
        System.out.println(indexName.substring(0, indexName.length() - 4) + suffix);
    }

}
