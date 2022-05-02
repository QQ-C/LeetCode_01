package labuladong;

import javax.naming.LinkRef;
import javax.print.attribute.EnumSyntax;
import javax.print.attribute.standard.NumberUp;
import java.net.Inet4Address;
import java.util.*;

public class huisu {
    public static void main(String[] args) {
        Solution40 solution = new Solution40();
        int[] num = {10, 2, 1, 7, 6, 1, 5};
        solution.combinationSum2(num, 8);
    }
}

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
//存储每个结点的结果
class Solution78 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        backTracking(nums, 0);
        return result;
    }

    void backTracking(int[] nums, int index) {
        result.add(new ArrayList<>(path));
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            backTracking(nums, index + 1);
            path.removeLast();
        }
    }
}

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 */
class Solution77 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n <= 0) return result;
        backTracking(n, k, 1);
        return result;
    }

    void backTracking(int n, int k, int index) {
        if (path.size() == k) {
            result.add(new LinkedList<>(path));
            return;
        }
        for (int i = index; i <= n; i++) {
            path.addLast(i);
            backTracking(n, k, i + 1);
            path.removeLast();
        }
    }
}

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
class Solution46 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return result;
        }
        used = new boolean[nums.length];
        backTracking(nums, used);
        return result;
    }

    void backTracking(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new LinkedList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            backTracking(nums, used);
            path.removeLast();
            used[i] = false;
        }
    }
}

class Solution46_1 {
    List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    public List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i])) {  ////////////////////////
                continue;
            }
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }
}

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */
class Solution90 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        backTracking(nums, 0);
        return result;
    }

    void backTracking(int[] nums, int startIndex) {
        result.add(new LinkedList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            backTracking(nums, i + 1);
            path.removeLast();
        }
    }
}

/**
 * 40. 组合总和 II
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合。
 */
class Solution40 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0 || target <= 0) {
            return result;
        }
        Arrays.sort(candidates);
        backTracking(candidates, target, 0, 0);
        return result;
    }

    void backTracking(int[] candidates, int target, int sum, int start) {
        if (sum == target) {
            result.add(new LinkedList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            sum += candidates[i];
            backTracking(candidates, target, sum, i + 1);
            path.removeLast();
            sum -= candidates[i];
        }
    }
}

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列
 */
class Solution47 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backTracking(nums);
        return result;
    }

    void backTracking(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i]=true;
            backTracking(nums);
            backTracking(nums);
            path.removeLast();
            used[i]=false;
        }
    }
}
/**
 * 39. 组合总和
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
class Solution39 {
    List<List<Integer>> result=new LinkedList<>();
    LinkedList<Integer> path=new LinkedList<>();
    int pathSum=0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates.length==0){
            return result;
        }
        backTracking(candidates,0,target);
        return result;
    }
    void backTracking(int[] nums,int start,int target){
        if(pathSum==target){
            result.add(new LinkedList<>(path));
            return;
        }
        if(pathSum>target){
            return;
        }
        for (int i = start; i <nums.length ; i++) {
          pathSum+=nums[i];
          path.addLast(nums[i]);
          backTracking(nums,i,target);
          pathSum-=nums[i];
          path.removeLast();
        }
    }
}