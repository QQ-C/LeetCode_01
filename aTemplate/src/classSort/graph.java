package classSort;

import java.util.List;

/**
 * 图的表示，邻接表和邻接矩阵
 */
public class graph {
    public static void main(String[] args) {

    }
}
/**
 * 图的表示，邻接表和邻接矩阵
 */

/**
 * 图结点的逻辑结构
 */
class Graph{
    int id;
    Graph[] neighbors;
}
//邻接表：把每个节点x的邻居都存到一个列表里  还可以存储x到每个邻居的权重
//邻接矩阵：是一个二维布尔数组  也可以表示为权重系数
class graphDefine{
    //邻接表：graph[x]存储x的所有邻居节点
    List<Integer>[] graph;
    //邻接矩阵：记录x是否有一条指向y的通路
    boolean[][] matrix;

    //邻接表：graph[x]存储x的所有邻居节点 及权重
    List<int[]>[] graph_weight;
    //邻接矩阵：记录x指向y的通路权重  0表示不相邻
    int[][] matrix_weight;
}
class graphVisited{
    //纪录被遍历过的结点
    boolean[] visited;
    //记录从起点到当前结点的路径
    boolean[] onPath;
    /**
     * 图遍历框架
     */
    void traverse(Graph1 graph,int s){
        if(visited[s]){
            return;
        }
        //经过结点s 标记为已遍历
        visited[s]=true;
        //做选择标记结点s在路径上
        onPath[s]=true;
        for(int neighbor:graph.neighbors(s)){
            traverse(graph,neighbor);
        }
        //撤销选择 结点s离开路径
        onPath[s]=false;
    }

}
class Graph1{
    int id;
    Graph[] neighbors;

    public Graph1(int id) {
        this.id = id;
    }

    public int[] neighbors(int s) {
        return new int[0];
    }
}