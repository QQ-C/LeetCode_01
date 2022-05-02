package test06.demo;


import com.sun.source.tree.Tree;

import java.util.*;

public class test020 {
    public static void main(String[] args){
        Solution0207 solution0205 = new Solution0207();
        solution0205.isBalanced(new TreeNode(1,new TreeNode(2,new TreeNode(3),new TreeNode(4)),new TreeNode(5)));
    }
}
//递归法
class Solution020{
    public boolean isSymmetric1(TreeNode root){
        return compare(root.left,root.right);
    }
    private boolean compare(TreeNode left, TreeNode right){
        if(left==null&&right!=null){
            return false;
        }
        if(left!=null&&right==null){
            return false;
        }
        if(left==null&&right==null){
            return true;
        }
        if(left.val!=right.val){
            return false;
        }
        //比较外侧
        boolean compareOutside = compare(left.left,right.right);
        //比较内侧
        boolean compareInside = compare(left.right,right.left);
        return compareInside&&compareOutside;
    }
}
//迭代法
//使用双端队列，相当于两个栈
class Solution0201{
    public boolean isSymmetric2(TreeNode root){
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerFirst(root.left);
        deque.offerLast(root.right);
        while(!deque.isEmpty()){
            TreeNode leftNode =deque.pollFirst();
            TreeNode rightNode = deque.pollLast();
            if(leftNode==null&&rightNode==null){
                continue;
            }
//            if (leftNode == null && rightNode != null) {
//                return false;
//            }
//            if (leftNode != null && rightNode == null) {
//                return false;
//            }
//            if (leftNode.val != rightNode.val) {
//                return false;
//            }
            // 以上三个判断条件合并
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }
            deque.offerFirst(leftNode.left);
            deque.offerFirst(leftNode.right);
            deque.offerLast(rightNode.right);
            deque.offerLast(rightNode.left);
        }
        return true;
    }
}
/**
 * 迭代法
 * 使用普通队列
 */
class Solution0202{
    public boolean isSymmetric3(TreeNode root) {
        Queue<TreeNode> deque = new LinkedList<>();
        deque.offer(root.left);
        deque.offer(root.right);
        while (!deque.isEmpty()) {
            TreeNode leftNode = deque.poll();
            TreeNode rightNode = deque.poll();
            if (leftNode == null && rightNode == null) {
                continue;
            }
//            if (leftNode == null && rightNode != null) {
//                return false;
//            }
//            if (leftNode != null && rightNode == null) {
//                return false;
//            }
//            if (leftNode.val != rightNode.val) {
//                return false;
//            }
            // 以上三个判断条件合并
            if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
                return false;
            }
            // 这里顺序与使用Deque不同
            deque.offer(leftNode.left);
            deque.offer(rightNode.right);
            deque.offer(leftNode.right);
            deque.offer(rightNode.left);
        }
        return true;
    }

}

//判断是不是另一棵树的子树
class Solution0203 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return dfs(s, t);
    }

    public boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    public boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null || s.val != t.val) {
            return false;
        }
        return check(s.left, t.left) && check(s.right, t.right);
    }
}

//二叉树的最大深度
class Solution0204{
    //递归
    public int maxDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth,rightDepth)+1;
    }
    //迭代法
    public int maxDepth1(TreeNode root){
        if(root==null){
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth=0;
        while(!deque.isEmpty()){
            int size=deque.size();
            depth++;
            for(int i=0;i<size;i++){
                TreeNode poll= deque.poll();
                if(poll.left!=null){
                    deque.offer(poll.left);
                }
                if(poll.right!=null){
                    deque.offer(poll.right);
                }
            }
        }
        return depth;
    }
}

//n叉树的最大深度
class Solution0205{
    //递归 后序遍历
    public int maxDepth(Node root) {
        if(root==null){
            return 0;
        }
        int depth=0;
        if(root.children!=null){
            for(Node child:root.children){
                depth = Math.max(depth,maxDepth(child));
            }
        }
        return depth+1;  //中结点
    }
    //迭代 层次遍历
}

class Node {
    public int val;
    public List<Node> children;
    public Node(){};
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

class solution02051 {
    /**
     * 迭代法，使用层序遍历
     */
    public int maxDepth(Node root) {
        if (root == null)   return 0;
        int depth = 0;
        Queue<Node> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty())
        {
            depth ++;
            int len = que.size();
            while (len > 0)
            {
                Node node = que.poll();
                for (int i = 0; i < node.children.size(); i++)
                    if (node.children.get(i) != null)
                        que.offer(node.children.get(i));
                len--;
            }
        }
        return depth;
    }
}

class Solution02052 {
    /**
     * 递归法，相比求MaxDepth要复杂点
     * 因为最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (root.left == null) {
            return rightDepth + 1;
        }
        if (root.right == null) {
            return leftDepth + 1;
        }
        // 左右结点都不为null
        return Math.min(leftDepth, rightDepth) + 1;
    }
}

/**
 * 完全二叉树的叶子结点的三种解法
 */
class Solution02061 {
    // 通用递归解法
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}

class Solution02062 {
    // 迭代法
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                TreeNode cur = queue.poll();
                result++;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return result;
    }
}
class Solution02063 {
    /**
     * 针对完全二叉树的解法
     *
     * 满二叉树的结点数为：2^depth - 1
     */
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {// 左子树是满二叉树
            // 2^leftDepth其实是 （2^leftDepth - 1） + 1 ，左子树 + 根结点      左移一位都相当于乘以2的1次方   右移一位相当于除2
            return (1 << leftDepth) + countNodes(root.right);
        } else {// 右子树是满二叉树
            return (1 << rightDepth) + countNodes(root.left);
        }
    }

    private int getDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }
}


//是否是高度平衡的二叉树
//递归法
class Solution0207{
    public boolean isBalanced(TreeNode root){
        return getHeight(root)!=-1;
    }
    private int getHeight(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftHeight = getHeight(root.left);
        if(leftHeight==-1){
            return -1;
        }
        int rightHeight=getHeight(root.right);
        if(rightHeight==-1){
            return -1;
        }
        // 左右子树高度差大于1，return -1表示已经不是平衡树了
        if(Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }
        return Math.max(leftHeight,rightHeight)+1;
    }
}

//二叉树的所有路径
//递归法
//class Solution0208{
//    public List<String> binaryTreePaths(TreeNode root){
//        List<String> res = new ArrayList<>();
//
//    }
//}