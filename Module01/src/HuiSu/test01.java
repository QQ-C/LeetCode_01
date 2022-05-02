package HuiSu;

import javax.lang.model.type.ArrayType;
import javax.naming.InsufficientResourcesException;
import java.rmi.MarshalledObject;
import java.util.*;


/**
 * 回溯法
 */
public class test01 {
    public static void main(String[] args) {
        Solution332 solution013 = new Solution332();
        List<List<String>> tickets = new LinkedList<>();
        List<String> list1 = new LinkedList<>();
        list1.add("JFK");
        list1.add("SFO");
        List<String> list2 = new LinkedList<>();
        list2.add("JFK");
        list2.add("ATL");
        List<String> list3 = new LinkedList<>();
        list3.add("SFO");
        list3.add("ATL");
        List<String> list4 = new LinkedList<>();
        list4.add("ATL");
        list4.add("JFK");
        List<String> list5 = new LinkedList<>();
        list5.add("ATL");
        list5.add("SFO");

        tickets.add(list1);
        tickets.add(list2);
        tickets.add(list3);
        tickets.add(list4);
        tickets.add(list5);

        solution013.findItinerary(tickets);

    }
}

/**
 * 77. 组合问题
 * 1、给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
class Solution77 {
    //确定递归函数的返回值以及参数
    //需要两个全局变量 一个用来存放符合条件的单一结果，一个用来存放符合条件的集合
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return result;
    }

    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     *
     * @param n          1--n
     * @param k          k个数
     * @param startIndex startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    void backtracking(int n, int k, int startIndex) {
        //终止条件
        //路径数组中存放的个数 等于k个数
        if (path.size() == k) {
//            result.add(path);     //要开辟一个新的地址，不能直接输入path.
            result.add(new LinkedList<>(path));
            return;
        }
        //单层搜索过程
        //for循环用来横向遍历，递归的过程是纵向遍历。
//        for (int i = startIndex; i <= n; i++) { //控制树的横向遍历
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) { //控制树的横向遍历 剪枝
            path.add(i); //处理结点
            backtracking(n, k, i + 1); //递归：控制树的纵向遍历，注意下一层搜索从i+1开始
            path.removeLast();  //回溯
        }
    }

}

/**
 * * 216.组合总和III
 */
//1、给定两个整数 n 和 k，返回 1 ... 9中所有 和为n 的可能的 k 个数的组合。

class Solution216 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    void backtracking(int targetSum, int k, int sum, int startIndex) {
        if (sum > targetSum) {  //剪枝
            return;
        }
        //终止条件
        if (path.size() == k) {
            if (sum == targetSum) {
                result.add(new LinkedList<>(path));
            }
            return;
        }
        //单层搜索条件
        //9-(k-path.size())+1  剪枝
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            sum += i;
            path.add(i);
            backtracking(targetSum, k, sum, i + 1);  //注意下一层搜索从i+1开始
            sum -= i;   //回溯
            path.removeLast(); //回溯
        }
    }

    List<List<Integer>> combinationSum(int k, int n) {
        backtracking(n, k, 0, 1);
        return result;
    }
}

/**
 * 17.电话号码的字母组合
 */
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合
class Solution17 {
    //设置全局列表存储最后的结果
    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //处理
        backTracking(digits, numString, 0);
        return list;
    }

    // //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    StringBuilder temp = new StringBuilder();

    void backTracking(String digits, String[] numString, int num) {
        //如果num==digits的长度，那么终止
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的字符串
        //比如digits如果为"23",num 为0，则str表示2对应的 abc
        String str = numString[digits.charAt(num) - '0'];    //数字的 char-'0' 对应的就是该数字的int的值
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            backTracking(digits, numString, num + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

}

/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */

class Solution39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0);
        return result;
    }

    //
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    void backTracking(int[] candidates, int target, int sum, int startIndex) {
        //终止
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new LinkedList<>(path));
            return;
        }
        //单层
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {   // 如果 sum + candidates[i] > target 就终止遍历
            sum += candidates[i];         //暂时的和
            path.add(candidates[i]);    //放入一个数
            backTracking(candidates, target, sum, i);  // 关键点:不用i+1了，表示可以重复读取当前的数
            sum -= candidates[i];  // 回溯
            path.removeLast();  // 回溯
        }
    }
}

/**
 * 40.组合总和II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 */
class Solution40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        backTracking(candidates, target, 0, 0, used);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    //建立一个used数组，判断数据是否已经使用过
    void backTracking(int[] candidates, int target, int startIndex, int sum, boolean[] used) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new LinkedList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            // 要对同一树层使用过的元素进行跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == false) {
                continue;
            }
            sum += candidates[i];
            path.add(candidates[i]);
            used[i] = true;
            backTracking(candidates, target, i + 1, sum, used);  //这里是i+1，每个数字在每个组合中只能使用一次
            used[i] = false;
            sum -= candidates[i];
            path.removeLast();
        }
    }
}

class Solution05_1 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    //建立一个used数组，判断数据是否已经使用过
    void backTracking(int[] candidates, int target, int startIndex, int sum) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new LinkedList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            // 要对同一树层使用过的元素进行跳过
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            sum += candidates[i];
            path.add(candidates[i]);

            backTracking(candidates, target, i + 1, sum);  //这里是i+1，每个数字在每个组合中只能使用一次

            sum -= candidates[i];
            path.removeLast();
        }
    }
}

/**
 * 131.分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 */
class Solution131 {
    public List<List<String>> partition(String s) {
        backTracking(s, 0);
        return result;
    }

    List<List<String>> result = new ArrayList<>();
    LinkedList<String> path = new LinkedList<>();  // 放已经回文的子串

    void backTracking(String s, int startIndex) {
        // 如果起始位置已经大于s的大小，说明已经找到了一组分割方案了
        if (startIndex >= s.length()) {
            result.add(new LinkedList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i)) {   // 是回文子串
                String str = s.substring(startIndex, i + 1);   // 获取[startIndex,i]在s中的子串
                path.addLast(str);
            } else {
                continue;     // 如果不是则直接跳过
            }
            backTracking(s, i + 1);   // 寻找i+1为起始位置的子串
            path.removeLast();   // 回溯过程，弹出本次已经填在的子串
        }
    }

    boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}

/**
 * 93.复原IP地址
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 */
class Solution93 {
    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) {
            return result;
        }
        backTracking(s, 0, 0);
        return result;
    }

    List<String> result = new ArrayList<>();

    //startIndex一定是需要的，因为不能重复分割，记录下一层递归分割的起始位置。
    //还需要一个变量pointNum，记录添加逗点的数量。
    void backTracking(String s, int startIndex, int pointNum) {
        if (pointNum == 3) {   // 逗点数量为3时，分隔结束
            // 判断第四段子字符串是否合法，如果合法就放进result中
            if (isValid(s, startIndex, s.length() - 1)) {
                result.add(s);
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) { // 判断 [startIndex,i] 这个区间的子串是否合法
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);   // 在i的后面插入一个逗点
                pointNum++;
                backTracking(s, i + 2, pointNum);   // 插入逗点之后下一个子串的起始位置为i+2
                pointNum--;                         // 回溯
                s = s.substring(0, i + 1) + s.substring(i + 2);         // 回溯删掉逗点
            } else {
                break; // 不合法，直接结束本层循环
            }
        }
    }

    // 判断字符串s在左闭又闭区间[start, end]所组成的数字是否合法
    boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) { // 0开头的数字不合法
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 遇到非数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) {   // 如果大于255了不合法
                return false;
            }
        }
        return true;
    }
}

/**
 * 78.子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
class Solution78 {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        backTracking(nums, 0);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果

    void backTracking(int[] nums, int startIndex) {
        // 收集子集，要放在终止添加的上面，否则会漏掉自己
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        //求取子集问题，不需要任何剪枝！因为子集就是要遍历整棵树。
        for (int i = startIndex; i < nums.length; i++) {
            path.addLast(nums[i]); // 子集收集元素
            backTracking(nums, i + 1); // 注意从i+1开始，元素不重复取
            path.removeLast();   // 回溯
        }
    }
}

/**
 * 90.子集II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */
//使用used数组
class Solution90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            result.add(path);
            return result;
        }
        Arrays.sort(nums);
        used = new boolean[nums.length];       //默认是 false
        backTracking(nums, 0);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    boolean[] used;

    private void backTracking(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {    //  !used[i - 1]为true；；即 used[i - 1] 为false；即 used[i - 1]=0
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            backTracking(nums, i + 1);
            path.removeLast();
            used[i] = false;
        }
    }
}

//不使用used数组
class Solution90_1 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            result.add(path);
            return result;
        }
        Arrays.sort(nums);
        backTracking(nums, 0);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果

    private void backTracking(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));

        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            // // 跳过当前树层使用过的、相同的元素
            if (i > startIndex && nums[i] == nums[i - 1]) {    //used[i-1]=0
                continue;
            }
            path.addLast(nums[i]);
            backTracking(nums, i + 1);
            path.removeLast();
        }
    }
}

/**
 * 491.递增子序列
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 */
class Solution491 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        backTracking(nums, 0);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    void backTracking(int[] nums, int startIndex) {
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
            // 注意这里不要加return，要取树上的节点
        }
        int[] used = new int[201];// 这里使用数组来进行去重操作，题目说数值范围[-100, 100]
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) || (used[nums[i] + 100] == 1)) {//非空 当前数值小于上一个数值 或者本层已经使用过该数值 则跳过这层循环
                continue;
            }
            used[nums[i] + 100] = 1;  //// 记录这个元素在本层用过了，本层后面不能再用了
            path.addLast(nums[i]);
            backTracking(nums, i + 1);
            path.removeLast();
        }
    }
}

/**
 * 46.全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return result;
        }
        used = new boolean[nums.length];
        backTracking(nums, used);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    void backTracking(int[] nums, boolean used[]) {
        // 此时说明找到了一组
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) {
                continue;  // path里已经收录的元素，直接跳过
            }
            path.addLast(nums[i]);
            used[i] = true;
            backTracking(nums, used);
            path.removeLast();
            used[i] = false;
        }
    }
}

// 解法2：通过判断path中是否存在数字，排除已经选择的数字
class Solution46_1 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return result;
        backtrack(nums, path);
        return result;
    }

    public void backtrack(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            // 如果path中已有，则跳过
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            backtrack(nums, path);
            path.removeLast();
        }
    }
}

/**
 * 47.全排列II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
class Solution47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backTracking(nums, used);
        return result;
    }

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    void backTracking(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == true，说明同一树枝nums[i - 1]使用过
            // used[i - 1] == false，说明同一树层nums[i - 1]使用过
            // 如果同一树层nums[i - 1]使用过则直接跳过

            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            //如果同⼀树⽀nums[i]没使⽤过开始处理
            if (used[i] == false) {
                path.addLast(nums[i]);
                used[i] = true;      //标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
                backTracking(nums, used);
                path.removeLast();  //回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                used[i] = false;  //回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
            }

        }
    }
}

/**
 * 332.重新安排行程
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 * <p>
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * <p>
 * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出：["JFK","MUC","LHR","SFO","SJC"]
 * 。
 */
class Solution332 {

    private Deque<String> res = new LinkedList<>();   //存储结果

    //存储机场信息   <出发机场, map<到达机场, 航班次数>>   航班次数 用来标记 到达机场 是否使用过

    // 如果“航班次数”大于零，说明目的地还可以飞，如果如果“航班次数”等于零说明目的地不能飞了
    //可以说本题既要找到一个对数据进行排序的容器，而且还要容易增删元素，迭代器还不能失效。
    private Map<String, Map<String, Integer>> map = new HashMap<>();


    public List<String> findItinerary(List<List<String>> tickets) {
        //map 和 res 需要初始化
        for (List<String> t : tickets) {   // t :["MUC","LHR"]
            Map<String, Integer> temp;
            if (map.containsKey(t.get(0))) {     //是否包含 起始机场
                temp = map.get(t.get(0));     //获得了该起始机场 对应的 终点机场 和 航班次数
                temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);  //继续添加 该起始机场 对应的 终点机场 和 航班次数
            } else {
                temp = new TreeMap<>();    //升序Map
                temp.put(t.get(1), 1);    //放入终点机场
            }
            map.put(t.get(0), temp);    //（起始机场，（终点机场，航班次数）)
        }

        res.add("JFK");
        backTracking(tickets.size());
        return new ArrayList<>(res);
    }

    //ticketNum 表示有多少个航班
//有4个航班，那么只要找出一种行程，行程里的机场个数是5就可以了。
    private boolean backTracking(int ticketNum) {
        if (res.size() == ticketNum + 1) {    //找到所有的机场
            return true;
        }
        String last = res.getLast();  //  // 起始机场 "JFK"
        if (map.containsKey(last)) {   //防止出现null
            for (Map.Entry<String, Integer> target : map.get(last).entrySet()) {     ////起始机场的value（终点机场，航班次数）一个entrySet
                int count = target.getValue();           ///获得 该起始机场对应的 （终点机场 的 航班次数）
                if (count > 0) {                   // 记录到达机场是否飞过了
                    res.add(target.getKey());     // 该 起始机场 对应的 终点机场 添加进去
                    target.setValue(count - 1);  //  该起始机场对应的 终点机场的 航班次数减一
                    if (backTracking(ticketNum)) {
                        return true;
                    }
                    res.removeLast();
                    target.setValue(count);
                }
            }
        }
        return false;
    }

}

/**
 * 第51题. N皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 */
class Solution51 {
    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTracking(n, 0, chessboard);
        return result;
    }

    List<List<String>> result = new ArrayList<>();

    //参数n是棋盘的大小，然后用row来记录当前遍历到棋盘的第几层了。  row 行  col 列
    void backTracking(int n, int row, char[][] chessboard) {
        if (row == n) {
            result.add(Array2List(chessboard));
            return;
        }
        //递归深度就是row控制棋盘的行，每一层里for循环的col控制棋盘的列，一行一列，确定了放置皇后的位置。
        //每次都是要从新的一行的起始位置开始搜，所以都是从0开始。
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, chessboard, n)) { // 验证合法就可以放
                chessboard[row][col] = 'Q';   // 放置皇后
                backTracking(n, row + 1, chessboard);
                chessboard[row][col] = '.';  // 回溯，撤销皇后
            }
        }

    }

    public List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    boolean isValid(int row, int col, char[][] chessboard, int n) {
        // 检查列
        for (int i = 0; i < row; i++) {     // 这是一个剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }
        // 检查 45度角是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        // 检查 135度角是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
        //每一层递归，只会选for循环（也就是同一行）里的一个元素，所以不用去重了。
    }
}

/**
 * 37. 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 */
class Solution37 {
    public void solveSudoku(char[][] board) {
        backTracking(board);
    }
    //一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！
    boolean backTracking(char[][] board) {
        //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
        // 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！」

        for (int i = 0; i < board.length; i++) {        // 遍历行
            for (int j = 0; j < board[0].length; j++) { // 遍历列
                if (board[i][j] != '.'){      // 跳过原始数字
                    continue;
                }

                for (char k = '1'; k <= '9'; k++) {     // (i, j) 这个位置放k是否合适
                    if (isValid(i, j, k, board)) {
                        board[i][j] = k;                // 放置k
                        if (backTracking(board)){
                            return true;     // 如果找到合适一组立刻返回
                        }
                        board[i][j] = '.';              // 回溯，撤销k
                    }
                }
                return false;                 // 9个数都试完了，都不行，那么就返回false

                // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
            }
        }
        return true; // 遍历完没有返回false，说明找到了合适棋盘位置了
    }
    /**
     * 判断棋盘是否合法有如下三个维度:
     *     同行是否重复
     *     同列是否重复
     *     9宫格里是否重复
     */
    boolean isValid(int row, int col, char val,char[][] board) {
        for (int i = 0; i < 9; i++) { // 判断行里是否重复
            if (board[row][i] == val) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) { // 判断列里是否重复
            if (board[j][col] == val) {
                return false;
            }
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) { // 判断9方格里是否重复
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val ) {
                    return false;
                }
            }
        }
        return true;
    }
}