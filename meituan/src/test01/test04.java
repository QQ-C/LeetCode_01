package test01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//井字格 每行每列有不同颜色 有多少种方案
public class test04 {

    public static void main(String[] args) {
        int n;
        Scanner scan=new Scanner(System.in);
        n=scan.nextInt();
        test04 solution=new test04();
        solution.backTracking(n,1,path);
        System.out.println(res%1000000007);
    }
    static List<Integer> path;
    static int  res=0;
    boolean isValid(List<Integer> path){
        return path.get(0)!=path.get(1) && path.get(0)!=path.get(2) && path.get(1)!=path.get(3) && path.get(2)!=path.get(3);
    }
    void backTracking(int n,int startIndex,List<Integer> path){
        if(path==null){
            return;
        }
        if(path.size()==4){
            if(isValid(path)){
                res++;
            }
            return;
        }
        for(int i=1;i<=n;++i){
            path.add(i);
            backTracking(n,1,path);
            path.remove(i);
        }
    }
}
