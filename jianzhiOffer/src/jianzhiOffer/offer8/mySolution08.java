package jianzhiOffer.offer8;

/**
 * 08.二叉树的下一个节点
 *
 */
/**
 * 二叉树节点
 * @Author rex
 * 2018/6/13
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    // 父节点
    TreeLinkNode parent = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
//如果一个节点的右子树不为空，那么该节点的下一个节点是右子树的最左节点
//如果一个节点的右子树为空，那么需要沿着父节点的指针一直向上遍历，知道找到一个是它父节点的左子节点的节点
public class mySolution08 {
    public TreeLinkNode getNext(TreeLinkNode pNode){
        if (pNode==null){
            return null;
        }
        if(pNode.right!=null){
            TreeLinkNode rNode=pNode.right;
            while(rNode.left!=null){   //一直向左找
                rNode=rNode.left;
            }
            return rNode;
        }else{
            while(pNode.parent!=null){
                TreeLinkNode parentNode = pNode.parent;
                if(parentNode.left==pNode){
                    return parentNode;
                }
                pNode=parentNode;
            }
        }
        return null;
    }
}
