package test01.demo;

import javax.management.MBeanRegistration;
import java.util.LinkedList;
import java.util.List;

public class test04 {
    public static void main(String[] args) {
        Solution041 solution041 =new Solution041();
        int[][] ans=solution041.generateMatrix(3);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans.length; j++) {
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
    }
}
class Solution041{
    public int [][] generateMatrix(int n){
        int[][] res = new int[n][n];  //定义一个二维数组
        int startx=0,starty=0; //定义每次循环一个圈的起始位置     定义每次循环起始位置
        int loop = n / 2; // 每个圈循环几次，例如n为奇数3，那么loop = 1 只是循环一圈，矩阵中间的值需要单独处理      循环次数
        int offset = 1; // 每一圈循环，需要控制每一条边遍历的长度       定义偏移量
        int count = 1; // 用来给矩阵中每一个空格赋值     定义填充数字
        int mid = n / 2; // 矩阵中间的位置，例如：n为3， 中间的位置就是(1，1)，n为5，中间位置为(2, 2)     定义中间位置
        while(loop>0){
            int i=startx;
            int j=starty;
            // 下面开始的四个for就是模拟转了一圈
            // 模拟填充上行从左到右(左闭右开)
            for (j = starty; j < starty + n - offset; j++) {
                res[startx][j] = count++;
            }
            // 模拟填充右列从上到下(左闭右开)
            for (i = startx; i < startx + n - offset; i++) {
                res[i][j] = count++;
            }
            // 模拟填充下行从右到左(左闭右开)
            for (; j > starty; j--) {
                res[i][j] = count++;
            }
            // 模拟填充左列从下到上(左闭右开)
            for (; i > startx; i--) {
                res[i][j] = count++;
            }
            loop--;
            // 第二圈开始的时候，起始位置要各自加1， 例如：第一圈起始位置是(0, 0)，第二圈起始位置是(1, 1)
            startx++;
            starty++;
            // offset 控制每一圈里每一条边遍历的长度
            offset += 2;
        }
            if (n % 2 == 1) {
                res[mid][mid] = count;
            }

            return res;
    }
}


//class Solution041{
//    public List<Integer> generateMatrix(int[][] matrix){
//        List<Integer> res = new LinkedList<>();
//        if(matrix.length == 0){
//            return res;
//        }
//        int up=0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
//        while(true){
//            for (int col = left; col <= right ; col++) {    //列
//                res.add(matrix[up][col]);
//            }
//            if(++up>down){
//                break;
//            }
//            for (int row = up; row <=down ; row++) {
//                res.add(matrix[row][right]);
//            }
//            if(--right<left){
//                break;
//            }
//            for (int col = right; col >=left ; col--) {
//                res.add(matrix[down][col]);
//            }
//            if(--down<up){
//                break;
//            }
//            for (int row = down; row >=up ; row--) {
//                res.add(matrix[row][left]);
//            }
//            if(++left>right){
//                break;
//            }
//        }
//        return res;
//    }
//}

//class Solution041 {
//    public int[] spiralOrder(int[][] matrix) {
//        int[] ans = new int[matrix.length*matrix[0].length];
//        if (matrix.length==0){
//            return new int[0];
//        }
//        int up = 0, down = matrix.length-1, left = 0, right = matrix[0].length-1;
//        int count=0;
//        while(true){
//            for (int i = left; i <= right ; i++) {
//                ans[count++]=matrix[up][i];
//            }
//            if(++up>down){
//                break;
//            }
//            for (int i = up; i <= down; i++) {
//                ans[count++]=matrix[i][right];
//            }
//            if(--right<left){
//                break;
//            }
//            for (int i = right; i >=left ; i--) {
//                ans[count++]=matrix[down][i];
//            }
//            if(--down<up){
//                break;
//            }
//            for (int i = down; i >=up ; i--) {
//                ans[count++]=matrix[i][left];
//            }
//            if(++left>right){
//                break;
//            }
//        }
//        return ans;
//    }
//}

