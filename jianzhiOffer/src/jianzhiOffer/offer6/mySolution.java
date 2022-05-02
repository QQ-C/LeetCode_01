package jianzhiOffer.offer6;

import java.util.*;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

public class mySolution {
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        Stack<Integer> st = new Stack<>();
        while (head.next != null) {
            st.push(head.val);
            head = head.next;
        }
        st.push(head.val);
        int length=st.size();
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = st.pop();
        }
        return result;
    }
}

class mySolution_1 {
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ArrayList<Integer> st = new ArrayList<>();
        while (head.next != null) {
            st.add(head.val);
            head = head.next;
        }
        st.add(head.val);
        Collections.reverse(st);
        int len=st.size();
        int[] result=new int[len];
        for (int i = 0; i < len; i++) {
            result[i]=st.get(i);
        }
        return result;
    }
}