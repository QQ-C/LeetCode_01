package jianzhiOffer.offer3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
class myDuplicate_0 {
    public int findRepeatNumber(int[] nums) {
        int length = nums.length;
        if (nums == null || length <= 0) {
            return -1;
        }
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {   //判断该值是不是等于 该值对应下标的值 如果等于 则重复
                    return nums[i];
                }
                //交换
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}

//
class myDuplicate_1 {
    public int findRepeatNumber(int[] nums) {
        int length = nums.length;
        if (nums == null || length <= 1) {
            return -1;
        }
        Arrays.sort(nums);
        for (int i = 1; i < length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }
}

//
class myDuplicate_2 {
    public int findRepeatNumber(int[] nums) {
        int length = nums.length;
        if (nums == null || length <= 1) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.get(i) == null) {
                map.put(i, 1);
            } else {
                return i;
            }
        }
        return -1;
    }
}

/**
 * 不修改数组  使用二分法
 */
//class myDuplicate{
//    public static void main(String[] args) {
//        int[] num={3,4,2,0,0,1};
//        myDuplicate_3 myDuplicate_3=new myDuplicate_3();
//        int n=myDuplicate_3.findRepeatNumber(num);
//    }
//}
class myDuplicate_3 {
    public int findRepeatNumber(int[] nums) {
        int length = nums.length;
        if (nums == null || length <= 1) {
            return -1;
        }
        int left = 1;
        int right = length - 1;
        while (right >= left) {
            int middle = (right - left) / 2 + left;
            int count = countRange(nums, left, middle);
            if (right == left) {
                if (count > 1) {
                    return left;
                } else {
                    break;
                }
            }
            if (count > (middle - left + 1)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    //返回个数
    int countRange(int[] nums, int left, int right) {
        if (nums == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                count++;
            }
        }
        return count;
    }
}
