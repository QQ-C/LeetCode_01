package DynamicProgramming;

public class testBeibao {
    public static void main(String[] args) {
//        Solution01 solution= new Solution01();
//        int[] weight = {1, 3, 4};
//        int[] value = {15, 20, 30};
//        int bagsize = 4;
//        solution.testweightbagproblem(weight, value, bagsize);
//
//
        Solution02 solution02=new Solution02();
        solution02.testCompletePack();
    }
}

class Solution01{
     public void testweightbagproblem(int[] weight,int[] value,int bagsize){ //weight 每个物品重量数组  value 每个物品价值数组  bagsize 背包容量
         int wlen = weight.length;
         int value0=0;
         //定义dp数组：dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
         int[][] dp=new int[wlen+1][bagsize+1];
//         //初始化：背包容量为0时，能获得的价值都为0
//         for(int i=0;i<=wlen;i++){
//             dp[i][0]=0;
//         }
//         //初始化：
//         for(int j=0;j<=bagsize;j++){
//             if(j<weight[0]){
//                 dp[0][j]=0;
//             }else{
//                 dp[0][j]=value[0];
//             }
//         }
         for(int j=weight[0];j<=bagsize;j++){
             dp[0][j]=value[0];
         }
         // weight数组的大小 就是物品个数
         for (int i = 1; i <= wlen; i++){
             for (int j = 1; j <= bagsize; j++){
                 if (j < weight[i - 1]){
                     dp[i][j] = dp[i - 1][j];
                 }else{
                     dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                 }
             }
         }

         //打印dp数组
         for (int i = 0; i <= wlen; i++){
             for (int j = 0; j <= bagsize; j++){
                 System.out.print(dp[i][j] + " ");
             }
             System.out.print("\n");
         }
     }
}

class Solution01_1{
    public void testWeightBagProblem(int[] weight, int[] value,int bagweight){
        int wlen = weight.length; //物品个数
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值
        int[] dp=new int[bagweight+1];
        //遍历顺序：先遍历物品，再遍历背包容量
        //如果使用一维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒序遍历！
        for(int i=0;i<wlen;i++){
            for(int j=bagweight;j>=weight[i];j--){    // 每一个元素一定是不可重复放入，所以从大到小遍历
                dp[j]=Math.max(dp[j],dp[j-weight[i]]+value[i]);
            }
        }
        //打印dp数组
        for (int j = 0; j <= bagweight; j++){
            System.out.print(dp[j] + " ");
        }
    }
}

/**
 * 完全背包问题
 */
//// 先遍历物品，在遍历背包
class Solution02{
    public void testCompletePack(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];

        for(int i=0;i<weight.length;i++){ // 遍历物品
            for(int j=weight[i];j<=bagWeight;j++){// 遍历背包容量
                dp[j]=Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        for(int maxValue:dp){
            System.out.println(maxValue+" ");
        }
    }
}
//先遍历背包，再遍历物品
class Solution02_1{
    public void testCompletePack(){
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        int[] dp = new int[bagWeight + 1];

        for(int j=0;j<=bagWeight;j++){ // // 遍历背包容量
            for(int i=0;i<=weight.length;i++){  // 遍历物品
                if(j-weight[i]>=0){
                    dp[j]=Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }
        for(int maxValue:dp){
            System.out.println(maxValue+" ");
        }
    }
}