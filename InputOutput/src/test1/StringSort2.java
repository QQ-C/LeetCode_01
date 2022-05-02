package test1;

/**
 * 对输入的字符串进行排序后输出
 * 输入描述:
 * 多个测试用例，每个测试用例一行。
 *
 * 每行通过空格隔开，有n个字符，n＜100
 *
 * 输出描述:
 * 对于每组测试用例，输出一行排序过的字符串，每个字符串通过空格隔开
 *
 * 输入例子1:
 * a c bb
 * f dddd
 * nowcoder
 *
 * 输出例子1:
 * a bb c
 * dddd f
 * nowcoder
 */
import java.util.*;
public class StringSort2 {
    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        while (scan.hasNext()){
            String[] str=scan.nextLine().split(" ");
            Arrays.sort(str);
            for(String s:str){
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }
}
