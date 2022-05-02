package HuiSu;

import java.util.*;

public class test02 {
    public static void main(String[] args) {

    }
}

/**
 * 51. N 皇后
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * <p>
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */
class solution51 {
    List<List<String>> result = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        // boolean[] visited=new boolean[n];
        char[][] num = new char[n][n];
        for (char[] c : num) {
            Arrays.fill(c, '.');
        }
        backTracking(n, 0, num);
        return result;
    }

    void backTracking(int n, int row, char[][] num) {
        if (row == n) {
            result.add(Array2List(num));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!isValid(num, n, row, col)) {
                continue;
            }
            num[row][col] = 'Q';
            backTracking(n, row + 1, num);
            num[row][col] = '.';
        }
    }

    List<String> Array2List(char[][] num) {
        List<String> list = new ArrayList<>();
        for (char[] c : num) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    boolean isValid(char[][] num, int n, int row, int col) {
        for (int i = 0; i < n; i++) {
            if (num[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (num[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (num[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
//返回一个解
class solution51_1 {
    List<List<String>> result = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        // boolean[] visited=new boolean[n];
        char[][] num = new char[n][n];
        for (char[] c : num) {
            Arrays.fill(c, '.');
        }
        backTracking(n, 0, num);
        return result;
    }

    boolean backTracking(int n, int row, char[][] num) {
        if (row == n) {
            result.add(Array2List(num));
            return true;
        }
        for (int col = 0; col < n; col++) {
            if (!isValid(num, n, row, col)) {
                continue;
            }
            num[row][col] = 'Q';
            if(backTracking(n, row + 1, num)) return true;
            num[row][col] = '.';
        }
        return false;
    }

    List<String> Array2List(char[][] num) {
        List<String> list = new ArrayList<>();
        for (char[] c : num) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    boolean isValid(char[][] num, int n, int row, int col) {
        for (int i = 0; i < n; i++) {
            if (num[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (num[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (num[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}

/**
 * 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 */

/**
 * 思路：
 * 仔细想想，这就可以抽象成一幅图，每个节点有 8 个相邻的节点，又让你求最短距离，这不就是典型的 BFS 嘛
 */
class Solution752{
    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for(String s:deadends){
            deads.add(s);
        }
        Set<String> visited = new HashSet<>();
        Queue<String> que=new LinkedList<>();
        int step=0;
        que.offer("0000");
        visited.add("0000");

        while(!que.isEmpty()){
            int size=que.size();
            for (int i = 0; i < size; i++) {
                String cur= que.poll();
                //是否终点
                if(deads.contains(cur)){
                    continue;
                }
                if(cur.equals(target)){
                    return step;
                }
                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up=plusOne(cur,j);
                    if(!visited.contains(up)){
                        que.offer(up);
                        visited.add(up);
                    }
                    String down=minusOne(cur,j);
                    if(!visited.contains(down)){
                        que.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    String plusOne(String s,int j){
        char[] ch=s.toCharArray();
        if(ch[j]=='9'){
            ch[j]='0';
        }else{
            ch[j]+=1;
        }
        return new String(ch);
    }
    String minusOne(String s ,int j){
        char[] ch=s.toCharArray();
        if(ch[j]=='0'){
            ch[j]='9';
        }else{
            ch[j]-=1;
        }
        return new String(ch);
    }
}
//双向bfs
class Solution752_1{
    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // 用集合不用队列，可以快速判断元素是否存在
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
            Set<String> temp = new HashSet<>();

            /* 将 q1 中的所有节点向周围扩散 */
            for (String cur : q1) {
                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (q2.contains(cur))
                    return step;

                visited.add(cur);

                /* 将一个节点的未遍历相邻节点加入集合 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up))
                        temp.add(up);
                    String down = minusOne(cur, j);
                    if (!visited.contains(down))
                        temp.add(down);
                }
            }
            /* 在这里增加步数 */
            step++;
            // temp 相当于 q1
            // 这里交换 q1 q2，下一轮 while 就是扩散 q2
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }

    String plusOne(String s,int j){
        char[] ch=s.toCharArray();
        if(ch[j]=='9'){
            ch[j]='0';
        }else{
            ch[j]+=1;
        }
        return new String(ch);
    }
    String minusOne(String s ,int j){
        char[] ch=s.toCharArray();
        if(ch[j]=='0'){
            ch[j]='9';
        }else{
            ch[j]-=1;
        }
        return new String(ch);
    }
}