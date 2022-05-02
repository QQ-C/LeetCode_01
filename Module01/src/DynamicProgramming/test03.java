package DynamicProgramming;

public class test03 {

}

/**
 * 509. 斐波那契数
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 */
class Solution509 {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        //1.确定dp数组 第i个数的斐波那契数值是dp[i]
        int[] dp = new int[n + 1];
        //3.初始化
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {   //4.从前向后遍历
            dp[i] = dp[i - 1] + dp[i - 2];  //2.递推公式 状态转移方程 dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

//当然可以发现，我们只需要维护两个数值就可以了，不需要记录整个序列。
class Solution509_1 {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        //1.确定dp数组
        int[] dp = new int[2];
        //3.初始化
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {   //4.从前向后遍历
            int sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;  //2.递推公式
        }
        return dp[1];
    }
}

class Solution509_12 {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        //1.确定dp数组
        //3.初始化
        int a = 0;
        int b = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {   //4.从前向后遍历
            sum = a + b;
            a = b;
            b = sum;  //2.递推公式
        }
        return b;
    }
}


//本题还可以使用递归解法来做
class Solution509_2 {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
class Solution70 {
    public int climbStairs(int n) {
        //1. dp[i]  爬到第i层楼梯，有dp[i]种方法
//        int[] dp=new int[n+1];

        //2.确定递推公式
        /**从dp[i]的定义可以看出，dp[i] 可以有两个方向推出来。
         首先是dp[i - 1]，上i-1层楼梯，有dp[i - 1]种方法，那么再一步跳一个台阶不就是dp[i]了么。
         还有就是dp[i - 2]，上i-2层楼梯，有dp[i - 2]种方法，那么再一步跳两个台阶不就是dp[i]了么。
         那么dp[i]就是 dp[i - 1]与dp[i - 2]之和！
         所以dp[i] = dp[i - 1] + dp[i - 2] 。*/

        //3.dp数组初始化  爬到第i层楼梯，有dp[i]中方法。
        //dp[1] = 1，dp[2] = 2

        //4.确定遍历顺序
        // 从递推公式dp[i] = dp[i - 1] + dp[i - 2];中可以看出，遍历顺序一定是从前向后遍历的
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) { // 注意i是从3开始的
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

//空间优化
class Solution70_1 {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int a = 1;
        int b = 2;
        for (int i = 3; i <= n; i++) { // 注意i是从3开始的
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}

/**
 * 746. 使用最小花费爬楼梯
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 */
class Solution746 {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }

        //dp[i]的定义：到达第i个台阶所花费的最少体力为dp[i]
        //dp[i] = min(dp[i - 1], dp[i - 2]) + cost[i];
        //dp[0] = cost[0];
        //dp[1] = cost[1];
        //从前到后遍历cost数组
        int[] dp=new int[cost.length];
        dp[0]=cost[0];
        dp[1]=cost[1];
        for(int i=2;i<cost.length;i++){
            dp[i]=Math.min(dp[i-1],dp[i-2])+cost[i];
        }
        // // 注意最后一步可以理解为不用花费，所以取倒数第一步，第二步的最少值
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}
//还可以优化空间复杂度，因为dp[i]就是由前两位推出来的，那么也不用dp数组了
class Solution746_1 {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }


        int dp0 = cost[0];
        int dp1 = cost[1];

        for(int i=2;i<cost.length;i++){
            int dpi=Math.min(dp0,dp1)+cost[i];
            dp0 = dp1; // 记录一下前两位
            dp1 = dpi;
        }
        // // 注意最后一步可以理解为不用花费，所以取倒数第一步，第二步的最少值
        return Math.min(dp0, dp1);
    }
}

/**62.不同路径
 *一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 */
class Solution62 {
    public int uniquePaths(int m, int n) {
        //dp[i][j] 移动到ij有多少条路径
        //dp[i][j] = dp[i][j-1]+dp[i-1][j];
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            dp[i][0]=1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i]=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=dp[i][j-1]+dp[i-1][j];
            }
        }
        return dp[m-1][n-1];
    }
}

/**
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 */
class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;   //行
        int n=obstacleGrid[0].length; //列
        int[][] dp=new int[m][n];
        for(int i=0;i<n;i++){
            if(obstacleGrid[0][i]==1) break;  //一旦遇到障碍，后续都到不了
            dp[0][i]=1;
        }
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]==1) break;  //一旦遇到障碍，后续都到不了
            dp[i][0]=1;
        }
        for(int i=1;i<m;i++){  //从左到右进行
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]==1) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m-1][n-1];
    }
}

/**
 343. 整数拆分
 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 返回 你可以获得的最大乘积 。
 */
class Solution343 {
    public int integerBreak(int n) {
        int[] dp=new int[n+1];
        dp[2]=1;
        dp[1]=1;
        for(int i=3;i<=n;i++){
            for(int j=1;j<i-1;j++){
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }
}

/**
 96.不同的二叉搜索树
 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 */
class Solution96{
    public int numTrees(int n) {
        int[] dp=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                dp[i]+=dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
}