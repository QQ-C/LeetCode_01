package test05.demo;

import java.util.Deque;
import java.util.LinkedList;

public class test15 {
    public static void main(String[] args) {
        Solution015 solution015=new Solution015();

        System.out.println(solution015.evalRPN(new String[]{"2","1","+","3","*"}));

    }
}
class Solution015{
    public int evalRPN(String[] tokens){
        Deque<Integer> que=new LinkedList();
        for(int i=0;i<tokens.length;i++){
            if("+".equals(tokens[i])) {  //  // leetcode 内置jdk的问题，不能使用==判断字符串是否相等
                que.push(que.pop()+que.pop());   // 注意 - 和/ 需要特殊处理
            }else if("-".equals(tokens[i])){
                que.push(-que.pop()+que.pop());
            }else if("*".equals(tokens[i])){
                que.push(que.pop() * que.pop());
            }else if ("/".equals(tokens[i])){
                int temp1 = que.pop();
                int temp2 = que.pop();
                que.push(temp2 / temp1);
            }else{
                que.push(Integer.valueOf(tokens[i]));
            }
        }
        return que.pop();


//        //方法二
//        Deque<Integer> st=new LinkedList();
//        for (int i = 0; i < tokens.length; i++) {
//            if (tokens[i] == "+" || tokens[i] == "-" || tokens[i] == "*" || tokens[i] == "/") {
//                int num1 = st.peek();
//                st.pop();
//                int num2 = st.peek();
//                st.pop();
//                if (tokens[i] == "+") st.push(num2 + num1);
//                if (tokens[i] == "-") st.push(num2 - num1);
//                if (tokens[i] == "*") st.push(num2 * num1);
//                if (tokens[i] == "/") st.push(num2 / num1);
//            } else {
//                st.push(Integer.valueOf(tokens[i]));
//            }
//        }
//        int result = st.peek();
//        st.pop(); // 把栈里最后一个元素弹出（其实不弹出也没事）
//        return result;
    }
}
