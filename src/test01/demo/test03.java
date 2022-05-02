package test01.demo;

//双指针


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class test03 {
    public static void main(String[] args) {
        Solution031 solution031 = new Solution031();
        String ans = solution031.minWindow("ADOBECODEBANC","ABC");
        System.out.println(ans);
    }
}

//class Solution031 {
//    public int removeElement(int[] nums, int val) {
//        int slowIndex = 0, fastIndex = 0;
//        for (fastIndex = 0; fastIndex < nums.length; fastIndex++) {
//            if (nums[fastIndex] != val) {
//                nums[slowIndex] = nums[fastIndex];
//                slowIndex++;
//            }
//        }
//        return slowIndex;
//    }
//}
//
//class Solution031 {
//    public int removeDuplicates(int[] nums) {
//        int slowIndex = 1;
//        if(nums.length==0){
//            return 0;
//        }
//        for (int fastIndex = 1; fastIndex < nums.length; fastIndex++) {
//            if(nums[fastIndex] > nums[fastIndex-1]){
//                nums[slowIndex]=nums[fastIndex];
//                slowIndex++;
//            }
//        }
//        return slowIndex;
//    }
//}

//class Solution031 {
//    public void moveZeroes(int[] nums) {
//        int slowIndex=0,fastIndex=0;
//        for(fastIndex=0;fastIndex<nums.length;fastIndex++){
//            if(nums[fastIndex]!=0){
//                nums[slowIndex]=nums[fastIndex];
//                slowIndex++;
//            }
//        }
//        for(int i=slowIndex;i<nums.length;i++){
//            nums[i]=0;
//        }
//    }
//}

//class Solution032{
//    public void moveZeroes(int[] nums){
//        int left=0,right=0;
//        while (right<nums.length){
//            if(nums[right]!=0){
//                swap(nums,left,right);
//                left++;
//            }
//            right++;
//        }
//    }
//
//    public void swap(int[] nums, int left, int right){
//        int temp = nums[left];
//        nums[left] = nums[right];
//        nums[right]=temp;
//    }
//}


//class Solution033 {
//    public boolean backspaceCompare(String s, String t) {
//        int i=s.length()-1,j=t.length()-1;
//        int skipS=0, skipT=0;
//
//        while(i>=0 || j>=0){
//            while(i>=0){
//                if(s.charAt(i)=='#'){
//                    skipS++;
//                    i--;
//                }else if(skipS>0){
//                    skipS--;
//                    i--;
//                }else{
//                    break;
//                }
//            }
//            while(j>=0){
//                if(t.charAt(j)=='#'){
//                    skipT++;
//                    j--;
//                }else if(skipT>0){
//                    skipT--;
//                    j--;
//                }else{
//                    break;
//                }
//            }
//            if(i>=0&&j>=0){
//                if(s.charAt(i)!=t.charAt(j)){
//                    return false;
//                }
//            }else{
//                if(i>=0||j>=0){
//                    return false;
//                }
//            }
//            j--;
//            i--;
//        }
//        return true;
//    }
//}


//class Solution031 {
//    public int[] sortedSquares(int[] nums) {
//        int[] ans = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            ans[i]=nums[i]*nums[i];
//        }
//        Arrays.sort(ans);
//        return ans;
//    }
//}

//class Solution031 {
//    public int minSubArrayLen(int s, int[] nums) {
//        int result = Integer.MAX_VALUE;
//        int sum=0;
//        int subLength =0;
//        for (int i = 0; i < nums.length; i++) {  //设置子序列的起点
//            sum = 0;
//            for (int j = i; j <nums.length ; j++) {  //设置子序列的终点
//                sum +=nums[j];
//                if(sum >= s){
//                    subLength=j-i+1;
//                    result = result<subLength?result:subLength;
//                    break;
//                }
//            }
//        }
//        return result == Integer.MAX_VALUE?0:result;
//    }
//}

//
//class Solution031 {
//    public int minSubArrayLen(int s, int[] nums) {
//        int result =Integer.MAX_VALUE;
//        int sum=0;
//        int i=0;   //起始位置
//        int subLength = 0;
//        for(int j=0;j<nums.length;j++){
//            sum += nums[j];
//            while(sum >= s){
//                subLength = (j-i+1);
//                result = result < subLength ? result :subLength;
//                sum -=nums[i++];    //移动起始位置
//            }
//        }
//        return result == Integer.MAX_VALUE ?0:result;
//    }
//}

//class Solution031 {
//    public int totalFruit(int[] tree) {
//        int ans = 0, i = 0;   //左指针
//        Counter count = new Counter();
//        for (int j = 0; j < tree.length; ++j) {   //右指针
//            count.add(tree[j], 1);   //添加数组中每一个元素的键值对   数组元素为key  出现次数为value
//            while (count.size() >= 3) {    // HashMap中元素的数量（键值对的数量）  大于等于3时
//                count.add(tree[i], -1);       //开始处理左指针 ，并且出现次数减一
//                if (count.get(tree[i]) == 0)     //如果数组中此元素个数减到0
//                    count.remove(tree[i]);      //删除键值对
//                i++;                          //移动左指针
//            }
//
//            ans = Math.max(ans, j - i + 1);  //得到结果
//        }
//
//        return ans;
//    }
//}
//
//class Counter extends HashMap<Integer, Integer> {
//    public int get(int k) {
//        return containsKey(k) ? super.get(k) : 0;        //获得指定key对应的value
//    }
//
//    public void add(int k, int v) {
//        put(k, get(k) + v);                   //添加键值对，，，出现k的次数
//    }
//}

class Solution031 {
    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();   //记录窗口中的字符和出现次数

    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;  //每次匹配符合时子串的长度  左指针和右指针
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            //可能window中一个字符出现的次数要多于我们要比对的那个字符串中字符的个数
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {//这里检查左指针是否可以收缩窗口，注意左指针可以收缩窗口的条件在于：窗口中包含了字符t
                //能够走到这里就说明已经符合要求了,但是要找到全局最小的子串，所以每次都要判断一下
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                //如果window中的左边出现了t中的字符，直接减1，然后看是否还能满足完全覆盖的要求
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR); //ansR==-1 说明没有符合的，就返回空字符串
    }
    //用于检测是否窗口中是否完全覆盖了子串
    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }
}

