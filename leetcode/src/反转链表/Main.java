package 反转链表;

/**
 * @author xzw
 * 2021-03-13
 */
public class Main {

    public static void main(String[] args) {
        ListNode1 listNode = new ListNode1(1);
        ListNode1 p = listNode;
        for (int i = 0; i < 9; i++) {
            ListNode1 node = new ListNode1(i + 2);
            p.next = node;
            p = node;
        }
        System.out.println(listNode);
        System.out.println(new Main().ReverseList(listNode));
    }

    public ListNode1 ReverseList(ListNode1 head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode1 pre = head, cur = head.next, temp;
        while (cur.next != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        cur.next = pre;
        head.next = null;
        return cur;
    }
}

class ListNode1 {
    int val;
    ListNode1 next = null;

    ListNode1(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val + " -> " + next;
    }
}