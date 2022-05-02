package test01;
import java.util.*;
import java.util.jar.JarOutputStream;

/**
 * 第一行一个正整数n，表示士兵数
 *
 * 第二行n个空格隔开的正整数h[1,2,…n]，h[i]表示第 i 个士兵的高度。
 *
 * 第三行n个空格隔开的字符串s[1,2,…n], s[i]表示第 i 个士兵的名字，注意，士兵的名字仅包含小写英文字母且没有重复的名字。
 *
 * n<=50000，h[i]<=300 , s[i]仅包含小写英文字母且长度不大于10
 *
 * 输出一行，包含n个字符串s’[1,2,…n]，s‘[i]表示经过排序后从左到右站在第i个位置的士兵的名字，不同名字间用单个空格隔开。
 * 4
 * 176 170 176 176
 * beta tom alpha bamma
 *
 * tom alpha bamma beta
 */
//笔试写的
//public class test02 {
//    public static void main(String[] args) {
//        Scanner scan=new Scanner(System.in);
//        Map<String,String> map = new HashMap<>();
//
//        while(scan.hasNextLine()){
//            int n=Integer.parseInt(scan.nextLine());
//            String h = scan.nextLine();
//            String s = scan.nextLine();
//
//            String[] h1=h.split(" "); //身高
//            String[] s1=s.split(" "); //姓名
//            for(int i=0;i<n;i++){
//                map.put(s1[i],h1[i]);
//            }
//            List<String> list=new ArrayList<>(map.keySet());
//            list.sort((a,b)->Integer.parseInt(map.get(a))-Integer.parseInt( map.get(b)));
//            for(int i=0;i<n;i++){
//                System.out.print(list.get(i)+" ");
//            }
//        }//
//    }
//}

public class test02 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        Map<String,String> map = new HashMap<>();

        while(scan.hasNextLine()){
            int n=Integer.parseInt(scan.nextLine());
            String h = scan.nextLine();
            String s = scan.nextLine();

            String[] h1=h.split(" "); //身高
            String[] s1=s.split(" "); //姓名
            List<Soldiers> soldiers = new ArrayList<>();
            for(int i=0;i<n;i++){
                soldiers.add(new Soldiers(Integer.parseInt(h1[i]),s1[i]));
            }
            soldiers.sort(new Comparator<Soldiers>() {
                @Override
                public int compare(Soldiers o1, Soldiers o2) {
                    if(o1.height==o2.height){
                        return o1.name.compareTo(o2.name);
                    }else{
                        return o1.height-o2.height;
                    }
                }
            });
            for(int i=0;i<n;i++){
                System.out.print(soldiers.get(i).name+" ");
            }
        }//
    }
    static class Soldiers{
        int height;
        String name;
        public Soldiers(int height, String name) {
            this.height = height;
            this.name = name;
        }
    }
}
