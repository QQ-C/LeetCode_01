package Tencent2021;
import java.util.*;
public class test01_3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        // System.out.println(s);
        char[] schar = s.toCharArray();
        int res = 0;
        int couti = 0;
        int couto = 0;
        for(int i = 0;i<x;i++){
            if(schar[i]=='0') couto++;
            else {
                couti++;
            }
        }
        if(couti>=couto){
            int z=0;int y= x-1;
            while (schar[z]=='0')z++;
            while(schar[y]=='0')y--;
            int temp = 1;
            for(int f =1;f<=couti;f++){
                res+=temp; temp++;//System.out.println(res);
            }
            if(z>0){
                int temp4 = 1;
                for(int f =0;f<z;f++){
                    res+=temp4;temp4++;  //System.out.println(res);
                }
            }
            if(y+1<x){
                int temp1 =1;
                for(int f=y+1;f<x;f++){

                    res+=temp1; temp1++;// System.out.println(res);
                }
            }
        }else{
            int z=0;int y= x-1;
            while (schar[z]=='1')z++;
            while(schar[y]=='1')y--;
            int temp2 =1;
            for(int f =1;f<=couto;f++){
                res+=temp2; temp2++;
            }
            if(z>=0){
                int temp5=1;
                for(int f =0;f<z;f++){
                    res+=temp5; temp5++;
                }
            }
            if(y+1<x){
                int temp3 =1;
                for(int f=y+1;f<x;f++){
                    res+=temp3;temp3++;
                }
            }
        }
        System.out.println(res);
    }
}                                  //贪心算法