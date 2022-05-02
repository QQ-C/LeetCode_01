package Tencent2021;

import java.util.Scanner;

public class test01_2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int nums = sc.nextInt();
        String s = sc.next();
        int first1 = -1;
        int last1 = nums;
        int first0 = -1;
        int last0 = nums;
        int nums1 = 0;
        int nums0 = 0;
        for (int i=0; i<nums; i++){
            char c = s.charAt(i);
            if (c=='1'){
                nums1++;
                if (first1==-1) first1 = i;
                last1 = i;
            }else if(c=='0'){
                nums0++;
                if (first0==-1) first0 = i;
                last0 = i;
            }
        }
        int sum = 0;
        if (nums1>nums0){
            int val = 1;
            for (int i=0; i<first1; i++){
                sum += val++;
            }
            val = 1;
            for (int i=0; i<nums1; i++){
                sum += val++;
            }
            val = 1;
            for (int i=last1+1; i<nums; i++){
                sum += val++;
            }
        }else{
            int val = 1;
            for (int i=0; i<first0; i++){
                sum += val++;
            }
            val = 1;
            for (int i=0; i<nums0; i++){
                sum += val++;
            }
            val = 1;
            for (int i=last0+1; i<nums; i++){
                sum += val++;
            }
        }
        System.out.println(sum);
    }

}
