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
public class huawei {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<int[]> list = new LinkedList<>();
        while (scan.hasNext()) {
            int M = scan.nextInt();  //输入m台服务器
            scan.nextLine();
            for (int i = 0; i < M; i++) {
                String s = scan.nextLine();
                String[] nums = s.split(",");   //存储每台服务器的属性  我这边是用字符串存储
                int[] num=new int[nums.length];
                for (int j = 0; j < nums.length; j++) {
                    num[i] =Integer.parseInt(nums[i]);
                }
                list.addLast(num);
            }

            String s2 = scan.nextLine();
            String[] requests = s2.split(" ");   //存储分配要求 1最大分配数量 2分配策略 3  >=cpuCount >=memSize  =cpuArch  =supportNP
            int[] request = new int[requests.length];
            for (int j = 0; j < requests.length; j++) {
                request[j] =Integer.parseInt(requests[j]);
            }

            int result_M = 0;     // 满足要求的数量
            List<Integer> result = new LinkedList<>();  //存储结果
            int length = request.length;  //

            // 小于 最大分配数量
            while (result_M <= request[0]) {    //因为是字符串存储的，所以都转为整数  如果你是按照整数存储，直接比较即可
                //两层遍历 分别是 遍历每一台服务器  然后遍历该服务器参数
                if(request[5]==2&&request[4]==9) {
                    if (request[1] == 1) {
                        sort11(list, request, result, 1, result_M);
                    } else if (request[1] == 2) {
                        sort11(list, request, result, 2, result_M);
                    }
                } else{
                    for (int i = 0; i < list.size(); i++) {
                        int[] temp = list.get(i);  //临时变量 存储服务器
                        if(request[5]!=2&&request[4]==9) {
                            if (request[5] == temp[4] && request[4] == temp[3]) {
                                if (request[1] == 1) {
                                    sort11(list, request, result, 1, result_M);
                                } else if (request[1] == 2) {
                                    sort11(list, request, result, 2, result_M);
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
        }

    }

    private static void sort11(List<int[]> list, int[] request,List<Integer> result,int n,int result_M){
        if(n==1){
            Collections.sort(list,(a,b)->{return (a[1]-b[1]);});  //正序
        }
        if(n==2){
            Collections.sort(list,(a,b)->{return (a[2]-b[2]);});  //正序
        }
        for(int i=0;i<list.size();i++){
            int[] temp = list.get(i);
            if (temp[1] >= request[2]&&temp[2]>=request[3]){
                if(result_M<=request[0]) {
                    result_M++;
                    result.add(temp[0]);
                }else {
                    System.out.println(result_M);
                    System.out.println(result);
                    return;
                }
            }else{
                continue;
            }
        }
    }
}

