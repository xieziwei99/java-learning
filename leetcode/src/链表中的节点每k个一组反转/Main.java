package 链表中的节点每k个一组反转;

/**
 * @author xzw
 * 2021-03-11
 */
public class Main {
    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        listNode.val = 1;
        ListNode p = listNode;
        for (int i = 0; i < 9; i++) {
            ListNode node = new ListNode();
            node.val = i + 2;
            p.next = node;
            p = node;
        }
        System.out.println(listNode);
        System.out.println(new Main().reverseKGroup(listNode, 1));
        System.out.println(new Main().reverseKGroup(listNode, 2));
        System.out.println(new Main().reverseKGroup(null, 2));
        System.out.println(new Main().reverseKGroup(new ListNode(2), 2));
    }

    /*
    1  ->  2 -> 3 -> 4 ->  5 ->  6 ->  7 ->  8 ->  9    k=4
    head
    每次处理4个, 进行几次循环呢, 9 / 4 = 2 次循环即可
    考虑如何反转一个链表

    第1步
    pre = head; cur = head.next; temp = cur.next;
    1  ->  2 -> 3 -> 4
    head
    pre   cur   temp

    第2步
    cur.next = pre
    1  <-  2 -> 3 -> 4
    pre   cur   temp

    第3步
    pre = cur; cur = temp;
    1  <-  2 -> 3 -> 4
           pre  cur

    回到第2步, ... , 最终
    1  <-  2 <- 3 <- 4
                pre  cur
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode p = head;
        ListNode pNext = head;
        ListNode pPre = head;
        int cnt = 0;
        ListNode ret = head;
        while (true) {
            cnt++;
            try {
                for (int i = 0; i < k; i++) {
                    pNext = pNext.next;
                }
            } catch (Exception e) {
                break;
            }

            ListNode pre = p, cur = p.next, temp;
            while (cur.next != pNext) {
                temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            cur.next = pre;
            if (1 == cnt) {
                ret = cur;
            } else {
                pPre.next = cur;
                pPre = p;
            }
            p.next = pNext;
            p = pNext;
        }
        return ret;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val + " -> " + next;
    }
}