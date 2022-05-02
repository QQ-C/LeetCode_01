package labuladong;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeTest {
}

/**
 * Definition for a binary tree node.
 */
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

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 */
//思路：
//解决这题的关键在于，每一条二叉树的「直径」长度，就是一个节点的左右子树的最大深度之和。
//直截了当的思路就是遍历整棵树中的每个节点，然后通过每个节点的左右子树的最大深度算出每个节点的「直径」，最后把所有「直径」求个最大值即可。
class Solution543 {
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return maxDiameter;
    }

    int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        // 后序位置，顺便计算最大直径
        int myDiameter = leftMax + rightMax;
        maxDiameter = Math.max(myDiameter, maxDiameter);
        return 1 + Math.max(leftMax, rightMax);
    }
}

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
//后序遍历的时候顺便计算题目要求的最大路径和。
class Solution124 {
    int res=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root==null){
            return 0;
        }
        // 计算单边路径和时顺便计算最大路径和
        oneSideMax(root);
        return res;
    }
    int oneSideMax(TreeNode root){
        // 定义：计算从根节点 root 为起点的最大单边路径和
        if(root==null){
            return 0;
        }
        int leftMaxSum=Math.max(0,oneSideMax(root.left));
        int rightMaxSum= Math.max(0,oneSideMax(root.right));

        int pathMaxSum=root.val+leftMaxSum+rightMaxSum;
        res=Math.max(res,pathMaxSum);
        // 实现函数定义，左右子树的最大单边路径和加上根节点的值
        // 就是从根节点 root 为起点的最大单边路径和
        return Math.max(leftMaxSum,rightMaxSum)+root.val;
    }
}

/**
 * 366. 寻找二叉树的叶子节点
 * 给你一棵二叉树，请按以下要求的顺序收集它的全部节点：
 *
 * 依次从左到右，每次收集并删除所有的叶子节点
 * 重复如上过程直到整棵树为空
 */
//后序遍历
class Solution366 {
    List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        maxDepth(root);
        return result;
    }
    // 定义：输入节点 root，返回以 root 为根的树的最大深度
    int maxDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        // 当前节点距离叶子节点的高度（最大深度）
        int h=Math.max(maxDepth(root.left),maxDepth(root.right))+1;
        //后序遍历
        if(result.size()<h){
            result.add(new ArrayList<>());
        }
        // 把所有相同高度的叶子节点放在一起
        result.get(h-1).add(root.val);
        return h;
    }
}

/**
 * 669. 修剪二叉搜索树
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
 * 修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
 *
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 */

/**
 * 思路：
 * 1、**root.val < lo，这种情况下 root 节点本身和 root 的左子树全都是小于 lo 的，都需要被剪掉**。
 *
 * 2、**root.val > hi，这种情况下 root 节点本身和 root 的右子树全都是大于 hi 的，都需要被剪掉**。
 */
class Solution669 {
    // 定义：删除 BST 中小于 low 和大于 high 的所有节点，返回结果 BST
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root==null){
            return null;
        }
        if(root.val<low){
            // 直接返回 root.right
            // 等于删除 root 以及 root 的左子树
            return trimBST(root.right,low,high);
        }
        if (root.val > high) {
            // 直接返回 root.left
            // 等于删除 root 以及 root 的右子树
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}