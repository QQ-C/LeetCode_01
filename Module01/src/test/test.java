package test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {

//        vector<string> rec;
//        for (auto& [key, value] : cnt) {
//            rec.emplace_back(key);
//        }
//        sort(rec.begin(), rec.end(), [&](const string& a, const string& b) -> bool {
//            return cnt[a] == cnt[b] ? a < b : cnt[a] > cnt[b];
//        });
//        rec.erase(rec.begin() + k, rec.end());
//

        Scanner scan = new Scanner(System.in);

        while(scan.hasNextLine()){      //输入 可能要改一下
            int topN=scan.nextInt();
            int num_M=scan.nextInt();
            String[] word1=new String[num_M];  //标题
            String[] word2=new String[num_M];  //正文

            List<String[]> list1=new ArrayList<>();
            List<String[]> list2=new ArrayList<>();

            for(int i=1;i<=2*num_M;i++){
                if(i%2==0){
                    word2[i]=scan.nextLine();
                    String[] word2Array = word2[i].split(" ");
                    list1.add(word2Array);
                }else{
                    word1[i]=scan.nextLine();
                    String[] word1Array = word1[i].split(" ");
                    list2.add(word1Array);
                }
            }

            Map<String,Integer> count1=new HashMap<>();  //统计单词的个数
            Map<String,Integer> count2=new HashMap<>();  //统计单词的个数

            for(int i=0;i<word1.length;i++){
                String[] temp=list1.get(i);
                for(String word:temp){
                    count1.put(word,count1.getOrDefault(word,0)+1*3);
                }
            }
            for(int i=0;i<word2.length;i++){
                String[] temp=list2.get(i);
                for(String word:temp){
                    count2.put(word,count2.getOrDefault(word,0)+1);
                }
            }
// 这里进行合并 因为排序要用
            Map<String,Integer> count=new HashMap<>(count1);  //统计单词的个数
            count2.forEach((key,value)->count.merge(key,value,(v1,v2)->(v1+v2)));    //使用map1+map2的value值

            // 2.用 list 存储字符 key 然后自定义 Comparator 比较器对 value 进行排序。
            List<String> candidates = new ArrayList<>(count.keySet());
            candidates.sort((a, b) -> {
                // 字符串频率相等按照
                if (count.get(a).equals(count.get(b))) {
                    if(count1.get(a).equals(count1.get(b))){    //标题
                        return count2.get(b) - count2.get(a);
                    }else{
                        return count1.get(b) - count1.get(a);
                    }
                } else {
                    // 字符串频率不等则按照频率排列。
                    return count.get(b) - count.get(a);
                }
            });
            // 3.截取前 K 大个高频单词返回结果。
            for(int i=0;i<topN;i++){
                System.out.println(candidates.get(i)+" ");
            }
           // candidates.subList(0, topN);
        }
    }
}



