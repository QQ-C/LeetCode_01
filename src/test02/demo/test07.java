package test02.demo;

public class test07 {
    public static void main(String[] args) {

    }
}

class ListNode07 {
    int val;
    ListNode07 next;

    ListNode07() {
    }

    ListNode07(int val) {
        this.val = val;
    }
}

//***********反转链表
//***********双指针法
class Solution07 {
    public ListNode07 reverseList(ListNode07 head) {
        ListNode07 pre = null;
        ListNode07 cur = head;
        ListNode07 temp = null;  //保存cur下一个结点

        while (cur != null) {
            temp = cur.next;   // 保存一下 cur的下一个节点，因为接下来要改变cur->next
            cur.next = pre;  // 翻转操作
            // 更新pre 和 cur指针
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}

//***********递归法
//class Solution07{
//    public ListNode07 reverse(ListNode07 pre, ListNode07 cur){
//        if(cur ==null){
//            return pre;
//        }
//        ListNode07 temp = cur.next;
//        cur.next=pre;
//        // 可以和双指针法的代码进行对比，如下递归的写法，其实就是做了这两步
//        // pre = cur;
//        // cur = temp;
//        return reverse(cur,temp);
//    }
//    ListNode07 reverseList(ListNode07 head){
//        // 和双指针法初始化是一样的逻辑
//        // ListNode* cur = head;
//        // ListNode* pre = NULL;
//        return reverse(null,head);
//    }
//}


//************从后往前反转指针指向
//class Solution07{
//    public ListNode07 reverseList(ListNode07 head){
//        //边缘条件判断
//        if(head==null){
//            return null;
//        }
//        if(head.next==null){
//            return head;
//        }
//        // 递归调用，翻转第二个节点开始往后的链表
//        ListNode07 last = reverseList(head.next);
//        // 翻转头节点与第二个节点的指向
//        head.next.next=head;                           (不懂)
//        // 此时的 head 节点为尾节点，next 需要指向 NULL
//        head.next = null;
//        return last;
//    }
//}

// *****************两两交换链表中的结点
class Solution071 {
    public ListNode07 swapPairs(ListNode07 head) {
        // ***********递归解法
//        if(head==null || head.next==null){
//            return head;
//        }
//        ListNode07 nextNode=head.next;
//        ListNode07 newNode = swapPairs(nextNode.next);
//        nextNode.next=head;
//        head.next=newNode;
//        return newNode;

        //**************** 虚拟头节点
        ListNode07 dummyNode = new ListNode07(0);
        dummyNode.next = head;
        ListNode07 cur = dummyNode;

        while (cur.next != null && cur.next.next != null) {
            ListNode07 temp = cur.next;
            ListNode07 temp1 = cur.next.next.next;

            cur.next = cur.next.next;
            cur.next.next = temp;
            cur.next.next.next = temp1;

            cur = cur.next.next;
        }
        return dummyNode.next;
    }
}
//检测环形链表
//首先检测是否有环  快慢指针相遇，即是有环
//然后检测环的入口  相遇处指针与头指针 同时移动 相遇处即为环的入口

class Solution072 {
    public ListNode07 detectCycle(ListNode07 head) {
        ListNode07 fast = head;
        ListNode07 slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇，此时从head 和 相遇点，同时查找直至相遇
            if (slow == fast) {
                ListNode07 index1 = fast;
                ListNode07 index2 = head;
                while(index1!=index2){
                    index1=index1.next;
                    index2=index2.next;
                }
                return index2;
            }
        }
        return null;
    }
}












