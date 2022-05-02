package TanXin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 贪心问题
 */
public class test02 {
    public static void main(String[] args) {
//        Solution406 solution406 = new Solution406();
//        int[][] people={{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
//        solution406.reconstructQueue(people);
        Solution763 solution763 = new Solution763();
        solution763.partitionLabels("ababcbacadefegdehijhklij");
    }
}

/**
 * 455.分发饼干
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
 * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值
 */
//胃口值g    饼干尺寸s
// 思路1：优先考虑饼干，小饼干先喂饱小胃口
class Solution455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int count = 0;     //满足的数量
        //遍历饼干
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (s[i] >= g[start]) {
                start++;
                count++;
            }
        }
        return count;
    }
}

// 思路2：优先考虑胃口，先喂饱大胃口
class Solution455_1 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int start = s.length - 1;
        int count = 0;     //满足的数量
        //遍历胃口
        for (int i = g.length - 1; i >= 0; i--) {
            if (start >= 0 && g[i] <= s[start]) {
                start--;
                count++;
            }
        }
        return count;
    }
}

/**
 * 376. 摆动序列
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
 * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 */
class Solution376 {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int curDiff = 0;  // 当前一对差值
        int preDiff = 0; // 前一对差值
        int result = 1;  // 记录峰值个数，序列默认序列最右边有一个峰值
        for (int i = 0; i < nums.length; i++) {
            curDiff = nums[i + 1] - nums[i];
            // 出现峰值
            if ((curDiff > 0 && preDiff <= 0) || (preDiff >= 0 && curDiff < 0)) {
                result++;
                preDiff = curDiff;
            }
        }
        return result;
    }
}

/**
 * 53. 最大子序和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 */
class Solution53 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
//            if (count > result) {// 取区间累计的最大值（相当于不断确定最大子序终止位置）
//                result = count;
//            }
            result = Math.max(result, count);
            if (count <= 0) {
                count = 0;  // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
        return result;
    }
}

/**
 * 122.买卖股票的最佳时机II
 * 给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。
 * 在每一天，你可能会决定购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以购买它，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * <p>
 * 那么根据prices可以得到每天的利润序列：(prices[i] - prices[i - 1]).....(prices[1] - prices[0])。
 * 局部最优：收集每天的正利润，全局最优：求得最大利润。
 */
class Solution122 {
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }
}

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 */
class Solution55 {
    public boolean canJump(int[] nums) {
        //覆盖范围, 初始覆盖范围应该是0，因为下面的迭代是从下标0开始的
        int cover = 0;
        if (nums.length == 1) {
            return true;  // 只有一个元素，就是能达到
        }
        //在覆盖范围内更新最大的覆盖范围
        for (int i = 0; i <= cover; i++) {  // 注意这里是小于等于cover
            cover = Math.max(i + nums[i], cover);
            if (cover >= nums.length - 1) {  // 说明可以覆盖到终点了
                return true;
            }
        }
        return false;
    }
}

/**
 * 45.跳跃游戏II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 */
//所以真正解题的时候，要从覆盖范围出发，不管怎么跳，覆盖范围内一定是可以跳到的，以最小的步数增加覆盖范围，覆盖范围一旦覆盖了终点，得到的就是最小步数！
//这里需要统计两个覆盖范围，当前这一步的最大覆盖和下一步最大覆盖。
class Solution45 {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        int curDistance = 0;    // 当前覆盖最远距离下标
        int ans = 0;            // 记录走的最大步数
        int nextDistance = 0;   // 下一步覆盖最远距离下标
        for (int i = 0; i < nums.length; i++) {
            nextDistance = Math.max(nums[i] + i, nextDistance);  // 更新下一步覆盖最远距离下标
            if (i == curDistance) {                         // 遇到当前覆盖最远距离下标
                if (curDistance != nums.length - 1) {       // 如果当前覆盖最远距离下标不是终点
                    ans++;                                  // 需要走下一步
                    curDistance = nextDistance;             // 更新当前覆盖最远距离下标（相当于加油了）
                    if (nextDistance >= nums.length - 1) {
                        break; // 下一步的覆盖范围已经可以达到终点，结束循环
                    }
                } else {
                    break;                               // 当前覆盖最远距离下标是集合终点，不用做ans++操作了，直接结束
                }
            }
        }
        return ans;
    }
}

class Solution45_1 {
    public int jump(int[] nums) {
        int curDistance = 0;    // 当前覆盖的最远距离下标
        int ans = 0;            // 记录走的最大步数
        int nextDistance = 0;   // 下一步覆盖的最远距离下标
        for (int i = 0; i < nums.length - 1; i++) { // 注意这里是小于nums.size() - 1，这是关键所在
            nextDistance = Math.max(nums[i] + i, nextDistance); // 更新下一步覆盖的最远距离下标
            if (i == curDistance) {                 // 遇到当前覆盖的最远距离下标
                curDistance = nextDistance;         // 更新当前覆盖的最远距离下标
                ans++;
            }
        }
        return ans;
    }
}

/**
 * 1005.K次取反后最大化的数组和
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 */
class Solution1005 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
//        nums = IntStream.of(nums)
//                .boxed().sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
//                .mapToInt(Integer::intValue).toArray();
//        Arrays.sort(nums,(o1,o2)->{return Math.abs((Integer) o2)-Math.abs((Integer) o1);});
        nums = IntStream.of(nums)
                .boxed().sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            //从前向后遍历，遇到负数将其变为正数，同时K--
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        // 如果K还大于0，那么反复转变数值最小的元素，将K用完
        if (k % 2 == 1) {
            nums[len - 1] = -nums[len - 1];
        }
        return Arrays.stream(nums).sum();
    }

    static boolean cmp(int a, int b) {
        return Math.abs(a) > Math.abs(b);
    }

}

class Solution1005_1 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        if (nums.length == 1) {
            return k % 2 == 0 ? nums[0] : -nums[0];
        }
        Arrays.sort(nums);
        int sum = 0;
        int idx = 0;
        for (int i = 0; i < k; i++) {
            if (i < nums.length - 1 && nums[idx] < 0) {
                nums[idx] = -nums[idx];
                if (nums[idx] >= Math.abs(nums[idx + 1])) {
                    idx++;
                }
                continue;
            }
            nums[idx] = -nums[idx];
        }
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }
}

/**
 * 134. 加油站
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 */
class Solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < cost.length; i++) {
            int rest = gas[i] - cost[i];
            int index = (i + 1) % cost.length;
            while (rest > 0 && index != i) {  //模拟以i为起点行驶一圈
                rest += gas[index] - cost[index];
                index = (index + 1) % cost.length;   //前进一步
            }
            // 如果以i为起点跑一圈，剩余油量>=0，返回该起始位置
            if (rest >= 0 && index == i) {
                return i;
            }
        }
        return -1;
    }
}

class Solution134_1 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int min = 0;  // 从起点出发，油箱里的油量最小值
        for (int i = 0; i < gas.length; i++) {
            int rest = gas[i] - cost[i];
            curSum += rest;
            if (curSum < min) {
                min = curSum;
            }
        }

        if (curSum < 0) return -1;
        if (min >= 0) return 0;
        for (int i = gas.length - 1; i >= 0; i--) {
            int rest = gas[i] - cost[i];
            min += rest;
            if (min >= 0) {
                return i;
            }
        }
        return -1;
    }
}

//那么局部最优：当前累加rest[j]的和curSum一旦小于0，起始位置至少要是j+1，因为从j开始一定不行。全局最优：找到可以跑一圈的起始位置。
class Solution134_2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {  // 当前累加rest[i]和 curSum一旦小于0
                start = i + 1;  // 起始位置更新为i+1
//                start=(i + 1) % gas.length   //前进一步
                curSum = 0;     // curSum从0开始
            }
        }
        if (totalSum < 0) return -1; // 说明怎么走都不可能跑一圈了
        return start;
    }
}

/**
 * 135. 分发糖果
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */

/**
 * 分两个阶段
 * 1、起点下标1 从左往右，只要 右边 比 左边 大，右边的糖果=左边 + 1
 * 2、起点下标 ratings.length - 2 从右往左， 只要左边 比 右边 大，此时 左边的糖果应该 取本身的糖果数（符合比它左边大） 和 右边糖果数 + 1 二者的最大值，这样才符合 它比它左边的大，也比它右边大
 */
class Solution135 {
    public int candy(int[] ratings) {
        int[] candyVec = new int[ratings.length];
        candyVec[0] = 1;
        //// 从前向后
        //右边评分大于左边的情况（也就是从前向后遍历）
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candyVec[i] = candyVec[i - 1] + 1;
            } else {
                candyVec[i] = 1;
            }
        }
        //再确定左孩子大于右孩子的情况（从后向前遍历）
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
            }
        }
        //所以就取candyVec[i + 1] + 1 和 candyVec[i] 最大的糖果数量，candyVec[i]只有取最大的才能既保持对左边candyVec[i - 1]的糖果多，也比右边candyVec[i + 1]的糖果多
        int result = 0;
        for (int i = 0; i < candyVec.length; i++) {
            result += candyVec[i];
        }
        return result;
    }
}

/**
 * 860.柠檬水找零
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * 注意，一开始你手头没有任何零钱。
 * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 */
class Solution860 {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0, twenty = 0;
        for (int bill : bills) {
            // 情况一
            if (bill == 5) five++;
            // 情况二
            if (bill == 10) {
                if (five <= 0) {
                    return false;
                }
                ten++;
                five--;
            }
            // 情况三
            if (bill == 20) {
                // 优先消耗10美元，因为5美元的找零用处更大，能多留着就多留着
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                    twenty++; // 其实这行代码可以删了，因为记录20已经没有意义了，不会用20来找零
                } else if (five >= 3) {
                    five -= 3;
                    twenty++; // 同理，这行代码也可以删了
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

/**
 * 406.根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 */
class Solution406 {
    public int[][] reconstructQueue(int[][] people) {
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });
        LinkedList<int[]> que = new LinkedList<>();
        for (int[] p : people) {
            que.add(p[1], p);
        }
        return que.toArray(new int[people.length][]);
    }
}

/**
 * 452. 用最少数量的箭引爆气球
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 */
class Solution452 {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        int result = 1;            // points 不为空至少需要一支箭
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {      // 气球i和气球i-1不挨着，注意这里不是>=
                result++; // 需要一支箭
            } else {  // 气球i和气球i-1挨着
                points[i][1] = Math.min(points[i - 1][1], points[i][1]); // 更新重叠气球最小右边界
            }
        }
        return result;
    }
}

/**
 * 435. 无重叠区间
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 */
class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 按照区间右边界排序
//        Arrays.sort(intervals, (a, b) -> {
//            if (a[0] == a[0]) return a[1] - b[1];
//            return a[0] - b[0];
//        });
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        int count = 1; // 记录非交叉区间的个数
        int end = intervals[0][1]; // 记录区间分割点
        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0]) {     //分割线 小于 下一个左边界starti
                end = intervals[i][1];    //下一个分割点 下一个的右边界 endi
                count++;    //没有交叉
            }
        }
        return intervals.length - count;
    }
}

//根据射箭的修改 只修改了两处
class Solution435_1 {
    public int findMinArrowShots(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        int result = 1;            // points 不为空至少需要一支箭
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[i - 1][1]) {      // 气球i和气球i-1不挨着，注意这里不是>=   只修改了这里
                result++; // 需要一支箭
            } else {  // 气球i和气球i-1挨着
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]); // 更新重叠气球最小右边界
            }
        }
        return intervals.length - result;    //还有这里
    }
}

// 按照区间左边界排序     只修改了排序方式 其他都没变
class Solution435_2 {
    public int findMinArrowShots(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        int result = 1;            // points 不为空至少需要一支箭
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= intervals[i - 1][1]) {      // 气球i和气球i-1不挨着，注意这里不是>=   只修改了这里
                result++; // 需要一支箭
            } else {  // 气球i和气球i-1挨着
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]); // 更新重叠气球最小右边界
            }
        }
        return intervals.length - result;    //还有这里
    }
}

/**
 * 763.划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 */

class Solution763 {
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new LinkedList<>();
        int[] edge = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) { // 统计每一个字符最后出现的位置
            edge[chars[i] - 'a'] = i;  //从a-z 分别对应下标为0-25
        }
        int left = -1;
        int right = 0;
        for (int i = 0; i < chars.length; i++) {
            right = Math.max(right, edge[chars[i] - 'a']); // 找到字符出现的最远边界
            if (i == right) {
                list.add(right - left);
                left = i;
            }
        }
        return list;
    }
}

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
class Solution56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        List<int[]> result = new LinkedList<>();

        int start = intervals[0][0];
        int length = intervals.length;
        for (int i = 1; i < length; i++) {
            if (intervals[i][0] > intervals[i - 1][1]) {
                result.add(new int[]{start, intervals[i - 1][1]});
                start = intervals[i][0];
            } else {
                intervals[i][1] = Math.max(intervals[i][1], intervals[i - 1][1]);
            }
        }
        result.add(new int[]{start, intervals[length - 1][1]});
        return result.toArray(new int[result.size()][]);
    }
}

/**
 * 738.单调递增的数字
 * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
 * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
 */
//遇到strNum[i - 1] > strNum[i]的情况，让strNum[i - 1]--，然后strNum[i]给为9，可以保证这两位变成最大单调递增整数。
class Solution738 {
    public int monotoneIncreasingDigits(int n) {
        if (n == 0) {
            return 0;
        }
//        String strNum1 = String.valueOf(n);
//        char[] strNum = strNum1.toCharArray();
        char[] strNum = Integer.toString(n).toCharArray();
        // flag用来标记赋值9从哪里开始
        // 设置为这个默认值，为了防止第二个for循环在flag没有被赋值的情况下执行
        int flag = strNum.length;
        for (int i = strNum.length - 1; i > 0; i--) {
            if (strNum[i - 1] > strNum[i]) {
                flag = i;
                strNum[i - 1]--;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strNum.length; i++) {
            if (strNum[i] == '0' && i == 0) {
                continue; //防止出现09这样的情况
            }
            if (i >= flag) {
                res.append('9');
            } else {
                res.append(strNum[i]);
            }
        }
//        for (int i = flag; i < strNum.length; i++) {
//            strNum[i] = 9;
//        }
        return Integer.parseInt(res.toString());
    }
}

/**
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 */

/**
 * 情况一：收获利润的这一天并不是收获利润区间里的最后一天（不是真正的卖出，相当于持有股票），所以后面要继续收获利润。
 * 情况二：前一天是收获利润区间里的最后一天（相当于真正的卖出了），今天要重新记录最小价格了。
 * 情况三：不作操作，保持原有状态（买入，卖出，不买不卖）
 */
class Solution714 {
    public int maxProfit(int[] prices, int fee) {
        int result = 0;
        int minPrice = prices[0]; // 记录最低价格
        for (int i = 1; i < prices.length; i++) {
            // 情况二：相当于买入
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }

            // 情况三：保持原有状态（因为此时买则不便宜，卖则亏本）
            if (prices[i] >= minPrice && prices[i] <= minPrice + fee) {
                continue;
            }
            // 计算利润，可能有多次计算利润，最后一次计算利润才是真正意义的卖出
            if (prices[i] > minPrice + fee) {
                result += prices[i] - minPrice - fee;
                minPrice = prices[i] - fee; // 情况一，这一步很关键
            }
        }
        return result;
    }
}

class Solution714_1 {
    public int maxProfit(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int sum = 0;
        for (int p : prices) {
            if (p + fee < buy) {
                buy = p + fee;
            } else if (p > buy) {
                sum += p - buy;
                buy = p;
            }
        }
        return sum;
    }
}
/**
 * 968.监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 */
 // Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {
    }
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//0：该节点无覆盖
//1：本节点有摄像头
//2：本节点有覆盖
class Solution968 {
    public int minCameraCover(TreeNode root) {
        if (traversal(root)==0){ // root 无覆盖
            result++;
        }
        return result;
    }
    int result=0;
    int traversal(TreeNode cur){
        // 空节点，该节点有覆盖
        if(cur==null){
            return 2;
        }
        int left=traversal(cur.left);  // 左    后续遍历 从下向上遍历
        int right = traversal(cur.right);  // 右
         // 情况1
        // 左右节点都有覆盖  则该结点没有被覆盖0，，，，，，
        if (left == 2 && right == 2) {
            return 0;
        }

        // 情况2   有一个结点没有覆盖，就得增加一个摄像头 该位置增加摄像头1
        // left == 0 && right == 0 左右节点无覆盖
        // left == 1 && right == 0 左节点有摄像头，右节点无覆盖
        // left == 0 && right == 1 左节点有无覆盖，右节点摄像头
        // left == 0 && right == 2 左节点无覆盖，右节点覆盖
        // left == 2 && right == 0 左节点覆盖，右节点无覆盖
        if (left == 0 || right == 0) {
            result++;
            return 1;
        }
        // 情况3  有一个结点有摄像头，就表示已经覆盖2 ，
        // left == 1 && right == 2 左节点有摄像头，右节点有覆盖
        // left == 2 && right == 1 左节点有覆盖，右节点有摄像头
        // left == 1 && right == 1 左右节点都有摄像头
        // 其他情况前段代码均已覆盖
        if (left == 1 || right == 1) {
            return 2;
        }
        // 以上代码我没有使用else，主要是为了把各个分支条件展现出来，这样代码有助于读者理解
        // 这个 return -1 逻辑不会走到这里。
        return -1;
    }
}