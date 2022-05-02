package test01;
import java.awt.image.ImageProducer;
import java.io.ByteArrayOutputStream;
import java.util.*;

/** 旅行计划
 * 第一行两个正整数n和m，分别表示地图上的地点数（地点从1到n进行标号），和地图中包含的道路数。
 *
 * 接下来2行，每行m个正整数，其中第1行第 i 个数为u，其中第2行第 i 个数为v，表示地点u和地点v之间有一条直接的通路。（保证无重边，无自环）
 *
 * 接下来一个正整数q，表示小美想询问的次数。
 *
 * 接下来q行，每行两个正整数u和v，表示一次询问：地点u和地点v之间是否有一条直接的通路呢？
 *
 * (题中道路为双向路，数字间两两有空格隔开)
 *
 * n<=10000, m<=10000, q<=300
 *
 * 1<=u<=n, 1<=v<=n, u≠v
 */
//原做法
//public class test03 {
//        public static void main(String[] args) {
//            int n, m;   //表示地图上的地点数（地点从1到n进行标号），和地图中包含的道路数。
//            Scanner scan=new Scanner(System.in);
//            Map<Integer,Integer> out = new HashMap<>();
//            int[][] temp=new int[2][];
//            while (scan.hasNext()){
//                n=scan.nextInt();
//                m=scan.nextInt();
//                scan.nextLine();
//                for(int i=0;i<2;i++){
//                    int a=m;
//                    while(a-->0){
//                        int x;
//                        x=scan.nextInt();
//                        temp[i][a]=x;
//                    }
//                }
//                for(int i=0;i<m;i++){
//                    int x=temp[0][i];
//                    int y=temp[1][i];
//                    out.put(x,y);
//                    out.put(y,x);
//                }
//                int q;
//                q=scan.nextInt();
//                while(q-->0){
//                    int u;
//                    int v;
//                    u=scan.nextInt();
//                    v=scan.nextInt();
//                    if(out.get(u).equals(v)){
//                        System.out.println("Yes");
//                    }else {
//                        System.out.println("No");
//                    }
//                }
//            }
//        }
//}
//


//修改二：图方法
public class test03 {
    public static void main(String[] args) {
        int n, m;   //表示地图上的地点数（地点从1到n进行标号），和地图中包含的道路数。
        Scanner scan=new Scanner(System.in);

        while (scan.hasNext()){
            n=scan.nextInt();
            m=scan.nextInt();
            scan.nextLine();
            List<Integer>[] graph=new LinkedList[n+1];
            String[] str1=scan.nextLine().split(" ");
            String[] str2=scan.nextLine().split(" ");
            //很重要！！！
            for (int i = 0; i < n+1; i++) {
                graph[i] = new LinkedList<>();
            }
            //添加
            for(int i=0;i<m;i++){
                graph[Integer.parseInt(str1[i])].add(Integer.parseInt(str2[i]));
                graph[Integer.parseInt(str2[i])].add(Integer.parseInt(str1[i]));
            }
            int q;
            q=scan.nextInt();
            scan.nextLine();
            List<int[]>[] request=new LinkedList[q];
            for (int i = 0; i < q; i++) {
                request[i] = new LinkedList<>();
            }
            for (int i = 0; i <q ; i++) {
                int u;
                int v;
                u=scan.nextInt();
                v=scan.nextInt();
                int[] tmp=new int[]{u,v};
                scan.nextLine();
                request[i].add(tmp);
            }
            for (int i = 0; i < q; i++) {
                int u=request[i].get(0)[0];
                int v=request[i].get(0)[1];
                if(graph[u].contains(v)){
                    System.out.println("Yes");
                }else{
                    System.out.println("No");
                }
            }
        }
    }
}