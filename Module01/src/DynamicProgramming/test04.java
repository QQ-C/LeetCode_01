package DynamicProgramming;

import java.util.Map;

public class test04 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        Solution494 solution494 = new Solution494();
        solution494.findTargetSumWays(nums, 3);
    }
}

/**
 * 416. 分割等和子集   698.划分为k个相等的子集  473.火柴拼正方形
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
class Solution416 {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;


        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];

        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) { // 每一个元素一定是不可重复放入，所以从大到小遍历
                //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
//        if(dp[target]==target){
//            return true;
//        }
//        return false;
        return dp[target] == target;
    }
}

class Solution416_1 {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        int[][] dp = new int[nums.length][target + 1];
        //dp[i][j]代表可装物品为0-i，背包容量为j的情况下，背包内容量的最大价值

        //初始化,dp[0][j]的最大价值nums[0](if j > weight[i])
        //dp[i][0]均为0，不用初始化
        for (int j = nums[0]; j <= target; j++) {
            dp[0][j] = nums[0];
        }
        //遍历物品，遍历背包
        //递推公式:
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) { // 每一个元素一定是不可重复放入，所以从大到小遍历
                //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                if (j >= nums[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
//        if(dp[target]==target){
//            return true;
//        }
//        return false;
        return dp[nums.length - 1][target] == target;
    }
}

/**
 * 1049. 最后一块石头的重量 II
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * <p>
 * //分成差不多相等的两堆，这样剩余最少
 */
class Solution1049 {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        int target = sum / 2;  //整除
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                //两种情况，要么放，要么不放
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - dp[target] - dp[target];
    }
}

class Solution1049_1 {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int s : stones) {
            sum += s;
        }

        int target = sum / 2;
        //初始化，dp[i][j]为可以放0-i物品，背包容量为j的情况下背包中的最大价值
        int[][] dp = new int[stones.length][target + 1];
        //dp[i][0]默认初始化为0
        //dp[0][j]取决于stones[0]
        for (int j = stones[0]; j <= target; j++) {
            dp[0][j] = stones[0];
        }

        for (int i = 1; i < stones.length; i++) {
            for (int j = 1; j <= target; j++) {//注意是等于
                if (j >= stones[i]) {
                    //不放:dp[i - 1][j] 放:dp[i - 1][j - stones[i]] + stones[i]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[stones.length - 1][target]);
        return (sum - dp[stones.length - 1][target]) - dp[stones.length - 1][target];
    }
}

/**
 * 494目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * //目标和可以转化为 left-right=target  left+right=sum
 * left-（sum-left)=target  left = (target+sum)/2;
 */
class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if ((target + sum) % 2 == 1) {
            return 0;
        }
        if (Math.abs(target) > sum) {
            return 0;
        }
        int left = (target + sum) / 2;
        if (left < 0) {
            left = -left;
        }
        int[] dp = new int[left + 1];   //和为j时，有几种方案
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[left];
    }
}

/**
 * 474.一和零
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 */
class Solution474 {
    public int findMaxForm(String[] strs, int m, int n) {
        //最多有i个0和j个1的strs的最大子集的大小为dp[i][j]。
        int[][] dp = new int[m+1][n+1];
        //dp[i][j] 可以由前一个strs里的字符串推导出来，strs里的字符串有zeroNum个0，oneNum个1。
        //dp[i][j] 就可以是 dp[i - zeroNum][j - oneNum] + 1。
        //字符串的zeroNum和oneNum相当于物品的重量,字符串本身的个数相当于物品的价值（value[i]）
        //这就是一个典型的01背包！ 只不过物品的重量有了两个维度而已。

        for (String s : strs) {     //遍历物品
            int oneNum = 0, zeroNum = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            //倒序遍历 从后往前遍历
            for (int i = m; i >= zeroNum; i--) {
                for(int j=n;j>=oneNum;j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }

        }
        return dp[m][n];
    }
}
/**
 518. 零钱兑换 II
 */
//class Solution518 {
//    public int change(int amount, int[] coins) {
//
//    }
//}