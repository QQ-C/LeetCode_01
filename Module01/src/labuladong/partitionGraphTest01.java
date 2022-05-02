package labuladong;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二分图判定思路
 * 定二分图的算法很简单，就是用代码解决「双色问题」。
 *就是遍历一遍图，一边遍历一边染色，看看能不能用两种颜色给所有节点染色，且相邻节点的颜色都不相同。
 * DFS 算法和 BFS 皆可
 */
public class partitionGraphTest01 {
    public static void main(String[] args) {

    }
}

/**
 * 785. 判断二分图
 * 存在一个 无向图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。给你一个二维数组 graph ，其中 graph[u] 是一个节点数组，由节点 u 的邻接节点组成。形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。该无向图同时具有以下属性：
 * 不存在自环（graph[u] 不包含 u）。
 * 不存在平行边（graph[u] 不包含重复值）。
 * 如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图）
 * 这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
 * 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
 *
 * 如果图是二分图，返回 true ；否则，返回 false 。
 */
//额外使用一个 color 数组来记录每个节点的颜色
    //DFS
class Solution785{
    // 记录图是否符合二分图性质
    private boolean ok = true;
    // 记录图中节点的颜色，false 和 true 代表两种不同颜色
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;

    // 主函数，输入邻接表，判断是否是二分图
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color =  new boolean[n];
        visited =  new boolean[n];
        // 因为图不一定是联通的，可能存在多个子图
        // 所以要把每个节点都作为起点进行一次遍历
        // 如果发现任何一个子图不是二分图，整幅图都不算二分图
        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }
        return ok;
    }

    // DFS 遍历框架
    private void traverse(int[][] graph, int v) {
        // 如果已经确定不是二分图了，就不用浪费时间再递归遍历了
        if (!ok) return;

        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                // 相邻节点 w 没有被访问过
                // 那么应该给节点 w 涂上和节点 v 不同的颜色
                color[w] = !color[v];
                // 继续遍历 w
                traverse(graph, w);
            } else {
                // 相邻节点 w 已经被访问过
                // 根据 v 和 w 的颜色判断是否是二分图
                if (color[w] == color[v]) {
                    // 若相同，则此图不是二分图
                    ok = false;
                }
            }
        }
    }
}
//BFS
class Solution785_1{
    // 记录图是否符合二分图性质
    private boolean ok = true;
    // 记录图中节点的颜色，false 和 true 代表两种不同颜色
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color =  new boolean[n];
        visited =  new boolean[n];

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                // 改为使用 BFS 函数
                bfs(graph, v);
            }
        }

        return ok;
    }

    // 从 start 节点开始进行 BFS 遍历
    private void bfs(int[][] graph, int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);

        while (!q.isEmpty() && ok) {
            int v = q.poll();
            // 从节点 v 向所有相邻节点扩散
            for (int w : graph[v]) {
                if (!visited[w]) {
                    // 相邻节点 w 没有被访问过
                    // 那么应该给节点 w 涂上和节点 v 不同的颜色
                    color[w] = !color[v];
                    // 标记 w 节点，并放入队列
                    visited[w] = true;
                    q.offer(w);
                } else {
                    // 相邻节点 w 已经被访问过
                    // 根据 v 和 w 的颜色判断是否是二分图
                    if (color[w] == color[v]) {
                        // 若相同，则此图不是二分图
                        ok = false;
                    }
                }
            }
        }
    }
}
/**
 * 886. 可能的二分法
 * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 *
 * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 */
//把 dislikes 构造成一幅图，然后执行二分图的判定算法即可：
class Solution886 {
    private boolean ok = true;
    private boolean[] color;
    private boolean[] visited;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 图节点编号从 1 开始
        color = new boolean[n + 1];
        visited = new boolean[n + 1];
        // 转化成邻接表表示图结构
        List<Integer>[] graph = buildGraph(n, dislikes);

        for (int v = 1; v <= n; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }

        return ok;
    }

    // 建图函数
    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        // 图节点编号为 1...n
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : dislikes) {
            int v = edge[1];
            int w = edge[0];
            // 「无向图」相当于「双向图」
            // v -> w
            graph[v].add(w);
            // w -> v
            graph[w].add(v);
        }
        return graph;
    }

    // 和之前的 traverse 函数完全相同
    private void traverse(List<Integer>[] graph, int v) {
        if (!ok) return;
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse(graph, w);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                }
            }
        }
    }
}

