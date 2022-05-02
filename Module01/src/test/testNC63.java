package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class testNC63 {
    public static void main(String[] args) {
        int[] numbers={13,12,11,0,1};
        System.out.println(IsContinuous(numbers));
    }
    static boolean IsContinuous(int [] numbers) {
        Set<Integer> set=new HashSet<>();
        int len=numbers.length;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<len;i++){
            if(set.contains(numbers[i])){
                return false;
            }
            if(numbers[i]==0){
                continue;
            }
            set.add(numbers[i]);
            max=Math.max(max,numbers[i]);
            min=Math.min(min,numbers[i]);
        }
        return max-min <5;
    }
}
