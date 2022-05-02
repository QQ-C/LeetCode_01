package test05.demo;


import java.util.Deque;
import java.util.LinkedList;

public class test13 {
    public static void main(String[] args) {
        Solution013 solution013 = new Solution013();
        System.out.println(solution013.isValid("({[]}}"));
    }
}
class Solution013{
    public boolean isValid(String s){
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for(int i=0;i<s.length();i++){
            ch=s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if(ch=='('){
                deque.push(')');
            }else if(ch=='{'){
                deque.push('}');
            }else if(ch=='['){
                deque.push(']');
            }else if(deque.isEmpty() || deque.peek() !=ch){
                return false;
            }else{  //如果是右括号判断是否和栈顶元素匹配
                deque.pop();
            }
        }
        return deque.isEmpty();
    }
}