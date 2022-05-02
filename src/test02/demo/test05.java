package test02.demo;


public class test05 {
    public static void main(String[] args) {
        Solution051 solution051 = new Solution051();
        ListNode nodeSta = new ListNode(0);    //创建首节点
        ListNode nextNode;                     //声明一个变量用来在移动过程中指向当前节点
        nextNode=nodeSta;                      //指向首节点

        //创建链表
        for(int i=1;i<3;i++){
            ListNode node = new ListNode(i);  //生成新的节点
            nextNode.next=node;               //把新节点连起来
            nextNode=nextNode.next;           //当前节点往后移动    //地址赋值给了nextNode
        } //当for循环完成之后 nextNode指向最后一个节点，
        nextNode=nodeSta;                     //重新赋值让它指向首节点
        print(nextNode);                      //打印输出

        solution051.removeElements(nextNode,5);

        print(nextNode);                      //打印输出

    }

    //打印输出方法
    static void print(ListNode listNoed){
        //创建链表节点
        while(listNoed!=null){
            System.out.print(listNoed.val);
            listNoed=listNoed.next;
        }
        System.out.println();
    }
}
/**
 * 添加虚节点方式
 * 时间复杂度 O(n)
 * 空间复杂度 O(1)
 */
class Solution051 {
    public ListNode removeElements(ListNode head, int val) {

        //*****递归
//        if(head==null){
//            return head;
//        }
//        head.next = removeElements(head.next,val);
//        return head.val ==val ? head.next:head;

        //******头节点单独处理
//        while (head!=null&&head.val==val){
//            head=head.next;
//        }
//        if(head==null){
//            return head;
//        }
//        ListNode prev = head;
//        while(prev.next!=null){ //确保当前结点后还有结点
//            if (prev.next.val==val){
//                prev.next=prev.next.next;
//            }else{
//                prev=prev.next;
//            }
//        }
//        return head;

        //******添加一个虚拟头节点
        ListNode dummyNode=new ListNode(val-1);    //创建一个虚拟头结点
        dummyNode.next=head;
        ListNode prev = dummyNode;
        while(prev.next!=null){ //确保当前结点后还有结点
            if (prev.next.val==val){
                prev.next=prev.next.next;
            }else{
                prev = prev.next;
            }
        }
        return dummyNode.next;
    }
}
//java 链表
class ListNode {     //类名 ：Java类就是一种自定义的数据结构
    int val;         //数据 ：节点数据
    ListNode next;     //对象 ：引用下一个节点对象。在Java中没有指针的概念，Java中的引用和C语言的指针类似

    ListNode() {}
    ListNode(int val) { this.val = val; }    //构造方法 ：构造方法和类名相同    //把接收的参数赋值给当前类的val变量
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


