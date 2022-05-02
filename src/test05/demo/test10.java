package test05.demo;

import javax.swing.*;

public class test10 {
    public static void main(String[] args) {
        Solution solution =new Solution();
    }
}
class Solution{
    //移除元素
     int removeElement(int[] nums, int val) {
        // 快慢指针
        int fastIndex;
        int slowIndex=0;
        for (fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;

    }
    //反转字符串
    void reverseString(char[] s){
        int left=0;
        int right=s.length-1;
        while(left<right){
            char temp=s[left];
            s[left]=s[right];
            s[right]=temp;
            left++;
            right--;
        }
    }
    //替换空格
    String replaceSpace(String s){
         if(s==null||s.length()==0){
             return s;
         }
         //扩充空间 空格数量的2倍
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==' '){
                str.append("  ");
            }
        }
        if(str.length()==0){
            return s;
        }
        //有空格，定义两个指针
        int left=s.length()-1;
        s+=str.toString();
        int right=s.length()-1;
        char[] chars=s.toCharArray();
        while(left>=0){
            if(chars[left]==' '){
                chars[right--]='0';
                chars[right--]='2';
                chars[right]='%';
            }else{
                chars[right]=chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }
    //翻转字符串里面的单词
    /**
     * 不使用Java内置方法实现
     * 1.去除首尾以及中间多余空格
     * 2.反转整个字符串
     * 3.反转各个单词
     */
    public String reverseWords(String s){
        StringBuilder sb = removeSpace(s);
        reverseString(sb,0,sb.length()-1);
        reverseEachWord(sb);
        return sb.toString();
    }
    private StringBuilder removeSpace(String s){
        int start=0;
        int end=s.length()-1;
        while(s.charAt(start)==' ') start++;
        while(s.charAt(end)==' ') end--;
        StringBuilder str = new StringBuilder();
        while(start<=end){
            char c= s.charAt(start);
            if(c!=' '||str.charAt(str.length()-1)!=' '){
                str.append(c);
            }
            start++;
        }
        return str;
    }
    //反转指定区间的字符串
    private void reverseString(StringBuilder sb,int start,int end){
        while(start<end){
            char temp = sb.charAt(start);
            sb.setCharAt(start,sb.charAt(end));
            sb.setCharAt(end,temp);
            start++;
            end--;
        }
    }
    //反转每一个单词
    private void reverseEachWord(StringBuilder sb){
        int start=0;
        int end=1;
        int n=sb.length();
        while(start<n){
            while(end<n&&sb.charAt(end)!=' '){
                end++;
            }
            reverseString(sb,start,end-1);
            start=end+1;
            end=start+1;
        }
    }

    public class ListNode{
        int val;
        ListNode next;
        ListNode(){};
        ListNode(int val){
            this.val=val;
        }
        ListNode(int val,ListNode next){
            this.val=val;
            this.next=next;
        }
    }
    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode cur=head;
        ListNode temp=null;
        while(cur!= null){
            temp=cur.next;
            cur.next=prev;
            prev=cur;
            cur=temp;
        }
        return prev;
    }
}

