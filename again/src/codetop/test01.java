package codetop;


import java.util.*;

public class test01 {
    public static void main(String[] args) {
//        Solution03 solution03=new Solution03();
//       int a= solution03.lengthOfLongestSubstring("abcdebfgdbb");
//        System.out.println(a);
        Solution215_2 solution215_2 = new Solution215_2();
        int[] nums = {3, 2, 1, 5, 6, 4};
        solution215_2.findKthLargest(nums, 2);
    }
}
/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution206 {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}

class Solution206_1 {
    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode temp = null;
        temp = cur.next;// 先保存下一个节点
        cur.next = prev;// 反转
        // 更新prev、cur位置
        // prev = cur;
        // cur = temp;
        return reverse(cur, temp);
    }
}

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
class Solution03 {
    //    Scanner scan = new Scanner(System.in);
//        while(scan.hasNext()){
//        String str=scan.next();
//    }
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}

class Solution03_1 {
    //    Scanner scan = new Scanner(System.in);
//        while(scan.hasNext()){
//        String str=scan.next();
//    }
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * <p>
 * 思路：
 * 一、暴力解法，排序，然后输出k-1
 * 二、堆排序 动态 最小值堆
 * 三、快速排序 数组长度已知
 */
//快排
class Solution215 {
    public int findKthLargest(int[] nums, int k) {
        if (k < 0 || k > nums.length) {
            return Integer.MIN_VALUE;
        }
        int len = nums.length;
        return quickSelect(nums, 0, len - 1, len - k);
        //第k大，就是第len-k小
    }

    //交换 数组中两个值
    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    //分治  比标准值小的放在左边 大的放在右边 返回标准值下标
    int partition(int nums[], int left, int right, int pivot_index) {
        int pivot = nums[pivot_index];  //参考值
        swap(nums, pivot_index, right); //将这个值放在最后面
        int store_index = left;        //暂存轴值下标
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, store_index);
                store_index++;
            }
        }
        swap(nums, store_index, right);
        return store_index;
    }

    //快速选择  递归实现
    int quickSelect(int[] num, int left, int right, int k_smallest) {
        if (left == right) {
            return num[left];
        }
        Random random = new Random();
        int pivot_index = left + random.nextInt(right - left);  //随便选取一个值作为轴值
        pivot_index = partition(num, left, right, pivot_index);
        if (k_smallest == pivot_index) {
            return num[k_smallest];
        } else if (k_smallest < pivot_index) {
            return quickSelect(num, left, pivot_index - 1, k_smallest);
        } else {
            return quickSelect(num, pivot_index + 1, right, k_smallest);
        }
    }
}

//最小堆  上面是最小值
class Solution215_1 {
    public int findKthLargest(int[] nums, int K) {
        //最小值堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> (n1 - n2));
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > K) {
                heap.poll();
            }
        }
        return heap.poll();  //前K大元素中的最小值
    }
}

//最小堆  上面是最小值  自己构建堆
//一般升序选择大顶堆  降序采用小顶堆
//堆排序 构建大顶堆
class Solution215_2 {
    public int findKthLargest(int[] nums, int k) {
        //排序 根和最后一个叶子结点交换 长度减一
        int heapSize = nums.length;//建树
        buildMaxheap(nums, heapSize);//砍树
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    //构建堆 从最后一个非叶子结点
    void buildMaxheap(int[] num, int heapSize) {
        for (int i = heapSize / 2; i >= 0; i--) {       //i=(heapSize-2)/2    从下到上遍历每个非叶子节点
            maxHeapify(num, i, heapSize);
        }
    }

    //堆排序
    public void maxHeapify(int[] num, int cur_index, int heapSize) {
        int left = cur_index * 2 + 1;
        int right = cur_index * 2 + 2;
        int largest = cur_index;
        if (left < heapSize && num[left] > num[largest]) {
            largest = left;
        }
        if (right < heapSize && num[right] > num[largest]) {
            largest = right;
        }
        if (largest != cur_index) {
            swap(num, cur_index, largest);
            maxHeapify(num, largest, heapSize);
        }
    }

    //排序
    void swap(int[] num, int a, int b) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }
}
/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        ListNode tail = dummyNode;
        while (tail.next != null) {
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k ; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummyNode.next;
                }
            }
            ListNode start=pre.next;
            ListNode nex = tail.next;
            tail.next=null;
            pre.next=myReverse(start);
            start.next=nex;
            pre=start;
            tail=pre;
        }
        return dummyNode.next;
    }

    public ListNode myReverse(ListNode head) {
        ListNode prev =null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nex = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nex;
        }
        return prev;
    }
}

class Solution25_1{
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            //剩余数量小于k的话，则不需要反转。
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(head, tail);
        //下一轮的开始的地方就是tail
        head.next = reverseKGroup(tail, k);

        return newHead;
    }

    /*
    左闭又开区间
     */
    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }
}

