package labuladong;

/**
 * 并查集  动态连通图
 * 1、用 parent 数组记录每个节点的父节点，相当于指向父节点的指针，所以 parent 数组内实际存储着一个森林（若干棵多叉树）。
 * <p>
 * 2、用 size 数组记录着每棵树的重量，目的是让 union 后树依然拥有平衡性，保证各个 API 时间复杂度为 O(logN)，而不会退化成链表影响操作效率。
 * <p>
 * 3、在 find 函数中进行路径压缩，保证任意树的高度保持在常数，使得各个 API 时间复杂度为 O(1)。使用了路径压缩之后，可以不使用 size 数组的平衡优化。
 */
public class UF_Test {
    public static void main(String[] args) {
        Solution323 solution = new Solution323();
        int[][] edgs = {{1, 2}, {2, 3}, {1, 4}};
        solution.countComponents(5, edgs);
    }
}

class UF {
    // 连通分量个数
    private int count;
    // 存储每个节点的父节点  // 节点 x 的父节点是 parent[x]
    private int[] parent;

    //构造函数  n 为图中节点的个数
    public UF(int n) {
        //一开始互不连通
        this.count = n;
        //父节点的指针初始指向自己
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // 将节点 p 和节点 q 连通
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ)
            return;
        // 将两棵树合并为一棵
        parent[rootQ] = rootP;
        // parent[rootP] = rootQ 也一样
        // 两个连通分量合并成一个连通分量
        count--;
    }

    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    /* 返回某个节点 x 的根节点 */
    public int find(int x) {
        // 根节点的 parent[x] == x
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 这段迭代代码方便你理解递归代码所做的事情
    public int find_1(int x) {
        // 先找到根节点
        int root = x;
        while (parent[root] != root) {
            root = parent[root];
        }
        // 然后把 x 到根节点之间的所有节点直接接到根节点下面
        int old_parent = parent[x];
        while (x != root) {
            parent[x] = root;
            x = old_parent;
            old_parent = parent[old_parent];
        }
        return root;
    }

    // 返回图中的连通分量个数
    public int count() {
        return count;
    }
}

/**
 * 力扣第 323 题「 无向图中连通分量的数目」就是最基本的连通分量题目：
 * <p>
 * 给你输入一个包含 n 个节点的图，用一个整数 n 和一个数组 edges 表示，其中 edges[i] = [ai, bi] 表示图中节点 ai 和 bi 之间有一条边。请你计算这幅图的连通分量个数。
 */
//会员题。。。。。。。。。 -_-
class Solution323 {
    int countComponents(int n, int[][] edges) {
        UF_1(n);
        // 将每个节点进行连通
        for (int[] e : edges) {
            union(e[0], e[1]);
        }
        return count;

//        UF uf = new UF(n);
//        // 将每个节点进行连通
//        for (int[] e : edges) {
//            uf.union(e[0], e[1]);
//        }
//        // 返回连通分量的个数
//        return uf.count();
    }

    private int count;
    private int[] parent;

    public void UF_1(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootQ == rootP) {
            return;
        }
        parent[rootQ] = rootP;
        count--;
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    public int count() {
        return this.count;
    }

}

//无注释版
class UF_1 {
    private int count;
    private int[] parent;

    public UF_1(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootQ == rootP) {
            return;
        }
        parent[rootQ] = rootP;
        count--;
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    public int count() {
        return this.count;
    }
}

/**
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 */
class Solution130 {
    void solve(char[][] board) {
        if (board.length == 0) return;

        int m = board.length;
        int n = board[0].length;
        // 给 dummy 留一个额外位置
        UF uf = new UF(m * n + 1);
        int dummy = m * n;
        // 将首列和末列的 O 与 dummy 连通
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                uf.union(i * n, dummy);
            if (board[i][n - 1] == 'O')
                uf.union(i * n + n - 1, dummy);
        }
        // 将首行和末行的 O 与 dummy 连通
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                uf.union(j, dummy);
            if (board[m - 1][j] == 'O')
                uf.union(n * (m - 1) + j, dummy);
        }
        // 方向数组 d 是上下左右搜索的常用手法
        int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        for (int i = 1; i < m - 1; i++)
            for (int j = 1; j < n - 1; j++)
                if (board[i][j] == 'O')
                    // 将此 O 与上下左右的 O 连通
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if (board[x][y] == 'O')
                            uf.union(x * n + y, i * n + j);
                    }
        // 所有不和 dummy 连通的 O，都要被替换
        for (int i = 1; i < m - 1; i++)
            for (int j = 1; j < n - 1; j++)
                if (!uf.connected(dummy, i * n + j))
                    board[i][j] = 'X';
    }
}

/**
 * 990. 等式方程的可满足性
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。
 * 在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * <p>
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
 */
//核心思想是，将 equations 中的算式根据 == 和 != 分成两部分，先处理 == 算式，使得他们通过相等关系各自勾结成门派（连通分量）；
// 然后处理 != 算式，检查不等关系是否破坏了相等关系的连通性。
class Solution990 {
    public boolean equationsPossible(String[] equations) {
        // 26 个英文字母
        UF uf = new UF(26);
        // 先让相等的字母形成连通分量
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                uf.union(x - 'a', y - 'a');
            }
        }
        // 检查不等关系是否打破相等关系的连通性
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                // 如果相等关系成立，就是逻辑冲突
                if (uf.connected(x - 'a', y - 'a'))
                    return false;
            }
        }
        return true;
    }
}

//官方解答：
class Solution990_1 {
    public boolean equationsPossible(String[] equations) {
        // 26 个英文字母
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
}