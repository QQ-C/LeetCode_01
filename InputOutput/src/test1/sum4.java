package test1;

/**
 * 计算一系列数的和
 * 输入描述:
 * 输入数据包括多组。
 * 每组数据一行,每行的第一个整数为整数的个数n(1 <= n <= 100), n为0的时候结束输入。
 * 接下来n个正整数,即需要求和的每个正整数。
 *
 * 输出描述:
 * 每组数据输出求和的结果
 *
 * 输入例子1:
 * 4 1 2 3 4
 * 5 1 2 3 4 5
 * 0
 *
 * 输出例子1:
 * 10
 * 15
 */
import java.util.Scanner;
public class sum4 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(scan.hasNext()){
            int n=scan.nextInt();
            if(n==0) break;
            int sum=0;
            for(int i=0;i<n;i++){
                sum+=scan.nextInt();
            }
            System.out.println(sum);
        }
    }
}
