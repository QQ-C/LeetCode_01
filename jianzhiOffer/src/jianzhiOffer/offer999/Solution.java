package jianzhiOffer.offer999;

import java.math.BigDecimal;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
//        Solution11_quiksort solution11_quiksort = new Solution11_quiksort();
////        int[] nums = {1, 2, 3, 7, 4, 9, 5, 7, 6};
////        solution11_quiksort.quiksort(nums, 9, 0, 9 - 1);
        Solution17_1 solution17_1 = new Solution17_1();
        solution17_1.printNumbers(3);
    }
}

/**
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */
class Solution09 {
}

class CQueue {
    Stack<Integer> A, B;

    public CQueue() {
        A = new Stack<>();
        B = new Stack<>();
    }

    public void appendTail(int value) {
        A.add(value);
    }

    public int deleteHead() {
        if (!B.isEmpty()) {
            return B.pop();
        }
        if (A.isEmpty()) {
            return -1;
        }
        while (!A.isEmpty()) {
            B.add(A.pop());
        }
        return B.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */
class Solution10 {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] = dp[i] % 1000000007;
        }
        return dp[n];
    }
}

class Solution10_1 {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0;
        int b = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }
}

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */
class Solution10_2 {
    public int numWays(int n) {
        //n---n-1 +   n-2
        //1---1    2----2

        if (n <= 0) {
            return 1;
        }
        if (n > 0 && n < 3) {
            return n;
        }
        int pre1 = 1;
        int pre2 = 2;
        int fib = 0;
        for (int i = 3; i < n + 1; i++) {
            fib = (pre1 + pre2) % 1000000007;
            pre1 = pre2;
            pre2 = fib;
        }
        return fib;
    }
}

/**
 * 快速排序
 */

class Solution11_quiksort {
    //交换数组中的两个值
    void Swap(int[] num, int a, int b) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }

    //快速排序，小数字在左边，大数字在右边
    int Partition(int[] data, int length, int start, int end) {
        Random random = new Random();
        if (data == null || length <= 0 || start < 0 || end >= length) {
            return -1;
//            throw new Exception("Incalid");
        }
        // 尽量避免极端情况，每次都是以最后一个数字作为基准值，在start和end之间随机出一个数
        //选择最后一个
//        int index = end;
        int index = start + random.nextInt(end - start + 1);
        // 将比较的值放在数组的最后
        Swap(data, index, end);
        // 定义一个指针，指向所有换序之后所有比基准值都小的数据的最右边，最后最指向基准值应该在位置
        int small = start - 1;

        for (int i = start; i < end; i++) {
            if (data[i] < data[end]) {
                small++;
                // 避免原来就是有序数列的无用交换
                if (small != i) {
                    Swap(data, small, i); //交换
                }
            }
        }
        //把基准值放在中间
        ++small;
        Swap(data, small, end);
        return small;
    }

    void quiksort(int[] data, int length, int start, int end) {
        if (start == end) {
            return;
        }
        int index = Partition(data, length, start, end);
        if (index > start) {
            quiksort(data, length, start, index - 1);
        }
        if (index < end) {
            quiksort(data, length, index + 1, end);
        }
    }
}

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 */
//二分查找
class Solution11 {
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int length = numbers.length;

        int index1 = 0;
        int index2 = length - 1;

        int middle = index1;
        while (numbers[index1] >= numbers[index2]) {
            if (index2 - index1 == 1) {
                middle = index2;
                break;
            }
            middle = (index1 + index2) / 2;

            //如果指向的三个数字相等，只能顺序查找
            if (numbers[index1] == numbers[index2] && numbers[middle] == numbers[index1]) {
                return MinInorder(numbers, index1, index2);
            }

            if (numbers[middle] >= numbers[index1]) {
                index1 = middle;
            } else if (numbers[middle] <= numbers[index2]) {
                index2 = middle;
            }
        }
        return numbers[middle];
    }

    int MinInorder(int[] num, int index1, int index2) {
        int result = num[index1];
        for (int i = index1 + 1; i <= index2; i++) {
            if (result > num[i]) {
                result = num[i];
            }
        }
        return result;
    }
}

class Solution11_1 {
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        int length = numbers.length;
        int i = 0;
        int j = length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (numbers[mid] < numbers[j]) {
                j = mid;
            } else if (numbers[mid] == numbers[j]) {
                j--;
            } else {
                i = mid + 1;
            }
        }
        return numbers[i];
    }
}

/**
 * 剑指 Offer 12. 矩阵中的路径
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 */
//回溯法 路径可以被看成是一个栈
// 深度优先搜索（DFS）+ 剪枝
class Solution12 {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        boolean res = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                res = dfs(board, words, i, j, 0);
                if (res == true) {
                    return res;
                }
            }
        }
        return res;
    }

    /**
     * 递归参数： 当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k
     * 终止条件：
     * 返回 falsefalse ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ） 。
     * 返回 truetrue ： k = len(word) - 1 ，即字符串 word 已全部匹配
     * 递推工作：
     * 标记当前矩阵元素： 将 board[i][j] 修改为 空字符 '' ，代表此元素已访问过，防止之后搜索时重复访问。
     * 搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，使用 或 连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ），并记录结果至 res 。
     * 还原当前矩阵元素： 将 board[i][j] 元素还原至初始值，即 word[k] 。
     * 返回值： 返回布尔量 res ，代表是否搜索到目标字符串。
     * 回溯；
     */
    private boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word[k]) {
            return false;
        }
        if (k == word.length - 1) {
            return true;
        }
        board[i][j] = '\0';  //空 代表使用过
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }
}

/**
 * 剑指 Offer 13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，
 * 机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */
//  回溯判断能到达多少个格子
// 实现判断能否进入格子  -- 实现位数之和
class Solution13 {
    int k, m, n;
    boolean[][] used;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.used = new boolean[m][n];
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m || j >= n || k < (getDigitSum(i) + getDigitSum(j)) || used[i][j]) {
            return 0;
        }
        used[i][j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    }

    int getDigitSum(int number) {
        int s = 0;
        while (number != 0) {
            s += number % 10;
            number = number / 10;
        }
        return s;
    }

}

//广度优先搜索方法
class Solution13_1 {
    public int movingCount(int m, int n, int k) {
        boolean[][] used = new boolean[m][n];
        int res = 0;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.addLast(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] x = queue.poll();
            int i = x[0], j = x[1];
            //出界，不满足条件，已经使用过
            if (i >= m || j >= n || k < (getDigitSum(i) + getDigitSum(j)) || used[i][j]) {
                continue;
            }
            used[i][j] = true;
            res++;
            queue.addLast(new int[]{i + 1, j});
            queue.addLast(new int[]{i, j + 1});
        }
        return res;
    }

    int getDigitSum(int number) {
        int s = 0;
        while (number != 0) {
            s += number % 10;
            number = number / 10;
        }
        return s;
    }

}

/**
 * 剑指 Offer 14- I. 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * ////
 * 动态规划
 * dp[i]  长度为i的绳子，剪开之后乘积最大是dp[i]
 * 剪一段j   1、j*（i-j）  2、j*dp[i-j]
 * dp[1]=1
 * dp[2]=1
 * dp[3]=2
 */

class Solution14 {
    public int cuttingRope(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}

class Solution14_1 {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        int a = n % 3;
        int b = n / 3;
        if (a == 0) return (int) Math.pow(3, b);
        if (a == 1) return (int) Math.pow(3, b - 1) * 4;
        return (int) Math.pow(3, b) * 2;
    }
}

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
 * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */
//取模不能用dp
class Solution14_3 {
    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        long mul = 1;
        while (n > 4) {
            //每次乘积后取余防止大数
            mul = mul * 3 % 1000000007;
            n -= 3;
        }
        return (int) (mul * n % 1000000007);
    }
}

/*
* 剑指 Offer 15. 二进制中1的个数
编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
*/
class Solution15 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res++;
            n = n & (n - 1);
        }
        return res;
    }
}

/*
剑指 Offer 16. 数值的整数次方
实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
* */
class Solution16 {
    private boolean g_invalid_input = false;

    /**
     * 全面但不够高效的解法
     *
     * @param base
     * @param exponent
     * @return
     */
    public double power(double base, int exponent) {
        // 0的0次方没有意义
        if (doubleCompare(base, 0.0) == 0 && exponent == 0) {
            g_invalid_input = false;
            return 0.0;
        }
        int absExponent = Math.abs(exponent);
        double result = powerWithPositiveExponent(base, absExponent);
        if (exponent < 0) {
            result = 1.0 / result;
        }
        return result;
    }


    /**
     * 指数为正时，得到的整数次方
     *
     * @param base
     * @param exponent
     * @return
     */
    public double powerWithPositiveExponent(double base, int exponent) {
        double result = 1.0;
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * 比较两个浮点型大小
     *
     * @param a
     * @param b
     * @return
     */
    public int doubleCompare(double a, double b) {
        BigDecimal data1 = new BigDecimal(a);
        BigDecimal data2 = new BigDecimal(b);
        return data1.compareTo(data2);
    }
}

class Solution16_1 {
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) {  //判断奇偶性
                res *= x;
            }
            x *= x;
            b = b >> 1; //除以2
        }
        return res;
    }
}

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 * <p>
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 * <p>
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 */

/**
 * 解题思路
 * 在字符串上模拟数字加法的解法
 * 我们用字符串来表示 n ，首先把字符串的每一个数字都转化为‘0’，然后每次为字符串表示的数字加1，再打印出来。
 * 因此我们需要做两件事：一是在字符串表示的数字上模拟加法；而是把字符串表示的数字打印出来。
 *
 * 把问题转成数字排列的解法
 * 如果我们在数字前面补零，就会发现 n 位十进制数其实就是 n 个从 0 到 9 的全排列。
 * 也就是说，我们把数字的每一个为都从 0 到 9 排列一遍，就得到了所有的十进制数。
 * 只是在打印的时候，排在前面的0不打印出来罢了。
 */

/**
 *         int number = (int) Math.pow(10, n);
 *         int[] res=new int(number);
 *         for (int i = 1; i < number; i++) {
 *             System.out.println(i);
 *             res[i]=i+1;
 *          }
 *          return res;
 *             没有考虑n是一个大数，没有意识到这是一个大数问题。
 *             没有考虑非法输入，如负数。
 */
//全排列
class Solution17 {
    //先定义几个需要用到的全局变量
    //初始化结果数组res
    int[] res;  //Math.pow返回的是double
    char[] nums;
    char[] loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int count = 0;  //数组计数索引
    int x = 0;    //x表示递归到第几层

    public int[] printNumbers(int n) {
        nums = new char[n];
        res = new int[(int) Math.pow(10, n) - 1];
        dfs(x, n);    //递归 全排列
        return res;
    }

    private void dfs(int x, int n) {  //x表示递归到第几层
        //递归终止条件，x==n,此时要向结果数组res中添加数字
        if (x == n) {
            String str = String.valueOf(nums); //把字符数组转换为字符串
            int temp = Integer.parseInt(str);  //把字符串转换为整数
            if (temp != 0) { //跳过0
                res[count] = temp;  //把字符串转换为整数后添加到结果数组中
                count++;   //计数索引加1
                return;
            }
            return;
        }
        for (char ch : loop) {  //对第x层的数字进行固定，然后继续向下一层递归
            nums[x] = ch;
            dfs(x + 1, n);
        }
    }
}

//字符串表示
class Solution17_1 {
    // 在字符串上模拟数字加法的解法
    public int[] printNumbers(int n) {
        if (n <= 0) {
            return new int[0];
        }
//        int[] res=new int[(int)Math.pow(10,n)-1];
        char[] number = new char[n];   //初始为空
        initCharArray(number);   //进行初始化 全部为 '0'
        while (!increment(number)) {    //每次加一的操作
            printNumber(number);   //去除0 输出的操作
        }
        return new int[0];
    }

    //字符串表示的数字上模拟加法
    private boolean increment(char[] number) {
        //是否超出999....
        boolean isoverFlow = false;
        //进位数
        int takeOver = 0;
        for (int i = number.length - 1; i >= 0; i--) {
            int sum = number[i] - '0' + takeOver;
            if (i == number.length - 1) {
                sum++;
            }
            if (sum >= 10) {
                if (i == 0) {
                    isoverFlow = true;
                } else {
                    sum -= 10;
                    takeOver = 1;
                    number[i] = (char) ('0' + sum);
                }
            } else {
                number[i] = (char) ('0' + sum);
                break;
            }
        }
        return isoverFlow;
    }

    //根据字符串打印出数字
    private void printNumber(char[] number) {
        // 默认字符串不以0开始
        boolean isBegining0 = true;
        for (int i = 0; i < number.length; i++) {
            if (isBegining0 && number[i] != '0') {
                isBegining0 = false;
            }
            if (!isBegining0) {
                System.out.print(number[i]);
            }
        }
        System.out.println();
    }

    //初始化字符数组
    public void initCharArray(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '0';
        }
    }
}

/**
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * 返回删除后的链表的头节点。
 * 注意：此题对比原题有改动
 * */

/**
 * Definition for singly-linked list.
 * */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }
}

class Solution18 {
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode cur = head;
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }
        if (cur.next.val == val) {
            cur.next = cur.next.next;
        }
        return head;
    }
}

//添加虚拟头节点
class Solution18_1 {
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }
        if (cur.next.val == val) {
            cur.next = cur.next.next;
        }
        return dummy.next;
    }
}

/*
删除链表中重复的结点
* 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
*/

/**
 * 解题思路
 * 定义两个指针（preNode，node），一个用来指向不被删除的前一个节点，一个用来遍历链表
 * 定义删除标志，如果前后两个值相等就需要删除，不等就不需要删除，改变preNode
 * 在删除的时候需要连续删除，还要考虑preNode是或否存在，如果不存在它就是头指针
 */
//添加虚拟头节点
class Solution18_2 {
    public ListNode deleteDuplication(ListNode pHead) {
        //防止非法输入
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        //定义两个指针
        ListNode preNode = null;
        ListNode curNode = pHead;
        while (curNode != null) {
            ListNode next = curNode.next;
            // 删除标志位
            boolean needDeleted = false;
            if (next != null && next.val == curNode.val) {
                needDeleted = true;
            }
            if (!needDeleted) {
                preNode = curNode;
                curNode = curNode.next;
            } else {
                int value = curNode.val;
                ListNode toBeDelete = curNode;
                while (toBeDelete != null && toBeDelete.val == value) {
                    next = toBeDelete.next;
                    toBeDelete = next;
                    // 考虑头指针是否存在
                    if (preNode == null) {
                        pHead = next;
                    } else {
                        preNode.next = next;
                    }
                    curNode = next;
                }
            }
        }
        return pHead;
    }
}

/**
 * 剑指 Offer 19. 正则表达式匹配
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 */

/**
 * 解题思路
 * 用递归的思想解决，对于一次判断有以下情况：
 *
 * 当模式中的第二个字符不是“*”时：
 *
 * 如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
 * 如果字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。
 * 当模式中的第二个字符是“*”时：
 *
 * 如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。
 * 如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
 * 模式后移2字符，相当于x*被忽略；
 * 字符串后移1字符，模式后移2字符；
 * 字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位；
 */
//正则表达式匹配
class Solution19 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        return matchCore(str, pattern, 0, 0);
    }

    /**
     * @param str 字符串
     * @param pattern 模式
     * @param i  str的下标
     * @param j  patter的下标
     * @return
     */
    public boolean matchCore(char[] str, char[] pattern, int i, int j) {
        //终止条件
        if (i == str.length && j == pattern.length) {
            return true;
        }
        // 当模式不足以配置字符串时
        if (i < str.length && j == pattern.length) {
            return false;
        }
        // 当模式中的第二个字符是“*”时：
        if (j + 1 < pattern.length && pattern[j + 1] == '*') {
            // 如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式
            if (i != str.length && pattern[j] == str[i] || (pattern[j] == '.' && i != str.length)) {
                return // 字符串后移1字符，模式后移2字符
                        matchCore(str, pattern, i + 1, j + 2)
                                // 字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位
                                || matchCore(str, pattern, i + 1, j)
                                // 模式后移2字符，相当于x*被忽略
                                || matchCore(str, pattern, i, j + 2);

            } else {
                return matchCore(str, pattern, i, j + 2);
            }
        }
        // 如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的
        if ((i != str.length && str[i] == pattern[j]) || (pattern[j] == '.' && i != str.length)) {
            return matchCore(str, pattern, i + 1, j + 1);
        }
        // 如果字符串第一个字符和模式中的第一个字符相不匹配，直接返回false
        return false;
    }
}

//动态规划
class Solution19_1 {
    public boolean isMatch(String s, String p) {
        int m = s.length() + 1;
        int n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        //// 初始化首行
        for (int j = 2; j < n; j++) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (p.charAt(j - 1) == '*') {
                    if (dp[i][j - 2]) dp[i][j] = true;                                            // 1.
                    else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) dp[i][j] = true; // 2.
                    else if (dp[i - 1][j] && p.charAt(j - 2) == '.') dp[i][j] = true;             // 3.
                } else {
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) dp[i][j] = true;  // 1.
                    else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') dp[i][j] = true;         // 2.
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

/**
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *
 * 数值（按顺序）可以分成以下几个部分：
 *
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分数值列举如下：
 *
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 *
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 */
//有限状态自动机
class Solution20 {
    public boolean isNumber(String s) {
        Map[] states = {
                new HashMap<>() {{
                    put(' ', 0);
                    put('s', 1);
                    put('d', 2);
                    put('.', 4);
                }}, // 0.
                new HashMap<>() {{
                    put('d', 2);
                    put('.', 4);
                }},                           // 1.
                new HashMap<>() {{
                    put('d', 2);
                    put('.', 3);
                    put('e', 5);
                    put(' ', 8);
                }}, // 2.
                new HashMap<>() {{
                    put('d', 3);
                    put('e', 5);
                    put(' ', 8);
                }},              // 3.
                new HashMap<>() {{
                    put('d', 3);
                }},                                        // 4.
                new HashMap<>() {{
                    put('s', 6);
                    put('d', 7);
                }},                           // 5.
                new HashMap<>() {{
                    put('d', 7);
                }},                                        // 6.
                new HashMap<>() {{
                    put('d', 7);
                    put(' ', 8);
                }},                           // 7.
                new HashMap<>() {{
                    put(' ', 8);
                }}                                         // 8.
        };
        int p = 0;
        char t;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') t = 'd';
            else if (c == '+' || c == '-') t = 's';
            else if (c == 'e' || c == 'E') t = 'e';
            else if (c == '.' || c == ' ') t = c;
            else t = '?';
            if (!states[p].containsKey(t)) return false;
            p = (int) states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;
    }
}

/**
 * 非正则表达式解法
 * 首先仔细表示数值的字符串的模式A[.[B]][e|EC]或者.B[e|EC]。其中A为数值的整数部分，B紧跟着小数点为数值的小数部分，C紧跟着’e’或者‘E’为数值的指数部分。在小数里可能没有数值的整数部分。例如，小数.123等于0.123。因此A部分不是必须，如果一个数没有整数部分，那么它的小数部分不能为空。
 *
 * 上诉A和C都是可能以‘+’或者‘-’开头的0-9的数字串；B也是0-9的数位串，单前面不能有正负号。
 *
 * 判断一个字符串是否符合上述模式时，首先尽可能多地扫描0-9的数位（有可能在起始处有‘+’或者‘-’），也就是前面模式中表示数值的A部分，如果遇到小数点‘.’，则开始扫描数值小数部分的B部分。如果遇到’e’或者‘E’，则开始扫描数值指数的C部分。
 */
class Solution20_1 {
    private int i = 0;

    public boolean isNumber(String s) {
        char[] str = s.trim().toCharArray();
        if (str == null || str.length < 0) {
            return false;
        }
        // 1. 扫描A

        boolean numberic = scanInteger(str);
        // 2. 扫描B
        if (i < str.length && str[i] == '.') {
            i++;
            // 小数后面可以没有数字
            numberic = scanUnSignedInteger(str) || numberic;
        }
        // 3. 扫描C
        if (i < str.length && (str[i] == 'e' || str[i] == 'E')) {
            i++;
            // 出现e/E 后面必须跟数字
            numberic = scanInteger(str) && numberic;
        }

        return numberic && i == str.length;
    }

    private boolean scanInteger(char[] str) {
        if (i < str.length && (str[i] == '+' || str[i] == '-')) {
            i++;
        }
        return scanUnSignedInteger(str);
    }

    /**
     * 扫描无符号整数部分（在起始处不可能有‘+’或者‘-’）
     * @param str
     * @return
     */
    private boolean scanUnSignedInteger(char[] str) {
        // 用于对比是否符合
        int temp = i;
        while (i < str.length && str[i] >= '0' && str[i] <= '9') {
            i++;
        }
        return i > temp;
    }
}

class Solution20_2 {
    /**
     * 表示数值的字符串
     *
     */
    public class Solution2 {
        /**
         * 正则表达式解法
         * @param s
         * @return
         */
        public boolean isNumbr(String s) {
            // 防止特殊输入
            if (s == null || s.length() < 0) {
                return false;
            }
            return s.matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
        }
    }
}

class Solution20_3 {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false; // s为空对象或 s长度为0(空字符串)时, 不能表示数值
        boolean isNum = false, isDot = false, ise_or_E = false; // 标记是否遇到数位、小数点、‘e’或'E'
        char[] str = s.trim().toCharArray();  // 删除字符串头尾的空格，转为字符数组，方便遍历判断每个字符
        for (int i = 0; i < str.length; i++) {
            if (str[i] >= '0' && str[i] <= '9') isNum = true; // 判断当前字符是否为 0~9 的数位
            else if (str[i] == '.') { // 遇到小数点
                if (isDot || ise_or_E) return false; // 小数点之前可以没有整数，但是不能重复出现小数点、或出现‘e’、'E'
                isDot = true; // 标记已经遇到小数点
            } else if (str[i] == 'e' || str[i] == 'E') { // 遇到‘e’或'E'
                if (!isNum || ise_or_E) return false; // ‘e’或'E'前面必须有整数，且前面不能重复出现‘e’或'E'
                ise_or_E = true; // 标记已经遇到‘e’或'E'
                isNum = false; // 重置isNum，因为‘e’或'E'之后也必须接上整数，防止出现 123e或者123e+的非法情况
            } else if (str[i] == '-' || str[i] == '+') {
                if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E')
                    return false; // 正负号只可能出现在第一个位置，或者出现在‘e’或'E'的后面一个位置
            } else return false; // 其它情况均为不合法字符
        }
        return isNum;
    }
}

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 */
class Solution21 {
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return new int[0];
        }
        int startIndext = 0;
        int lastIndex = nums.length - 1;
        while (startIndext < lastIndex) {
            while (startIndext < lastIndex && !isEven(nums[startIndext])) {
                startIndext++;
            }
            while (startIndext < lastIndex && isEven(nums[lastIndex])) {
                lastIndex--;
            }
            if (startIndext < lastIndex) {
                swapArray(nums, startIndext, lastIndex);
            }
        }
        return nums;
    }

    void swapArray(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private boolean isEven(int num) {
//        return (num&1)!=1;  //偶数
        return (num & 1) == 0;  //偶数
    }
}

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 */
class Solution22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        ListNode fastNode = head;
        ListNode slowNode = head;
        for (int i = 0; i < k - 1; i++) {
            if (fastNode.next != null) {
                fastNode = fastNode.next;
            } else {
                return null;
            }
        }
        while (fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }
        return slowNode;
    }
}

class Solution22_1 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        ListNode fastNode = head;
        ListNode slowNode = head;
        for (int i = 0; i < k; i++) { ////
//            if (fastNode.next != null) {      //这是不对的
            fastNode = fastNode.next;
//            } else {
//                return null;
//            }
        }
        while (fastNode != null) { ////
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }
        return slowNode;
    }
}

/**剑指 Offer 24. 反转链表
 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 */
class Solution24 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode lastNode = null;
        ListNode curNode = head;
        ListNode firstNode = null;
        while (curNode != null) {
            firstNode = curNode.next;
            curNode.next = lastNode;
            lastNode = curNode;
            curNode = firstNode;
        }
        return lastNode;
    }
}

/**
 * 剑指 Offer 25. 合并两个排序的链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 */
//递归
class Solution25 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode dummyNode = null;
        if (l1.val < l2.val) {
            dummyNode = l1;
            dummyNode.next = mergeTwoLists(l1.next, l2);
        } else {
            dummyNode = l2;
            dummyNode.next = mergeTwoLists(l1, l2.next);
        }
        return dummyNode;
    }
}

//迭代
class Solution25_1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }
}
/**
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//递归
class Solution26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        boolean result = false;
//        if(A!=null&&B!=null){
        if (A.val == B.val) {
            result = doesTreeAhasTreeB(A, B);
        }
        if (!result) {
            result = isSubStructure(A.left, B);
        }
        if (!result) {
            result = isSubStructure(A.right, B);
        }
//        }
        return result;
    }

    boolean doesTreeAhasTreeB(TreeNode TreeA, TreeNode TreeB) {
        if (TreeB == null) {
            return true;
        }
        if (TreeA == null) {
            return false;
        }
        if (TreeA.val != TreeB.val) {
            return false;
        }
        return doesTreeAhasTreeB(TreeA.left, TreeB.left) && doesTreeAhasTreeB(TreeA.right, TreeB.right);
    }
}

/**
 * 剑指 Offer 27. 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */
class Solution27 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        //交换子节点
        TreeNode tempNode = root.right;
        root.right = root.left;
        root.left = tempNode;
        if (root.left != null) {
            mirrorTree(root.left);
        }
        if (root.right != null) {
            mirrorTree(root.right);
        }
        return root;
    }
}

class Solution27_1 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode tempNode = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tempNode);
        return root;
    }
}

/**
 * 剑指 Offer 28. 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。
 * 如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
class Solution28 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        }
        if (left != null && right == null) {
            return false;
        }
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null && left.val == right.val) {
            return compare(left.left, right.right) && compare(left.right, right.left);
        }
        return false;
    }
}


class Solution28_1 {
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : recur(root.left, root.right);
    }

    boolean recur(TreeNode L, TreeNode R) {
        if (L == null && R == null) return true;
        if (L == null || R == null || L.val != R.val) return false;
        return recur(L.left, R.right) && recur(L.right, R.left);
    }
}

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 */
class Solution29 {
    int[] result;
    int res = 0;

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int row = matrix.length;
        int col = matrix[0].length;
        result = new int[row * col];
        int start = 0;
        while (row > start * 2 && col > start * 2) {
            printMatrixInCircle(matrix, row, col, start);
            start++;
        }
        return result;
    }

    void printMatrixInCircle(int[][] nums, int row, int col, int start) {
        int endX = col - 1 - start;  //最后一列
        int endY = row - 1 - start;  //最后一行
        //从左到右
        for (int i = start; i <= endX; i++) {
            result[res++] = nums[start][i];
        }
        //上到下
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                result[res++] = nums[i][endX];
            }
        }
        //右--左
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                result[res++] = nums[endY][i];
            }
        }
        //下--上
        if (start < endY - 1 && start < endX) {
            for (int i = endY - 1; i >= start + 1; i--) {
                result[res++] = nums[i][start];
            }
        }
    }
}

class Solution29_1 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if (++l > r) break;
        }
        return res;
    }
}

class Solution29_3 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int[] order = new int[total];
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order[i] = matrix[row][column];
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }
}

class Solution29_4 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[] order = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}

/**
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
//使用一个栈存储数据
//使用一个辅助栈 存储最小值
//用一个变量存储最小值（方便比较）

class MinStack30 {
    /** initialize your data structure here. */
    Stack<Integer> stack1;  //数据栈
    Stack<Integer> stack2;  //辅助栈

    public MinStack30() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        if (stack2.isEmpty() || stack2.peek() >= x) {
            stack2.push(x);
        }
        stack1.push(x);
    }

    public void pop() {
        if (!stack2.isEmpty() && stack1.peek() <= stack2.peek()) {
            stack2.pop();
        }
        stack1.pop();      //移除
    }

    public int top() {
        return stack1.isEmpty() ? 0 : stack1.peek();
    }

    public int min() {
        return stack2.peek();  //返回不移除
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */

/**
 *
 * 剑指 Offer 31. 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。
 * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，
 * 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 */

/**
 * 解题思路
 * 建立一个辅助栈
 * 当栈为空或者栈的栈顶元素不为弹出序列需要的弹出元素时，将压入序列继续压入栈直到栈顶为弹出元素
 * 当栈顶元素为弹出元素时，则弹出该元素
 * 当压入序列的所有元素都压入栈时，依然找不到匹配的弹出元素，则不是弹出序列
 */
class Solution31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null && popped == null) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                j++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 */
/**
 * 解题思路
 * 这道题如果能想到队列，编码还是很简单的。
 *
 * 先将头节点放入队列
 * 当队列不为空的时候，从队列取出一个节点输出
 * 判断取出的节点左右节点是否为空，不为空则将他们加入队列
 * 循环至队列为空跳出
 */
//广度优先遍历  先进先出 队列

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution32 {
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();   //找结点
        queue.add(root);
        ArrayList<Integer> ans = new ArrayList<>();   //存储结点的值
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            ans.add(temp.val);
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}
/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution32_2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int len = queue.size();   //当层的长度
            for (int i = 0; i < len; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}

/**
 剑指 Offer 32 - III. 从上到下打印二叉树 III
 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，
 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * //之字形打印二叉树
 * 奇数层 从左到右
 * 偶数层 从右到左
 *
 * 先进后出
 * 两个栈实现
 */
class Solution32_3 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if (res.size() % 2 == 0) {
                    temp.addLast(node.val); // 偶数层 -> 队列头部
                } else {
                    temp.addFirst(node.val);  // 奇数层 -> 队列尾部
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(temp);
        }
        return res;
    }
}

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 */

/**
 * 解题思路
 * 先找到数组最后一个数字（根节点的值），然后就可以将数组分成两部分：
 * 第一部分是左子树，他们都比根节点的值小；第二部分是右子树，他们都比根节点的值大。
 * 然后就是递归验证左右子树合法（满足二叉搜索树的条件）即可
 */
//后序遍历最后一个结点是根节点
//在二叉搜索树中左子树结点的值小于根节点的值
//右子树结点的值大于根节点的值
//递归判断左右子树是不是二叉搜索树
class Solution33 {
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null) {
            return true;
        }
        return dfs(postorder, 0, postorder.length - 1);
    }

    boolean dfs(int[] postorder, int left, int rootIndex) {
        if (left >= rootIndex) {
            return true;
        }
        int p = left;
        while (postorder[p] < postorder[rootIndex]) p++;
        int m = p;
        while (postorder[p] > postorder[rootIndex]) p++;
        return p == rootIndex && (dfs(postorder, left, m - 1) && (dfs(postorder, m, rootIndex - 1)));
    }
}

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
 * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
//前序遍历，递归，回溯
class Solution34 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return result;
        }
        dfs(root, target);
        return result;
    }

    void dfs(TreeNode root, int target) {
        path.addLast(root.val);
        target -= root.val;
        //终止条件
        if (target == 0 && root.left == null && root.right == null) {
            result.add(new LinkedList<>(path));
        }
        if (root.left != null) {
            dfs(root.left, target);
        }
        if (root.right != null) {
            dfs(root.right, target);
        }
        path.removeLast();
        //path.remove(path.size()-1);
    }
}

/**
 * 剑指 Offer 35. 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 */
/*
// Definition for a Node.
 */
class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution35 {
    public Node copyRandomList(Node head) {
        if(head==null){
            return head;
        }
        //创建一个组合链表 复制原来的每个链表的值放在原值后面 A -> A' -> B ->B'
        Node cur =head;
        while(cur!=null){
            Node temp=new Node(cur.val);
            temp.next=cur.next;
            cur.next=temp;
            cur=temp.next;
        }
        //构建random  A' 指向 B'
        cur=head;
        while(cur!=null){
            if(cur.random!=null){
                cur.next.random=cur.random.next;
            }
            cur=cur.next.next;
        }

        //拆分
        //奇数位置上的是原链表 偶数位置上的就是复制出来的链表
        Node res=head.next;
        cur=head.next;
        Node pre=head;
        while(cur.next!=null){
            pre.next=pre.next.next;   //原始链表
            cur.next=cur.next.next;   //复制出来的链表
            pre=pre.next;
            cur=cur.next;
        }
        pre.next=null;
        return res;
    }
}

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
//中序遍历 保证是有序的
//关键：设置一个末尾节点指针用来串联根节点的两侧连接
//先将左子树遍历完，然后末尾节点指针将指向左子树的最右侧,然后根节点的左指针就指向了末尾节点
//当右子树的最左侧遍历完之后，右子树的最左侧的节点的左指针就将指向根节点（此时的末尾节点）
//递归
class Solution36 {
    TreeNode pre,head;
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root==null){
            return root;
        }
        dfs(root);
        pre.right=head;
        head.left=pre;
        return head;
    }
    void dfs(TreeNode cur){
        if(cur==null){
            return;
        }
        dfs(cur.left);
        if(pre!=null){
            pre.right=cur;
        }else{
            head=cur;
        }
        cur.left=pre;
        pre=cur;
        dfs(cur.right);
    }
}
/**
 * 剑指 Offer 37. 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，
 * 详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，
 * 你也可以采用其他的方法解决这个问题。
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//确定序列化规则
// 如果我们根据前序遍历进行序列化，那么我们只要根据前序遍历解序列化就可以啦!!

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
class Codec37 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return "[]";
        }
        StringBuilder res=new StringBuilder("[");
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node =queue.poll();
            if(node!=null){
                res.append(node.val+",");
                queue.add(node.left);
                queue.add(node.right);
            }else {
                res.append("null,");
            }
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("[]")){
            return null;
        }
        String[] vals=data.substring(1,data.length()-1).split(",");
        TreeNode root =new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i=1;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(!vals[i].equals("null")){
                node.left=new TreeNode(Integer.valueOf(vals[i]));
                queue.add(node.left);
            }
            i++;
            if(!vals[i].equals("null")){
                node.right=new TreeNode(Integer.valueOf(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 */
//全排列问题   递归加回溯
class Solution38 {
    List<String> result=new ArrayList<>();
    char[] path;
    public String[] permutation(String s) {
        if(s.equals("null")){
            return new String[0];
        }
        path=s.toCharArray();
        backTracking(s,0);
        return result.toArray(new String[result.size()]);
    }
    void backTracking(String s,int index){
        if(index==path.length-1){
            result.add(String.valueOf(path));  // 添加排列方案
            return;
        }
        HashSet<Character> set=new HashSet<>();
        for (int i = index; i <path.length ; i++) {
            if(set.contains(path[i])) continue;  // 重复，因此剪枝
            set.add(path[i]);
            swap(i,index);    // 交换，将 c[i] 固定在第 index 位
            backTracking(s,index+1); // 开启固定第 x + 1 位字符
            swap(i,index);           // 恢复交换
        }
    }
    void swap(int a,int b){
        char temp=path[a];
        path[a]=path[b];
        path[b]=temp;
    }
}
//*********** 有重复 所以需要一个used数组    没写出来，没通过
//class Solution38_1 {
//    List<String> result=new ArrayList<>();
//    StringBuilder path=new StringBuilder();
//    public String[] permutation(String s) {
//        boolean[] used = new boolean[s.length()];
//        Arrays.fill(used,false);
//        char[] ch=s.toCharArray();
//        Arrays.sort(ch);
//        s=ch.toString();
//        if(s.equals("null")){
//            return new String[0];
//        }
//        backTracking(s,path,used);
//        return result.toArray(new String[result.size()]);
//    }
//    void backTracking(String s,StringBuilder path,boolean[] used){
//        if(path.length()==s.length()){
//            result.add(String.valueOf(path));  // 添加排列方案
//            return;
//        }
//
//        for (int i = 0; i < s.length(); i++) {
//            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
//            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
//            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
//
//            // 如果path中已有，则跳过
//           // if(contains(path,s.charAt(i))) continue;
//            if(i>0&&s.charAt(i)==s.charAt(i-1)&&used[i - 1] == false){
//                continue;
//            }
//            //如果同⼀树⽀nums[i]没使⽤过开始处理
//            if(used[i]==false){
//                used[i]=true;  ////标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
//                path.append(s.charAt(i));
//                backTracking(s,path,used);
//                path.deleteCharAt(path.length()-1);
//                used[i]=false;
//            }
//
//        }
//    }
//    boolean contains(StringBuilder path,char c){
//        for (int i = 0; i < path.length(); i++) {
//            if(path.charAt(i)==c){
//                return true;
//            }
//        }
//        return false;
//    }
//}

