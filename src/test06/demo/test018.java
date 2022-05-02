package test06.demo;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class test018 {
    public static void main(String[] args){
        Solution018 solution018 = new Solution018();
        solution018.preorderTraversal(new TreeNode(1,new TreeNode(2),new TreeNode(5)));
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){};
    TreeNode(int val){
        this.val=val;
    }
    TreeNode(int val,TreeNode left,TreeNode right){
        this.val=val;
        this.left=left;
        this.right=right;
    }
}

//递归 前序遍历
//class Solution018{
//    public List<Integer> preorderTraversal(TreeNode root){
//        List<Integer> result = new ArrayList<Integer>();
//        preorder(root,result);
//        return result;
//    }
//    public void preorder(TreeNode root,List<Integer> result){
//        if(root==null){
//            return;
//        }
//        result.add(root.val);
//        preorder(root.left,result);
//        preorder(root.right,result);
//    }
//}

// 迭代 前序遍历顺序：中-左-右，入栈顺序：中-右-左
class Solution018{
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<Integer>();
        if(root==null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();   //新建一个栈
        stack.push(root);             //将root全部放入堆栈中
        while(!stack.isEmpty()){         //非空
            TreeNode node = stack.pop();        //移出并赋给node
            result.add(node.val);             //中间结点放入result
            if(node.right!=null){
                stack.push(node.right);         //右节点放入堆栈中
            }
            if(node.left!=null){
                stack.push(node.left);         //左节点放入堆栈中
            }
        }
        return result;
    }
}

//递归 中序遍历
//class Solution0181{
//    public List<Integer> inorderTraversal(TreeNode root){
//        List<Integer> result = new ArrayList<Integer>();
//        inorder(root,result);
//        return result;
//    }
//    public void inorder(TreeNode root,List<Integer> result){
//        if(root==null){
//            return;
//        }
//        inorder(root.left,result);
//        result.add(root.val);
//        inorder(root.right,result);
//    }
//}

//迭代 中序遍历顺序: 左-中-右 入栈顺序： 左-右
class Solution0181{
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur =root;
        while(cur!=null||!stack.isEmpty()){
            if(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }else{
                cur=stack.pop();
                result.add(cur.val);
                cur=cur.right;
            }
        }
        return result;
    }
}

//递归 后序遍历
//class Solution0182{
//    public List<Integer>  postorderTraversal(TreeNode root){
//        List<Integer> result = new ArrayList<Integer>();
//        postorder(root,result);
//        return result;
//    }
//    public void  postorder(TreeNode root,List<Integer> result){
//        if(root==null){
//            return;
//        }
//        postorder(root.left,result);
//        postorder(root.right,result);
//        result.add(root.val);
//    }
//}

//迭代 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
class Solution0182{
    public List<Integer>  postorderTraversal(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null){
                stack.push(node.left);
            }
            if (node.right != null){
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}