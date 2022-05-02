package test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//服务器属性 ： 编号 CPU核数     内存       CPU架构  是否支持NP加速卡（0,1）
//                >=cpuCount >=memSize  =cpuArch  =supportNP
//优先级策略 strategy
//CPU 优先 1
//内存 优先  2
//编号


//先输出实际分配数量，后按照分配的服务器编号从小到大，依次输出
public class huawei1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<int[]> list = new LinkedList<>();
//        while (scan.hasNext()) {
            int M = scan.nextInt();  //输入m台服务器
            scan.nextLine();
            for (int i = 0; i < M; i++) {
                String s = scan.nextLine();
                String[] nums = s.split(",");   //存储每台服务器的属性  我这边是用字符串存储
                int[] num = new int[nums.length];
                for (int j = 0; j < nums.length; j++) {
                    num[j] = Integer.parseInt(nums[j]);
                }
                list.addLast(num);
            }

            String s2 = scan.nextLine();
            String[] requests = s2.split(" ");   //存储分配要求 1最大分配数量 2分配策略 3  >=cpuCount >=memSize  =cpuArch  =supportNP
            int[] request = new int[requests.length];
            for (int j = 0; j < requests.length; j++) {
                request[j] = Integer.parseInt(requests[j]);
            }

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).length; j++) {
                    System.out.print(list.get(i)[j]+" ");
                }
                System.out.println();
            }
            for (int j = 0; j < requests.length; j++) {
                System.out.print(requests[j]+" ");
            }
            System.out.println();
//        }
    }
}

