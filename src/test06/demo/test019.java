package test06.demo;

import com.sun.source.tree.Tree;

import java.awt.event.TextEvent;
import java.util.*;

public class test019 {
    public static void main(String[] args) {
        Solution0197 solution0197 = new Solution0197();
        solution0197.invertTree(new TreeNode(1,new TreeNode(2),new TreeNode(5)));
    }

}
//二叉树的层次遍历
class Solution019 {
    public List<List<Integer>> resList = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        //checkFun01(root,0);
        checkFun02(root);

        return resList;
    }

    //DFS--递归方式
//    public void checkFun01(TreeNode node, Integer deep) {
//        if (node == null) return;
//        deep++;
//
//        if (resList.size() < deep) {
//            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
//            List<Integer> item = new ArrayList<Integer>();
//            resList.add(item);
//        }
//        resList.get(deep - 1).add(node.val);
//
//        checkFun01(node.left, deep);
//        checkFun01(node.right, deep);
//    }

    //BFS--迭代方式--借助队列
    public void checkFun02(TreeNode node) {
        if (node == null) return;
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(node);

        while (!que.isEmpty()) {
            List<Integer> itemList = new ArrayList<Integer>();
            int len = que.size();

            while (len > 0) {
                TreeNode tmpNode = que.poll();
                itemList.add(tmpNode.val);

                if (tmpNode.left != null) que.offer(tmpNode.left);
                if (tmpNode.right != null) que.offer(tmpNode.right);
                len--;
            }

            resList.add(itemList);
        }
    }
}

class Solution0191{
    public List<List<Integer>> solution1(TreeNode root){
        List<List<Integer>> list = new ArrayList<>();
        Deque<TreeNode> que = new LinkedList<>();
        if(root==null){
            return list;
        }
        que.offerLast(root);
        while(!que.isEmpty()){
            List<Integer> levelList= new ArrayList<>();
            int levelSize = que.size();
            for (int i = 0; i < levelSize; i++) {
//                TreeNode peek=que.peekFirst();
//                levelList.add(que.pollFirst().val);
                TreeNode peek = que.poll();
                levelList.add(peek.val);
                if (peek.left!=null){
                    que.offerLast(peek.left);
                }
                if(peek.right!=null){
                    que.offerLast(peek.right);
                }
            }
            list.add(levelList);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i-- ) {
            result.add(list.get(i));
        }

        return result;

    }
}


// 199.二叉树的右视图
class N0199 {
    /**
     * 解法：队列，迭代。
     * 每次返回每层的最后一个字段即可。
     *
     * 小优化：每层右孩子先入队。代码略。
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> que = new LinkedList<>();

        if (root == null) {
            return list;
        }

        que.offerLast(root);     //返回false或者null
        while (!que.isEmpty()) {
            int levelSize = que.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode poll = que.pollFirst();    //返回false或者null

                if (poll.left != null) {
                    que.addLast(poll.left);   //抛出异常
                }
                if (poll.right != null) {
                    que.addLast(poll.right);
                }

                if (i == levelSize - 1) {
                    list.add(poll.val);
                }
            }
        }

        return list;
    }
}


// 637. 二叉树的层平均值
class N0637 {

    /**
     * 解法：队列，迭代。
     * 每次返回每层的最后一个字段即可。
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        Deque<TreeNode> que = new LinkedList<>();

        if (root == null) {
            return list;
        }

        que.offerLast(root);
        while (!que.isEmpty()) {

            int levelSize = que.size();
            double levelSum = 0.0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode poll = que.pollFirst();

                levelSum += poll.val;

                if (poll.left != null) {
                    que.addLast(poll.left);
                }
                if (poll.right != null) {
                    que.addLast(poll.right);
                }
            }
            list.add(levelSum / levelSize);
        }
        return list;
    }
}


// 429. N 叉树的层序遍历
class N0429 {
    /**
     * 解法1：队列，迭代。
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        Deque<Node> que = new LinkedList<>();

        if (root == null) {
            return list;
        }

        que.offerLast(root);
        while (!que.isEmpty()) {
            int levelSize = que.size();
            List<Integer> levelList = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node poll = que.pollFirst();

                levelList.add(poll.val);

                List<Node> children = poll.children;
                if (children == null || children.size() == 0) {
                    continue;
                }
                for (Node child : children) {
                    if (child != null) {
                        que.offerLast(child);
                    }
                }
            }
            list.add(levelList);
        }

        return list;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}



class Solution0192 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> retVal = new ArrayList<Integer>();
        Queue<TreeNode> tmpQueue = new LinkedList<TreeNode>();
        if (root != null) tmpQueue.add(root);

        while (tmpQueue.size() != 0){
            int size = tmpQueue.size();
            List<Integer> lvlVals = new ArrayList<Integer>();
            for (int index = 0; index < size; index++){
                TreeNode node = tmpQueue.poll();
                lvlVals.add(node.val);
                if (node.left != null) tmpQueue.add(node.left);
                if (node.right != null) tmpQueue.add(node.right);
            }
            retVal.add(Collections.max(lvlVals));
        }

        return retVal;
    }
}

//
//class Solution0193 {
//    public Node connect(Node root) {
//        Queue<Node> tmpQueue = new LinkedList<Node>();
//        if (root != null) tmpQueue.add(root);
//
//        while (tmpQueue.size() != 0){
//            int size = tmpQueue.size();
//
//            Node cur = tmpQueue.poll();
//            if (cur.left != null) tmpQueue.add(cur.left);
//            if (cur.right != null) tmpQueue.add(cur.right);
//
//            for (int index = 1; index < size; index++){
//                Node next = tmpQueue.poll();
//                if (next.left != null) tmpQueue.add(next.left);
//                if (next.right != null) tmpQueue.add(next.right);
//
//                cur.next = next;
//                cur = next;
//            }
//        }
//
//        return root;
//    }
//}

//// 二叉树之层次遍历
//class Solution0194 {
//    public Node connect(Node root) {
//        Queue<Node> queue = new LinkedList<>();
//        if (root != null) {
//            queue.add(root);
//        }
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            Node node = null;
//            Node nodePre = null;
//
//            for (int i = 0; i < size; i++) {
//                if (i == 0) {
//                    nodePre = queue.poll(); // 取出本层头一个节点
//                    node = nodePre;
//                } else {
//                    node = queue.poll();
//                    nodePre.next = node; // 本层前一个节点 next 指向当前节点
//                    nodePre = nodePre.next;
//                }
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//            }
//            nodePre.next = null; // 本层最后一个节点 next 指向 null
//        }
//        return root;
//    }
//}


class Solution0195 {
    public int maxDepth(TreeNode root) {
        if (root == null)   return 0;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int depth = 0;
        while (!que.isEmpty())
        {
            int len = que.size();
            while (len > 0)
            {
                TreeNode node = que.poll();
                if (node.left != null)  que.offer(node.left);
                if (node.right != null) que.offer(node.right);
                len--;
            }
            depth++;
        }
        return depth;
    }
}

class Solution0196 {
    public int minDepth(TreeNode root){
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            depth++;
            TreeNode cur = null;
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                //如果当前节点的左右孩子都为空，直接返回最小深度
                if (cur.left == null && cur.right == null){
                    return depth;
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return depth;
    }
}

class Solution0197{
    /**
     * 前后序遍历都可以
     * 中序不行，因为先左孩子交换孩子，再根交换孩子（做完后，右孩子已经变成了原来的左孩子），再右孩子交换孩子（此时其实是对原来的左孩子做交换）
     */
    public TreeNode invertTree(TreeNode root){
        if(root==null){
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        swapChildren(root);
        return root;
    }
    private void swapChildren(TreeNode root){
        TreeNode tmp = root.left;
        root.left=root.right;
        root.right=tmp;
    }
}


class Solution0198{
    /**
     * 前后序遍历都可以
     * 中序不行，因为先左孩子交换孩子，再根交换孩子（做完后，右孩子已经变成了原来的左孩子），再右孩子交换孩子（此时其实是对原来的左孩子做交换）
     */
    public TreeNode invertTree(TreeNode root){
        if(root==null){
            return null;
        }
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while(!deque.isEmpty()){
            int size = deque.size();
            while(size-->0){
                TreeNode node= deque.poll();
                swapChildren(node);
                if(node.left!=null){deque.offer(node.left);}
                if(node.right!=null){deque.offer(node.right);}
            }
        }
        return root;
    }
    private void swapChildren(TreeNode root){
        TreeNode tmp = root.left;
        root.left=root.right;
        root.right=tmp;
    }
}