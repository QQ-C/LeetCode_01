package test05.demo;

import java.util.Stack;

/**
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。  判断输出栈是否为空，不空则直接输出；如果为空，则把输入栈的先输出到输出栈，再输出
 * peek() -- 返回队列首部的元素。 判断输出栈是否为空，不空则直接输出；如果为空，则把输入栈的先输出到输出栈，再输出
 * empty() -- 返回队列是否为空。 两个都为空才可以
 */

public class test11 {
    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        obj.pop();
        obj.push(3);
        obj.push(4);
        obj.peek();
        obj.pop();
        obj.pop();
        obj.pop();
        obj.empty();
    }
}
class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();  //负责进栈
        stackOut = new Stack<>();  //负责出栈
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        dumpstackIn();
        return stackOut.pop();
    }

    public int peek() {
        dumpstackIn();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty()&&stackOut.isEmpty();
    }
    // 如果stackOut为空，那么将stackIn中的元素全部放到stackOut中
    private void dumpstackIn(){
        if(!stackOut.isEmpty()) return; //如果空的话，继续下一步；如果不空 ，则停止
        while(!stackIn.isEmpty()){    //不空，继续循环
            stackOut.push(stackIn.pop());
        }
    }
}