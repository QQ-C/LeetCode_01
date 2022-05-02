package Tencent2021;
import java.util.*;
public class test01_4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        String s=sc.nextLine();
        int zero=0;
        int one=0;
        int big=0;
        //int last=0;
        long sum=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='0'){
                zero++;
            }
            else{
                one++;
            }
        }
        int tmp=0;
        if(zero==one){
            int[] tmp1=find(s,0);
            int[] tmp2=find(s,1);
            int a=(tmp1[0]+1)*tmp1[0]/2+(tmp1[1]+1)*tmp1[1]/2;
            int b=(tmp2[0]+1)*tmp2[0]/2+(tmp2[1]+1)*tmp2[1]/2;
            tmp=Math.max(a,b);
            sum=tmp+(zero+1)*zero/2;
        }
        else{
            big=zero>one?0:1;
            int[] tmp1=find(s,big);
            int a=(tmp1[0]+1)*tmp1[0]/2+(tmp1[1]+1)*tmp1[1]/2;
            if(big==0){
                sum=a+(zero+1)*zero/2;
            }
            else{
                sum=a+(one+1)*one/2;
            }
        }
        System.out.println(sum);

    }
    public static int[] find(String s,int big){
        char ch=big==0?'1':'0';
        int[] tmp=new int[2];
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==ch){
                tmp[0]++;
            }
            else{
                break;
            }
        }
        //
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)==ch){
                tmp[1]++;
            }
            else{
                break;
            }
        }
        return tmp;
    }
}
