package test1;

/**
 * 计算a+b
 * 输入描述:
 * 输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为0 0则结束输入
 * <p>
 * 输出描述:
 * 输出a+b的结果
 * <p>
 * 输入例子1:
 * 1 5
 * 10 20
 * 0 0
 * <p>
 * 输出例子1:
 * 6
 * 30
 */

import java.util.*;

public class sum3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            if (a == 0 && b == 0) {
                break;
            }
            System.out.println(a + b);

        }
    }
}
