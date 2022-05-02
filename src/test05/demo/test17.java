package test05.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class test17 {
    public static void main(String[] args) {
        Solution017 solution017 = new Solution017();
        solution017.topKFrequent(new int[]{2,2,1,1,1,3},1);
    }
}
class Solution017 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result =new int[k];
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        Set<Map.Entry<Integer,Integer>> entries=map.entrySet();
        // 根据map的value值正序排，相当于一个小顶堆  //https://zhuanlan.zhihu.com/p/50104612
        PriorityQueue<Map.Entry<Integer,Integer>>queue = new PriorityQueue<>((o1,o2)->o1.getValue()-o2.getValue());  //哪一个小，哪一个在上面
        for (Map.Entry<Integer, Integer> entry : entries) {   //https://blog.csdn.net/songfeihu0810232/article/details/80156626
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }
}