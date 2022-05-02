package test1;

import java.util.*;

/**
 * 计算a+b
 * 输入描述:
 * 输入第一行包括一个数据组数t(1 <= t <= 100)
 * 接下来每行包括两个正整数a,b(1 <= a, b <= 1000)
 *
 * 输出描述:
 * 输出a+b的结果
 *
 * 输入例子1:
 * 2
 * 1 5
 * 10 20
 *
 * 输出例子1:
 * 6
 * 30
 */
public class sum2 {
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
//        while(scan.hasNext()){
            int t=scan.nextInt();
            for(int i=0;i<t;i++){
                int a=scan.nextInt();
                int b=scan.nextInt();
                System.out.println(a+b);
            }
//        }
    }
}
