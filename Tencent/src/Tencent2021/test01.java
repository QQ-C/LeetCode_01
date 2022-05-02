package Tencent2021;

/**
 * 给出一个只包含 0 和 1 的 01 串 s ，下标从 1 开始，设第 i 位的价值为 vali ，则价值定义如下：
 *
 * 1. i=1时:val[i] = 1
 * 2. i>1时：
 * 2.1 若 s[i] ≠ s[i-1] , val[i] = 1
 * 2.2 若 s[i] = s[i-1] , val[i ]= val[i-1] + 1
 * 字符串的价值等于 val1 + val2 + val3 + ... + valn
 *
 * 你可以删除 s 的任意个字符，问这个串的最大价值是多少。
 */

import java.util.Scanner;

/**
 * 第一行一个正整数 n ，代表串长度。
 * 接下来一行一个 01 串 s 。
 * 1 ≤ n ≤ 5,000
 *
 * 输出一个整数代表答案
 */
//递归。从左往右的尝试模型。
//当前index位置的字符保留；当前index位置的字符不保留。这两种情况取最大值。
public class test01 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(scan.hasNext()){
            int n=scan.nextInt();
            scan.nextLine();
            String s=scan.nextLine();

            int res=0;
            if(n==0){
                System.out.println(0);
                return;
            }
            int[] arr=new int[n];
            for (int i = 0; i < n; i++) {
                if(s.charAt(i)=='0'){
                    arr[i]=0;
                }else{
                    arr[i]=1;
                }
            }
            res= bp(arr,0,0,0);
            System.out.println(res);
        }
    }
    //递归
    // 递归含义 :
    // 目前在arr[index...]上做选择, str[index...]的左边，最近的数字是lastNum
    // 并且lastNum所带的价值，已经拉高到baseValue
    // 返回在str[index...]上做选择，最终获得的最大价值
    // index -> 0 ~ 4999
    // lastNum -> 0 or 1
    // baseValue -> 1 ~ 5000
    // 5000 * 2 * 5000 -> 5 * 10^7(过!)
   static int bp(int[] arr,int index,int lastNum,int baseValue){
        if(index==arr.length){
            return 0;
        }
        int curValue=0;
        if(lastNum==arr[index]){
            curValue=baseValue+1;
        }else{
            curValue=1;
        }
        // 当前index位置的字符保留
        int next1=bp(arr,index+1,arr[index],curValue);
        // 当前index位置的字符不保留
        int next2=bp(arr,index+1,lastNum,baseValue);
        return Math.max(curValue+next1,next2);
    }

}


