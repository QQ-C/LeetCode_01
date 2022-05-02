package test05.demo;

import java.util.ArrayDeque;

public class test14 {
    public static void main(String[] args) {
        Solution014 solution014=new Solution014();
        System.out.println(solution014.removeDuplicates("abbacd"));
    }
}

class Solution014{
    public String removeDuplicates(String s){
        //使用 Deque 作为堆栈
        //ArrayDeque会比LinkedList在除了删除元素这一点外会快一点
        //参考：https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist
        ArrayDeque<Character> deque= new ArrayDeque<>();
        char ch;
        for(int i=0;i<s.length();i++){
            ch=s.charAt(i);
            if(deque.isEmpty()||deque.peek()!=ch){
                deque.push(ch);
            }else{
                deque.pop();
            }
        }
        String str="";
        //剩余的元素即为不重复的元素
        while(!deque.isEmpty()){
            str=deque.pop()+str;
        }
        return str;


//        //拿字符串直接作为栈，省去了栈还要转为字符串的操作。
//        // 将 res 当做栈
//        StringBuffer res = new StringBuffer();
//        // top为 res 的长度
//        int top=-1;
//        for(int i=0;i<s.length();i++){
//            char c=s.charAt(i);
//            // 当 top > 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
//            if(top>=0&&res.charAt(top)==c){
//                res.deleteCharAt(top);
//                top--;
//            }else
//            // 否则，将该字符 入栈，同时top++
//            res.append(c);
//            top++;
//        }
//        return res.toString();

//        //双指针
//        char[] ch = s.toCharArray();
//        int fast=0;
//        int slow=0;
//        while(fast<s.length()){
//            // 直接用fast指针覆盖slow指针的值
//            ch[slow]=ch[fast];
//            // 遇到前后相同值的，就跳过，即slow指针后退一步，下次循环就可以直接被覆盖掉了
//            if(slow>0&&ch[slow]==ch[slow-1]){
//                slow--;
//            }else{
//                slow++;
//            }
//            fast++;
//        }
//        return new String(ch,0,slow);
    }
}