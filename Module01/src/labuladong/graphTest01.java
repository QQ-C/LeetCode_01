package labuladong;

import java.util.*;

public class graphTest01 {
    public static void main(String[] args) {
//        Solution797 solution797 = new Solution797();
//        int[][] graph = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
//        solution797.allPathsSourceTarget(graph);
        Solution207 solution = new Solution207();
        int[][] graph = {{0, 1}, {1, 2}, {1, 3}};
        solution.canFinish(4, graph);
    }
}
///************************图遍历

/**
 * 797. 所有可能的路径
 * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 * graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
 */
//思路：以0为起点遍历图，记录遍历过的路径，当遍历到终点将路径记录下来
//因为图是无环的，不需要使用visited数组辅助
class Solution797 {
    //记录所有路径
    List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //记录从起点到当前结点的路径   维护递归过程中经过的路径
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path); //从0开始   0到终点的路径
        return result;
    }

    /**
     * 图的遍历
     */
    void traverse(int[][] graph, int s, LinkedList<Integer> path) {
        //添加结点s的路径
        path.addLast(s);

        int n = graph.length;
        if (s == n - 1) {
            //到达终点
            result.add(new LinkedList<>(path));
            //可以在这里直接return ，但要removelast 正确维护path
            path.removeLast();
            return;
            //不return也可以，因为图中不包含环，不会无限递归
//            path.removeLast();
//            return;
        }
        //递归每个相邻的结点    递归
        for (int v : graph[s]) {    // v是 s 指向的 相邻结点  就是或许要添加到路径中的下一个点
            traverse(graph, v, path);
        }
        //从路径移出结点s
        path.removeLast();   //回溯
    }
}

//两个都是全局变量
class Solution797_1 {
    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int len = graph.length;
        traverse(graph, 0, len);
        return result;
    }

    void traverse(int[][] graph, int s, int n) {
        path.addLast(s);
        if (s == n - 1) {
            result.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }
        for (int v : graph[s]) {
            traverse(graph, v, n);
        }
        path.removeLast();
    }
}
//*******************************环检测

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */
//思路： 什么时候无法修完所有课程？当存在循环依赖的时候。
//看到依赖问题，首先想到的就是把问题转化成「有向图」这种数据结构，只要图中存在环，那就说明存在循环依赖。
//把题目的输入转化成一幅有向图，然后再判断图中是否存在环。     常见的存储方式是使用邻接表 graph[s]是一个列表，存储着结点s所指向的结点。

class Solution207 {

    //添加一个布尔数组 onPath 记录当前 traverse 经过的路径：  记录一次递归堆栈中的结点
    boolean[] onPath;
    //记录遍历过的结点，，防止重复遍历同一个结点
    boolean[] visited;
    //记录图中是否出现环
    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 图中并不是所有节点都相连，所以要用一个 for 循环将所有节点都作为起点调用一次 DFS 搜索算法。
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            //遍历图中所有结点
            traverse(graph, i);
        }
        // 只要没有循环依赖可以完成所有课程
        return !hasCycle;
    }

    /**
     * 1、构建图
     *
     * @param numCourses    结点数量
     * @param prerequisites 课程依赖关系
     * @return
     */
    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        //图中共有numCourses个结点    graph数组 每个位置下标 里面是list  //graph [0]  {,,,}  //graph [1]  {,,,,}
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {   //在二维数组中 去除每一个 一维数组
            int from = edge[1], to = edge[0];
            //添加一条从from到to的有向边
            //边的方向是被依赖关系，修完课程from才能修课程to
            graph[from].add(to);
        }
        return graph;
    }

    /**
     * 判断是否有环 以及递归遍历结果
     *
     * @param graph
     * @param s
     */
    void traverse(List<Integer>[] graph, int s) {
        // 从节点 s 开始 DFS 遍历，将遍历过的节点标记为 true
        if (onPath[s]) {
            //发现环
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            // 如果已经找到了环，也不用再遍历了
            return;
        }
        /* 前序遍历代码位置 */
        // 将当前节点标记为已遍历
        visited[s] = true;
        //开始遍历结点
        onPath[s] = true;

        for (int t : graph[s]) {
            traverse(graph, t);
        }
        /* 后序遍历代码位置 */
        //结点s遍历完成
        onPath[s] = false;
    }
}
//拓扑排序

/**
 * 210. 课程表 II
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * <p>
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 */
class Solution210 {
    List<Integer> postorder = new ArrayList<>();
    boolean hasCycle = false;
    boolean[] visited;
    boolean[] onPath;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        //遍历图
        for (int i = 0; i < numCourses; i++) {
            traverse(i, graph);
        }
        if (hasCycle) {
            return new int[]{};
        }
        Collections.reverse(postorder);   //反转
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }

    //2、遍历
    void traverse(int s, List<Integer>[] graph) {
        if (onPath[s]) {
            hasCycle = true;
            //有环
        }
        if (hasCycle || visited[s]) {
            return;
        }
        onPath[s] = true;
        visited[s] = true;
        for (int t : graph[s]) {
            traverse(t, graph);
        }
        postorder.add(s);
        onPath[s] = false;
    }

    //1、构建图 被依赖关系
    List<Integer>[] buildGraph(int num, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[num];
        for (int i = 0; i < num; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] neighbor : prerequisites) {
            graph[neighbor[1]].add(neighbor[0]);
        }
        return graph;
    }
}
//源代码
class Solution210_2{
    // 记录后序遍历结果
    List<Integer> postorder = new ArrayList<>();
    // 记录是否存在环
    boolean hasCycle = false;
    boolean[] visited, onPath;

    // 主函数
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        // 遍历图
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        // 有环图无法进行拓扑排序
        if (hasCycle) {
            return new int[]{};
        }
        // 逆后序遍历结果即为拓扑排序结果
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }

    // 图遍历函数
    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 发现环
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }
        // 前序遍历位置
        onPath[s] = true;
        visited[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        // 后序遍历位置
        postorder.add(s);
        onPath[s] = false;
    }

    // 建图函数
    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] neighbor : prerequisites) {
            graph[neighbor[1]].add(neighbor[0]);
        }
        return graph;
    }
}

//环检测算法（BFS 版本）
//  DFS 算法利用 onPath 数组判断是否存在环；用 DFS 算法利用逆后序遍历进行拓扑排序。
//其实 BFS 算法借助 indegree 数组记录每个节点的「入度」，也可以实现这两个算法。

/**
 * 总结这段 BFS 算法的思路：
 * <p>
 * 1、构建邻接表，和之前一样，边的方向表示「被依赖」关系。
 * <p>
 * 2、构建一个 indegree 数组记录每个节点的入度，即 indegree[i] 记录节点 i 的入度。
 * <p>
 * 3、对 BFS 队列进行初始化，将入度为 0 的节点首先装入队列。
 * <p>
 * 4、开始执行 BFS 循环，不断弹出队列中的节点，减少相邻节点的入度，并将入度变为 0 的节点加入队列。
 * <p>
 * 5、如果最终所有节点都被遍历过（count 等于节点数），则说明不存在环，反之则说明存在环。
 */
class Solution207_1 {
    // 主函数
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 建图，有向边代表「被依赖」关系
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 构建入度数组
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            //想学习0课程，必须先完成1   1-->0
            int from = edge[1], to = edge[0];
            // 节点 to 的入度加一
            indegree[to]++;
        }

        // 根据入度初始化队列中的节点
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                // 节点 i 没有入度，即没有依赖的节点
                // 可以作为拓扑排序的起点，加入队列
                q.offer(i);
            }
        }

        // 记录遍历的节点个数
        int count = 0;
        // 开始执行 BFS 循环
        while (!q.isEmpty()) {
            // 弹出节点 cur，并将它指向的节点的入度减一
            int cur = q.poll();
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    // 如果入度变为 0，说明 next 依赖的节点都已被遍历
                    q.offer(next);
                }
            }
        }

        // 如果所有节点都被遍历过，说明不成环
        return count == numCourses;
    }


    // 建图函数
    List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] neighbor : edges) {
            graph[neighbor[1]].add(neighbor[0]);
        }
        return graph;
    }
}

class Solution210_1 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses,prerequisites);
        int[] indegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            int form = edge[1], to = edge[0];
            indegree[to]++;
        }
        // 根据入度初始化队列中的节点，和环检测算法相同
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        // 记录拓扑排序结果
        int[] res = new int[numCourses];
        int count = 0;
        // 开始执行 BFS 算法
        while (!q.isEmpty()) {
            int cur = q.poll();
            res[count] = cur;
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        if (count != numCourses) {
            return new int[]{};
        }
        return res;
    }

    // 建图函数
    List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] neighbor : edges) {
            graph[neighbor[1]].add(neighbor[0]);
        }
        return graph;
    }
}