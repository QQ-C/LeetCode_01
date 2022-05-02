package Tencent2021;

import java.util.*;

public class test02_1{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        double cost = 2 * m;

        for (double i = 1; i <= m; i++) {
            cost += (1 / i) * n;
        }
        System.out.println(String.format("%.2f", cost));
    }
}

//import java.util.*;
//public class test02_2{
//    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        double res = 0;
//        for(int i = m;i>0;i--){
//            double p = (double)(i*2+n) / i;
//            res+=p;
//        }
//
//        System.out.println(String.format("%.2f",res));
//    }
//}