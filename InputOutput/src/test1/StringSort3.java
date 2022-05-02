package test1;


/**
 * 对输入的字符串进行排序后输出
 * 多个测试用例，每个测试用例一行。
 * 每行通过,隔开，有n个字符，n＜100
 *
 * 输出描述:
 * 对于每组用例输出一行排序后的字符串，用','隔开，无结尾空格
 *
 * 输入例子1:
 * a,c,bb
 * f,dddd
 * nowcoder
 *
 * 输出例子1:
 * a,bb,c
 * dddd,f
 * nowcoder
 */
import java.util.Arrays;
import java.util.Scanner;

public class StringSort3 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(scan.hasNext()){
            String[] str=scan.nextLine().split(",");
            Arrays.sort(str);

//            for(String s:str){
//                System.out.print(s+",");
//            }   //不行，最后有一个逗号

//            for(int i=0;i<str.length-1;i++){
//                System.out.print(str[i]+",");
//            }
//            System.out.print(str[str.length-1]);
            //或者
            for(int i=0;i<str.length;i++){
                System.out.print(str[i]);
                if(i<str.length-1){
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }
}
