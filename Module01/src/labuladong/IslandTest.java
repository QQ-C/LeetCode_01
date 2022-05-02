package labuladong;

import java.util.HashSet;
import java.util.Spliterator;

/**
 * 岛屿系列题目的核心考点就是用 DFS/BFS 算法遍历二维数组。
 */
public class IslandTest {
}

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 */
class Solution200 {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    int[][] num = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        for (int[] d : num) {
            dfs(grid, i + d[0], j + d[1]);
        }
//        // 淹没上下左右的陆地
//        dfs(grid, i + 1, j);
//        dfs(grid, i, j + 1);
//        dfs(grid, i - 1, j);
//        dfs(grid, i, j - 1);
    }
}

/**
 * 1254. 统计封闭岛屿的数目
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。
 * 岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 * <p>
 * 请返回 封闭岛屿 的数目。
 */
//靠边的不算  所以先把靠边的去掉
class Solution1254 {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);  //左右
            dfs(grid, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);  //上下
            dfs(grid, m - 1, j);
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 1) {
            return;
        }
        grid[i][j] = 1;

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}

/**
 * 1020. 飞地的数量
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * <p>
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * <p>
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 */
//边界是陆地
class Solution1020 {
    public int numEnclaves(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        //把与边界相连的变为海洋
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);  //左右
            dfs(grid, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);  //上下
            dfs(grid, m - 1, j);
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}

/**
 * 695. 岛屿的最大面积
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * <p>
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * <p>
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 */
class Solution695 {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int sum = dfs(grid, i, j);
                    result = Math.max(result, sum);
                }
            }
        }
        return result;
    }

    int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        return 1 + dfs(grid, i + 1, j) +
                dfs(grid, i - 1, j) +
                dfs(grid, i, j + 1) +
                dfs(grid, i, j - 1);
    }
}

/**
 * 1905. 统计子岛屿
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
 * <p>
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 。
 * <p>
 * 请你返回 grid2 中 子岛屿 的 数目 。
 */
//思路：
//这道题的关键在于，如何快速判断子岛屿？
//
//什么情况下 grid2 中的一个岛屿 B 是 grid1 中的一个岛屿 A 的子岛？
//
//当岛屿 B 中所有陆地在岛屿 A 中也是陆地的时候，岛屿 B 是岛屿 A 的子岛。
//
//反过来说，如果岛屿 B 中存在一片陆地，在岛屿 A 的对应位置是海水，那么岛屿 B 就不是岛屿 A 的子岛。
//
//那么，我们只要遍历 grid2 中的所有岛屿，把那些不可能是子岛的岛屿排除掉，剩下的就是子岛。

class Solution1905 {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        if (grid1 == null || grid1.length == 0 || grid2 == null || grid2.length == 0) {
            return 0;
        }
        int m = grid1.length;
        int n = grid1[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    // 这个岛屿肯定不是子岛，淹掉
                    dfs(grid2, i, j);
                }
            }
        }
        // 现在 grid2 中剩下的岛屿都是子岛，计算岛屿数量
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid2[i][j]==1){
                    result++;
                    dfs(grid2,i,j);
                }
            }
        }
        return result;
    }

    void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}

/**
 力扣第 694 题「 不同的岛屿数量」，题目还是输入一个二维矩阵，0 表示海水，1 表示陆地，
 这次让你计算 不同的 (distinct) 岛屿数量
 */

/** 思路
 * 其中有四个岛屿，但是左下角和右上角的岛屿形状相同，所以不同的岛屿共有三个，算法返回 3。
 *
 * 很显然我们得想办法把二维矩阵中的「岛屿」进行转化，变成比如字符串这样的类型，
 * 然后利用 HashSet 这样的数据结构去重，最终得到不同的岛屿的个数。
 *
 * 如果想把岛屿转化成字符串，说白了就是序列化，序列化说白了就是遍历嘛，
 * 前文 二叉树的序列化和反序列化 讲了二叉树和字符串互转，这里也是类似的。
 *
 * 首先，对于形状相同的岛屿，如果从同一起点出发，dfs 函数遍历的顺序肯定是一样的。
 *
 * 因为遍历顺序是写死在你的递归函数里面的，不会动态改变：
 * 如果我用分别用 1, 2, 3, 4 代表上下左右，用 -1, -2, -3, -4 代表上下左右的撤销，
 * 那么可以这样表示它们的遍历顺序：
 * 只要每次使用 dfs 遍历岛屿的时候生成这串数字进行比较，就可以计算到底有多少个不同的岛屿了。
 *
 */
class Solution694{
    int numDistinctIslands(int[][] grid){
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        // 记录所有岛屿的序列化结果
        HashSet<String> islands=new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j]==1){
                    // 淹掉这个岛屿，同时存储岛屿的序列化结果
                    StringBuilder sb = new StringBuilder();
                    // 初始的方向可以随便写，不影响正确性
                    dfs(grid, i, j, sb, 666);
                    islands.add(sb.toString());
                }
            }
        }
        // 不相同的岛屿数量
        return islands.size();
    }
    //dir 记录方向，dfs 函数递归结束后，sb 记录着整个遍历顺序，
    // 其实这就是前文 回溯算法核心套路 说到的回溯算法框架，你看到头来这些算法都是相通的。
    void dfs(int[][] grid, int i, int j,StringBuilder sb,int dir) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j]=0;
        sb.append(dir).append(',');

        // 递归顺序：
        dfs(grid, i - 1, j,sb,1); // 上
        dfs(grid, i + 1, j,sb,2); // 下
        dfs(grid, i, j - 1,sb,3); // 左
        dfs(grid, i, j + 1,sb,4); // 右

        sb.append(-dir).append(',');
    }
}