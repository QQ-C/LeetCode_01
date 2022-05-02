package test01;

import java.util.*;

//Main
public class test01 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Map<Character,Integer> map =new HashMap<>();
        map.put('0',1);
        map.put('1',0);
        map.put('2',0);
        map.put('3',0);
        map.put('4',0);
        map.put('5',0);
        map.put('6',1);
        map.put('7',0);
        map.put('8',2);
        map.put('9',1);

        int n=scan.nextInt();
        String s = String.valueOf(n);
        int length = s.length();
        int sum=0;
        for(int i=0;i<length;i++){
            char c=s.charAt(i);
            sum=sum+map.get(c);
        }
        System.out.println(sum);
    }
}

