package Tencent2021;

import java.util.Scanner;

/**
 * 小A在玩一个网络游戏。这个游戏有个抽装备环节。装备池总共有n+m件装备，分别为n件普通装备和m件ssr装备。抽一次装备的费用按你抽中的装备决定。
 *
 * 抽中每一件装备的概率都为1/(n+m)。如果你抽中了ssr装备。
 * 这次的抽装备费用为2金币，否则这次的费用为1金币。如果你抽中了ssr装备，
 * 得到奖励，并且装备不会放回。如果你抽中了普通装备。
 * 得到奖励，但是这件装备会放回装备池。现在小A希望抽中所有的ssr装备，
 * 请你计算一下：需要花费金币的期望值。
 */

//输入一行：n,m(1<=n,m<=106)
    //抽中所有的ssr装备，需要花费金币的期望值。输出保留2位有效小数。

/**
 * 想法：
 *
 * 换一种方式求解概率论；如果有n张普通卡，m张ssr卡，假设平均随机抽取N次，从期望来讲，抽到一张m卡，对应抽到n/m张普通卡；所以第m-i次抽到ssr卡的期望金币是：
 *
 * 2*1+1*n/i；最后结果累加，就是图中所示的结果；
 */
public class test02 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int m=scan.nextInt();
        double ans=0.00;
        for (int i = 1; i <=m ; i++) {
            ans = ans + (2.0 +(double)n/(double)i);
        }
        System.out.println(String.format("%.2f",ans));
    }
}
