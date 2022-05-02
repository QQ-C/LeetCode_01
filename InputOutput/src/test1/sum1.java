package test1;
import java.util.*;

/**    计算a+b
 * 输入描述:
 * 输入包括两个正整数a,b(1 <= a, b <= 1000),输入数据包括多组。
 *
 * 输出描述:
 * 输出a+b的结果
 *
 * 输入例子1:
 * 1 5
 * 10 20
 *
 * 输出例子1:
 * 6
 * 30
 */
public class sum1 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int a=scan.nextInt();
            int b=scan.nextInt();
            System.out.println(a+b);
        }
    }
}
