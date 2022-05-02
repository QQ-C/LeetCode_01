package classSort;



/**
 * 二叉树的深度优先遍历
 */

/**
 * 二叉树的构造
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode() {
    }
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * 二叉树的深度优先遍历
 * 二叉树的 DFS 有两个要素：「访问相邻结点」和「判断 base case」。
 *
 * 第一个要素是访问相邻结点。二叉树的相邻结点非常简单，只有左子结点和右子结点两个。
 * 二叉树本身就是一个递归定义的结构：一棵二叉树，它的左子树和右子树也是一棵二叉树。
 * 那么我们的 DFS 遍历只需要递归调用左子树和右子树即可。
 *
 * 第二个要素是 判断 base case。一般来说，二叉树遍历的 base case 是 root == null。
 * 这样一个条件判断其实有两个含义：一方面，这表示 root 指向的子树为空，不需要再往下遍历了。
 * 另一方面，在 root == null 的时候及时返回，可以让后面的 root.left 和 root.right 操作不会出现空指针异常。
 *
 */
public class DFS_Tree {
    public static void main(String[] args) {

    }
}

class templete_Tree_DFS{
    /**
     * 二叉树的深度优先遍历
     * @param root
     */
    void traverse(TreeNode root){
        //判断 base case
        if(root==null){
            return;
        }
        // 访问两个相邻结点：左子结点、右子结点
        traverse(root.left);
        traverse(root.right);
    }
}
