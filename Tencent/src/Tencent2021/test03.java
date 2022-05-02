package Tencent2021;

/**
 * 我们定义一个有效序列为：该序列两端的数一个为最小值，另一个为次小值。（即序列两端以外的数一定大于等于最左边的数且大于等于最右边的数）
 * <p>
 * 现在给你一个序列 a ，想让你找到它的连续子序列中有多少个有效序列(比如 ，1 2 ，2 3，1 2 3 是序列 1 2 3 的连续子序列，但是 1 3 不是)
 * <p>
 * 注：长度为 2 的子序列，一定为有效序列，长度为 1 的子序列，一定不是有效序列
 * <p>
 * 单调栈
 * 很不好想，需要根据业务来构建单调栈的单调逻辑。构建一个大压小的栈，保持栈底到栈顶是单调递增的，用一个例子来说明一下算法流程：
 * 正序遍历
 * arr：[1,1,2,1]
 * <p>
 * stack：[]
 * <p>
 * i=0，考察以arr[0]结尾，且以arr[0]为最小值的有效序列数，由于arr[0]的左边没有元素，所以直接入栈（为了考虑重复数字，需要记录某个数字的重复次数）
 * <p>
 * stack：[node(val=1,times=1)]
 * <p>
 * i=1，考察以arr[1]结尾，且以arr[1]为最小值的有效序列数，由于arr[0]=stack.top.val，直接压栈（与栈顶元素相同，增加栈顶元素的出现次数）
 * <p>
 * stack：[node(val=1,times=2)]
 * <p>
 * i=2，考察以arr[2]结尾，且以arr[2]为最小值的有效序列数，由于arr[2]>stack.top.val，直接压栈
 * <p>
 * stack：[node(val=1,times=2),node(val=2,times=1)]
 * <p>
 * i=3，考察以arr[3]结尾，且以arr[3]为最小值的有效序列数，而此时arr[3]<stack.top.val，破坏单调性，开始弹栈，栈顶元素为2，且只有一个2，说明可以与arr[3]构成一个有效的序列[2,1]（刚好是以arr[3]结尾，且arr[3]为最小值，2为次小值）。
 * <p>
 * 弹栈之后发现栈顶为1，且1出现了两次，可以与arr[3]构成两个有效序列，一个是①：[1,2,1]，另一个是②：[1,1,2,1]。①中左边的1是把栈顶的2弹出后的次大值（由于栈是大压小的单调性，所以弹出2之后，如果栈顶元素仍然不小于arr[3]，则栈顶元素一定可以作为次小值）；同理②弹出的1也可以作为次小值。而两个1是同时弹出的，所以直接累加上2就可以了，此时已经有了[2,1]，[1,2,1]，[1,1,2,1]三个有效序列。
 * <p>
 * 这时候注意到弹出的node(val=1,times=2)，其中的两个1也可以构成一个有效序列[1,1]。如果弹出的某个栈顶元素出现了n次，除了n个有效序列贡献给当前破坏单调性的那个元素，它本身也可以构成个有效序列，组合数表示选择两个数作为序列的左右端点。
 * 倒序遍历
 * 正序遍历完成后，还需要倒序遍历一遍，对于某个元素arr[i]往右边找次小值，同样的流程可以再抓出序列[1,2]。但在倒序遍历的过程中需要注意，相同元素的有效序列不需要再计算了，因为正序遍历时已经算过了。
 */
//public class test03 {
//    public static void main(String[] args) {
//
//    }
//}
/**
 单调栈
 很不好想，需要根据业务来构建单调栈的单调逻辑。构建一个大压小的栈，保持栈底到栈顶是单调递增的，用一个例子来说明一下算法流程：
 正序遍历
 arr：[1,1,2,1]

 stack：[]

 i=0，考察以arr[0]结尾，且以arr[0]为最小值的有效序列数，由于arr[0]的左边没有元素，所以直接入栈（为了考虑重复数字，需要记录某个数字的重复次数）

 stack：[node(val=1,times=1)]

 i=1，考察以arr[1]结尾，且以arr[1]为最小值的有效序列数，由于arr[0]=stack.top.val，直接压栈（与栈顶元素相同，增加栈顶元素的出现次数）

 stack：[node(val=1,times=2)]

 i=2，考察以arr[2]结尾，且以arr[2]为最小值的有效序列数，由于arr[2]>stack.top.val，直接压栈

 stack：[node(val=1,times=2),node(val=2,times=1)]

 i=3，考察以arr[3]结尾，且以arr[3]为最小值的有效序列数，而此时arr[3]<stack.top.val，破坏单调性，开始弹栈，栈顶元素为2，且只有一个2，说明可以与arr[3]构成一个有效的序列[2,1]（刚好是以arr[3]结尾，且arr[3]为最小值，2为次小值）。

 弹栈之后发现栈顶为1，且1出现了两次，可以与arr[3]构成两个有效序列，一个是①：[1,2,1]，另一个是②：[1,1,2,1]。①中左边的1是把栈顶的2弹出后的次大值（由于栈是大压小的单调性，所以弹出2之后，如果栈顶元素仍然不小于arr[3]，则栈顶元素一定可以作为次小值）；同理②弹出的1也可以作为次小值。而两个1是同时弹出的，所以直接累加上2就可以了，此时已经有了[2,1]，[1,2,1]，[1,1,2,1]三个有效序列。

 这时候注意到弹出的node(val=1,times=2)，其中的两个1也可以构成一个有效序列[1,1]。如果弹出的某个栈顶元素出现了n次，除了n个有效序列贡献给当前破坏单调性的那个元素，它本身也可以构成个有效序列，组合数表示选择两个数作为序列的左右端点。
 倒序遍历
 正序遍历完成后，还需要倒序遍历一遍，对于某个元素arr[i]往右边找次小值，同样的流程可以再抓出序列[1,2]。但在倒序遍历的过程中需要注意，相同元素的有效序列不需要再计算了，因为正序遍历时已经算过了。
 */

import java.io.*;
import java.util.*;

class Node {
    public int value;
    public int times;

    public Node(int v, int t) {
        value = v;
        times = t;
    }
}

public class test03 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n < 2) {
            System.out.println(0);
            return;
        }
        String[] strs = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }
        long ans = 0;
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek().value > arr[i]) {
                Node topNode = stack.pop();
                ans += topNode.times + cn2(topNode.times);
            }
            if (!stack.isEmpty() && arr[i] == stack.peek().value) {
                stack.peek().times++;
            } else {
                stack.push(new Node(arr[i], 1));
            }
        }
        while (!stack.isEmpty()) {
            ans += cn2(stack.pop().times);
        }
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().value > arr[i]) {
                Node topNode = stack.pop();
                ans += topNode.times;
            }
            if (!stack.isEmpty() && arr[i] == stack.peek().value) {
                stack.peek().times++;
            } else {
                stack.push(new Node(arr[i], 1));
            }
        }
        System.out.println(ans);
    }

    private static long cn2(int n) {
        return (long) n * (n - 1) >> 1;
    }
}