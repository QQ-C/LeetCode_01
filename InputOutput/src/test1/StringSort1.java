package test1;

/**
 *对输入的字符串进行排序后输出
 * 输入描述:
 * 输入有两行，第一行n
 *
 * 第二行是n个字符串，字符串之间用空格隔开
 *
 * 输出描述:
 * 输出一行排序后的字符串，空格隔开，无结尾空格
 *
 * 输入例子1:
 * 5
 * c d a bb e
 *
 * 输出例子1:
 * a bb c d e
 */
import java.util.Arrays;
import java.util.Scanner;
//public class StringSort1 {
//    public static void main(String[] args) {
//        Scanner scan=new Scanner(System.in);
//        while(scan.hasNext()){
//            int n=scan.nextInt();
//            String[] str=new String[n];
//            for(int i=0;i<n;i++){
//                str[i] = scan.next();
//            }
//            Arrays.sort(str);
//            for (int i = 0; i < n; i++) {
//                System.out.print(str[i]+" ");
//            }
//        }
//    }
//}

public class StringSort1 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
//        while(scan.hasNext()){
            int n=scan.nextInt();
            scan.nextLine();
            String[] str=scan.nextLine().split(" ");
            Arrays.sort(str);
            for(String s:str){
                System.out.print(s+" ");
            }
//        }
    }
}
