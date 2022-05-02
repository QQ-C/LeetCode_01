package test05.demo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class test12 {
    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(1);
    }
}
//使用两个Queue实现

/**
 * 　　add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
 * 　　remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
 * 　　element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
 * 　　offer       添加一个元素并返回true       如果队列已满，则返回false
 * 　　poll         移除并返问队列头部的元素    如果队列为空，则返回null
 * 　　peek       返回队列头部的元素             如果队列为空，则返回null
 * 　　put         添加一个元素                      如果队列满，则阻塞
 * 　　take        移除并返回队列头部的元素     如果队列为空，则阻塞
 */
//class MyStack {
//    Queue<Integer> queue1; // 和栈中保持一样元素的队列
//    Queue<Integer> queue2; // 辅助队列
//
//    public MyStack() {
//        queue1 = new LinkedList<>();
//        queue2 = new LinkedList<>();
//    }
//
//    public void push(int x) {
//        queue2.offer(x); // 先放在辅助队列中
//        while(!queue1.isEmpty()){
//            queue2.offer(queue1.poll());
//        }
//        Queue<Integer> queueTemp;
//        queueTemp=queue1;
//        queue1=queue2;
//        queue2=queueTemp; // 最后交换queue1和queue2，将元素都放到queue1中
//    }
//
//    public int pop() {
//        return queue1.poll();
//    }
//
//    public int top() {
//        return queue1.peek();
//    }
//
//    public boolean empty() {
//        return queue1.isEmpty();
//    }
//}

//// Deque 接口继承了 Queue 接口
//// 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
//class MyStack {
//    Deque<Integer> que1;  // 和栈中保持一样元素的队列
//    Deque<Integer> que2;  // 辅助队列
//    public MyStack() {
//        que1 = new ArrayDeque<>();
//        que2 = new ArrayDeque<>();
//    }
//
//    public void push(int x) {
//        que1.addLast(x);
//    }
//
//    public int pop() {
//        int size = que1.size();
//        size--;
//        // 将 que1 导入 que2 ，但留下最后一个值
//        while(size-->0){
//            que2.addLast(que1.peekFirst());
//            que1.pollFirst();
//        }
//        int res = que1.pollFirst();
//        // 将 que2 对象的引用赋给了 que1 ，此时 que1，que2 指向同一个队列
//        que1=que2;
//        // 如果直接操作 que2，que1 也会受到影响，所以为 que2 分配一个新的空间
//        que2=new ArrayDeque<>();
//        return res;
//    }
//
//    public int top() {
//        return que1.peekLast();
//    }
//
//    public boolean empty() {
//        return que1.isEmpty();
//    }
//}

////优化，使用一个 Deque 实现
//// Deque 接口继承了 Queue 接口
//// 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
class MyStack {
    Deque<Integer> que1;
    public MyStack() {
        que1 = new ArrayDeque<>();
    }

    public void push(int x) {
        que1.addLast(x);
    }

    public int pop() {
        int size = que1.size();
        size--;
        while(size-->0){
            que1.addLast(que1.peekFirst());
            que1.pollFirst();
        }
        int res=que1.pollFirst();
        return res;
    }

    public int top() {
        return que1.peekLast();
    }

    public boolean empty() {
        return que1.isEmpty();
    }
}