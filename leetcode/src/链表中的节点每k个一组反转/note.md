题目：

链表中的节点每k个一组反转

将给出的链表中的节点每k个一组翻转，返回翻转后的链表如果链表中的节点数不是k的倍数，将最后剩下的节点保持原样你不能更改节点中的值，只能更改节点本身。
要求空间复杂度O（1）

例如：
给定的链表是  1→2→3→4→5  

对于k=2，你应该返回 2→1→4→3→5

对于k=3，你应该返回 3→2→1→4→5



思路：

链表的反转，需要注意的是每K个一组之间的衔接

```中文
第1次反转之后的指针位置

1 <- 2 <- 3 <- 4    5 -> 6 -> 7 -> 8 -> 9 -> 10        k=4
p
pPre                pNext                   
         pre  cur
               ↑
          返回值的起始位置
这时我们让p所指节点指向pNext, 然后让p和pNext后移构成下一轮循环的开始
像这样
5
↑
1 <- 2 <- 3 <- 4    5 -> 6 -> 7 -> 8 -> 9 -> 10        k=4
pPre                p                   pNext
                    pre  cur
                    
第2次反转结束之后
5                   9
↑                   ↑
1 <- 2 <- 3 <- 4    5 <- 6 <- 7 <- 8    9 -> 10        k=4
pPre                p                   pNext
                              pre  cur
这里是关键点, 我们先前让1号节点指向了5号节点, 然而1号节点应该指向下一组K个数的起始点, 即当前的cur节点, 于是我们要将预留的pPer指向cur, 
然后后移指针开启下一轮循环, 像这样
8                   9
↑                   ↑
1 <- 2 <- 3 <- 4    5 <- 6 <- 7 <- 8    9 -> 10        k=4
                    pPre                p                   pNext
                                        pre  cur
循环                                        
```



代码

```java
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
```