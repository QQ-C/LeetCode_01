package jianzhiOffer.offer4;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class mySolution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if(matrix==null||rows==0||cols==0){
            return false;
        }
        if (matrix != null && cols > 0 && rows > 0) {
            //左上角
            int row = 0;
            int col = cols - 1;
            while (row < rows && col >= 0) {
                if(matrix[row][col]==target){
                    return true;
                }else if(matrix[row][col]>target){
                    col--;
                }else{
                    row++;
                }
            }
        }
        return false;
    }
}
