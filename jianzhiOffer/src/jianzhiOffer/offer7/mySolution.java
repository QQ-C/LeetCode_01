package jianzhiOffer.offer7;

/**
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * <p>
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */

import com.sun.source.tree.Tree;

import java.util.HashMap;

/**
 * Definition for a binary tree node.
 * */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        this.val = x;
    }
}

/**
 * 前序遍历确定根节点
 * 中序遍历根据根节点的位置确定左右子树
 * 然后前序遍历再确定根节点
 */
class mySolution {
    HashMap<Integer,Integer> map=new HashMap<>();
    int[] preorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder=preorder;
        //将中序遍历的值及索引放在map中，方便递归时获取左右子树的数量及其根的索引  左--中--右
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        //三个索引分别是
        //当前根的索引。。。递归树的左边界、、、递归树的右边界
        return recur(0,0,inorder.length-1);
    }
    TreeNode recur(int pre_root,int in_left,int in_right){
        if(in_left>in_right){
            return null;
        }
        TreeNode root=new TreeNode(preorder[pre_root]); //获取root结点
        //获取在中序遍历中根节点所在的索引，以方便获得左子树的数量
        int idx=map.get(preorder[pre_root]);
        //左子树根的索引为 先序遍历中根节点+1
        //递归左子树的左边界为原来的中序in_left
        //递归左子树的右边界为中序根节点-1
        root.left=recur(pre_root+1,in_left,idx-1);
        //右子树根的索引为 先序遍历中 当前根节点位置+左子树长度+1
        //递归右子树的左边界为 中序当前根节点+1 i+1
        //递归右子树的右边界为中序 右子树的边界
        root.right=recur(pre_root+(idx-in_left)+1,idx+1,in_right);
        return root;
    }
}
