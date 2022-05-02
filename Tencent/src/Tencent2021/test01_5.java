package Tencent2021;

import java.util.Scanner;

/** 借鉴牛客的解法 */
public class test01_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        sc.nextLine();

        String str = sc.nextLine();
//        str = "111001111000000";
        int cou0 = 0;
        int cou1 = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='0') cou0++;
            else cou1++;
        }


        if(cou0>cou1){
            int left = couF(str,'1');
            int right = couL(str,'1');
            int sum1 = ((1+left)*left)/2; //先括起来！保证结果一定是准确的
            int sum2 = ((1+right)*right)/2;
            int sum3 = ((cou0+1)*cou0)/2;
            System.out.println(sum1+sum2+sum3);

        }else
        if(cou1>cou0){
            int left = couF(str,'0');
            int right = couL(str,'0');
            int sum1 = ((1+left)*left)/2;
            int sum2 = ((1+right)*right)/2;
            int sum3 = ((cou1+1)*cou1)/2;
            System.out.println(sum1+sum2+sum3);

        }else{//反而要小心出错
            int left = couF(str,str.charAt(len-1));//选择一个
            int right = couL(str,str.charAt(len-1));//选择一个
            int sum1 = ((1+left)*left)/2;
            int sum2 = ((1+right)*right)/2;
            int sum3=0;
            if(str.charAt(len-1)=='1'){ //那么我们 sum1 sum2 统计的是头尾可能连续1的价值，我们中间要保留的是0
                sum3 = ((1+cou0)*cou0)/2;
            }else sum3 = ((1+cou1)*cou1)/2;

            System.out.println(sum1+sum2+sum3);
        }
        return;

    }
    public static int couF(String str,char ch){
        int cou =0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == ch){
                cou++;
            }else break;
        }

        return cou;
    }
    public static int couL(String str, char ch){
        int cou =0;
        for(int i= str.length()-1;i>=0;i--){
            if(str.charAt(i) == ch) cou++;
            else break;
        }
        return cou;
    }
}