package test01.demo;

//二分法

public class test02 {
    public static void main(String[] args) {
//        Solution02 solution02 = new Solution02();
//        int ans[] = solution02.searchRange(new int[]{0, 2, 5, 7,7,7,8, 9}, 7);
//        for (int i = 0; i < ans.length; i++) {
//            System.out.println(ans[i]);
//   }
        Solution03 solution03 = new Solution03();
        int ans = solution03.mySqrt(8);
        System.out.println(ans);
    }
}
//左闭右闭
//class Solution02{
//    public int search(int[] nums, int target){
//        if(target<nums[0]||target>nums[nums.length-1]){
//            return -1;
//        }
//        int left=0, right=nums.length-1;
//        while(left<=right){
//            int mid=left+((right-left)/2);
//            if(nums[mid]==target){
//                return mid;
//            }else if(nums[mid]<target){
//                left=mid+1;
//            }else if(nums[mid]>target){
//                right = mid-1;
//            }
//        }
//        return -1;
//    }
//}

//左闭右开
//class Solution02 {
//    public int search(int[] nums, int target) {
//        int left = 0, right = nums.length;
//        while (left < right) {
//            int mid = left + ((right - left) / 2);
//            if (nums[mid] == target) {
//                return mid;
//            } else if (nums[mid] < target) {
//                left = mid + 1;
//            } else if (nums[mid] > target) {
//                right = mid ;
//            }
//        }
//        return -1;
//    }
//}

//class Solution02 {
//    public int searchInsert(int[] nums, int target) {
//        int left=0;
//        int right = nums.length-1;
//        while(left<=right){
//            int mid = left +(right-left)/2;
//            if(nums[mid]==target){
//                return mid;
//            }else if(nums[mid]>target){
//                right = mid-1;
//            }else if(nums[mid]<target){
//                left=mid+1;
//            }
//        }
//        return right+1;
//    }
//}

class Solution02 {
    public int[] searchRange(int[] nums, int target) {
        int leftBorder = getLeftBorder(nums, target);
        int rightBorder = getRightBorder(nums, target);
        // 情况一
        if (leftBorder == -2 || rightBorder == -2) return new int[]{-1, -1};
        // 情况三
        if (rightBorder - leftBorder > 1) return new int[]{leftBorder + 1, rightBorder - 1};
        // 情况二
        return new int[]{-1, -1};

    }
    // 二分查找，寻找target的右边界（不包括target）
    // 如果rightBorder为没有被赋值（即target在数组范围的左边，例如数组[3,3]，target为2），为了处理情况一
    int getRightBorder(int[] nums,int target){
        int left = 0;
        int right = nums.length - 1; // 定义target在左闭右闭的区间里，[left, right]
        int rightBorder = -2; // 记录一下rightBorder没有被赋值的情况
        while (left <= right) { // 当left==right，区间[left, right]依然有效
            int middle = left + ((right - left) / 2);// 防止溢出 等同于(left + right)/2
            if (nums[middle] > target) {
                right = middle - 1; // target 在左区间，所以[left, middle - 1]
            } else { // 当nums[middle] == target的时候，更新left，这样才能得到target的右边界
                left = middle + 1;
                rightBorder = left;
            }
        }
        return rightBorder;
    }
    // 二分查找，寻找target的左边界leftBorder（不包括target）
    // 如果leftBorder没有被赋值（即target在数组范围的右边，例如数组[3,3],target为4），为了处理情况一
    int getLeftBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 定义target在左闭右闭的区间里，[left, right]
        int leftBorder = -2; // 记录一下leftBorder没有被赋值的情况
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (nums[middle] >= target) { // 寻找左边界，就要在nums[middle] == target的时候更新right
                right = middle - 1;
                leftBorder = right;
            } else {
                left = middle + 1;
            }
        }
        return leftBorder;
    }

}



class Solution03 {
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
