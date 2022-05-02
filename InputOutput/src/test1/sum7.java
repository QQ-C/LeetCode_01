package test1;
/**
 * 计算一系列数的和
 * 输入描述:
 * 输入数据有多组, 每行表示一组输入数据。
 *
 * 每行不定有n个整数，空格隔开。(1 <= n <= 100)。
 *
 * 输出描述:
 * 每组数据输出求和的结果
 *
 * 输入例子1:
 * 1 2 3
 * 4 5
 * 0 0 0 0 0
 *
 * 输出例子1:
 * 6
 * 9
 * 0
 */

import java.util.*;
public class sum7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String[] nums=scan.nextLine().split(" ");
            int sum=0;
            for (int i = 0; i < nums.length; i++) {
                sum+=Integer.parseInt(nums[i]);
            }
            System.out.println(sum);
        }
    }
}
