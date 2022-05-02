package test1;
/**
 * 计算一系列数的和
 * 输入描述:
 * 输入数据有多组, 每行表示一组输入数据。
 * 每行的第一个整数为整数的个数n(1 <= n <= 100)。
 * 接下来n个正整数, 即需要求和的每个正整数。
 *
 * 输出描述:
 * 每组数据输出求和的结果
 *
 * 输入例子1:
 * 4 1 2 3 4
 * 5 1 2 3 4 5
 *
 * 输出例子1:
 * 10
 * 15
 */

import java.util.Scanner;

public class sum6 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(scan.hasNext()){
            int n= scan.nextInt();
            int sum=0;
            for(int i=0;i<n;i++){
                sum+=scan.nextInt();
            }
            System.out.println(sum);
        }
    }
}
