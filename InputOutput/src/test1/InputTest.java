package test1;

/**
 * 数据范围：2*10^10
 *
 * 输入描述:
 * 输入有多组测试用例，每组空格隔开两个整数
 *
 * 输出描述:
 * 对于每组数据输出一行两个整数的和
 *
 * 输入例子1:
 * 1 1
 *
 * 输出例子1:
 * 2
 */
import java.util.Scanner;
import java.math.BigInteger;
public class InputTest {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(scan.hasNext()){
            BigInteger a=scan.nextBigInteger();
            BigInteger b=scan.nextBigInteger();
            System.out.println(a.add(b));

//            long a = Long.parseLong(scan.next());
//            long b = Long.parseLong(scan.next());
//            System.out.println(a + b);
        }
    }
}
