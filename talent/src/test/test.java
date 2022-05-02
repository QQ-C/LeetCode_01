package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组的全排列
 */
public class test {
    public static void main(String[] args) {

        int[] num ={1,2,3,4,5};
        Solution solution=new Solution();
        if(num==null||num.length==0){
            System.out.println(solution.list);
        }
        solution.backTracking(num,solution.temp);
//        return solution.list;
        for (int i = 0; i < solution.list.size(); i++) {
            for (int j = 0; j < solution.list.get(i).size(); j++) {
                System.out.print(solution.list.get(i).get(j)+" ");
            }
            System.out.println();
        }

    }
}

class Solution{
    List<List<Integer>> list = new ArrayList<>();
    LinkedList<Integer> temp= new LinkedList<>();

    void backTracking(int[] num,LinkedList<Integer> temp){
        if(temp.size()==num.length){
            list.add(new ArrayList<>(temp));
        }
        for(int i=0;i<num.length;i++){
            // 如果path中已有，则跳过
            if (temp.contains(num[i])) {
                continue;
            }
            temp.add(num[i]);
            backTracking(num, temp);
            temp.removeLast();
        }
    }
}
/**
 括号入栈
 */
