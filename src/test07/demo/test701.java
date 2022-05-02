package test07.demo;

import java.io.*;
import java.util.*;


class ListNode{
    int value;
    ListNode next;
    public ListNode(){

    }
    public ListNode(int value, ListNode next){
        this.value = value;
        this.next = next;
    }
}

public class test701 {
    public static void main(String[] args) throws IOException{
//        withArraysAPI();

        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int num = scan.nextInt();
            ListNode header = new ListNode();
            for (int i=0; i< num;i++) {
                int value = scan.nextInt();
                ListNode node = new ListNode(value, header.next);
                header.next = node;
            }
            int target = scan.nextInt();
            for (int i=0; i<target; i++) {
                header = header.next;
            }
            System.out.println(header.value);
        }
    }

    /**
     * 输入
     * 提取第一个数值
     * 依次读取每个字符串，存到一个字符数组中
     * 排序
     * 输出
     */
    public static void withArraysAPI() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        String[] ss = new String[n];
        for(int i=0;i<n;i++){
            ss[i]=br.readLine();
        }
        br.close();
        Arrays.sort(ss);
        for(int i=0;i<n;i++){
            System.out.println(ss[i]);
        }
    }
}






