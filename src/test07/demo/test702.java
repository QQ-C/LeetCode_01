package test07.demo;

import java.util.Scanner;

public class test702 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()){
            String s=scan.nextLine();
            int length=s.length();
            double count=1;
            int letterCount=0;
            for(int i=0;i<length;i++){
                if(s.charAt(i)==' '){
                    count++;
                }else{
                    letterCount++;
                }
            }
            System.out.println(String.format("%.2f",letterCount/count));
        }
    }
}
