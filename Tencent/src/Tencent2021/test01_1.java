package Tencent2021;

import java.util.Scanner;

import java.util.*;
import java.io.*;
public class test01_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(read.readLine().trim());
        char[] cs = read.readLine().trim().toCharArray();
        // 动态规划
        // 记录这个位置的最大和
        int[] vals = new int[n];
        vals[0] = 1;
        for (int i = 1; i < n; i++) {
            // 假设这个节点最小的情况：1
            vals[i] = vals[i - 1] + 1;
            int cc = 1;
            for (int g = i - 1; g >= 0; g--) {
                // 相同节点个数
                if (cs[i] == cs[g]) {
                    cc++;
                } else {
                    // 如果保留该区域相同节点 和 不同节点以及之前的数据 的最大
                    int temp = vals[g] + (cc + 1) * cc / 2;
                    // 如果数据比当前大，则更新
                    if (temp > vals[i])
                        vals[i] = temp;
                }
            }
            //如果保留所有相同的节点 是否相同
            int temp = (cc + 1) * cc / 2;
            if (vals[i] < temp) {
                vals[i] = temp;
            }
        }
        writer.write(Integer.toString(vals[n - 1]));
        writer.newLine();
        writer.flush();
    }
}
