package classSort;

import java.util.*;

/**
 * 用于基本数据类型的数组排序，用到的是Arrays类下的重载静态方法sort( );
 */
public class arraySort {
    public static void main(String[] args) {
/**********************/
//        Person[] p1 = new Person[]{
//                new Person("Ja", 25),
//                new Person("Ai", 17),
//                new Person("Aa", 17),
//                new Person("Lo", 32),
//                new Person("Hi", 12),
//        };
//
//        Arrays.sort(p1);
//        System.out.println(Arrays.toString(p1));
/********************************/
//        Scanner scanner =new Scanner(System.in);
//        int n=scanner.nextInt();
//        Person01[] ps01=new Person01[n];
//        for (int i = 0; i < n; i++) {
//            Person01 p =new Person01(i+1,scanner.nextInt(),scanner.nextInt(),scanner.nextInt());
//            ps01[i]=p;
//        }
//        Arrays.sort(ps01);
//        for (int i = 0; i < ps01.length; i++) {
//            System.out.println(ps01[i].toString());
//        }
        /**************************************/
        //这里使用ArrayList数组对Person对象进行排序。
        ArrayList<Person02> p=new ArrayList<>();
        p.add(new Person02("ja",25));
        p.add(new Person02("Az",18));
        p.add(new Person02("Ai",18));
        p.add(new Person02("Ab",15));
        p.add(new Person02("cc",28));
        p.add(new Person02("dd",26));
        System.out.println(p);

        //方法一：先使用Collections.sort对其排序，重写compare函数
        Collections.sort(p, new Comparator<Person02>() {
            @Override
            public int compare(Person02 o1, Person02 o2) {
                return o1.getAge()-o2.age;
            }
        });
        System.out.println(p);

        //方法二：使用p.sort对其排序，重写compare函数
        p.sort(new Comparator<Person02>() {
            @Override
            public int compare(Person02 o1, Person02 o2) {
                if(o1.age==o2.age){
                    return o1.name.compareTo(o2.name);
                }
                return o1.getAge()-o2.age;
            }
        });
        System.out.println(p);

        Collections.shuffle(p);//由于前面排序了，这里把数组打乱
        System.out.println(p);

        //方法三：使用Lambda
        p.sort((Person02 o1,Person02 o2)->o1.getAge()-o2.age);
        System.out.println(p);

        Collections.shuffle(p);//由于前面排序了，这里把数组打乱
        //方法四：
        p.sort(((o1, o2) -> o1.getAge()-o2.getAge()));//简化一下
    }
}


class arraySortTest1 {
    /**
     * Arrays.sort()进行排序，进行是升序排序。
     */
    void arraySortTest1(int[] arr) {
        arr = new int[]{1, 6, 5, 4, 8, 9};
        Arrays.sort(arr);   //升序
        System.out.println(Arrays.toString(arr));
    }
    /**
     *  如果要对自定义类的对象数组进行排序呢。
     *  该类就需要实现Comparable接口，重写compareTo();
     *  在compareTo方法中需要表现出是对类的那个字段进行排序，并且可以升降排序
     */

}

/**
 * 下面对Person类举例，
 * 对其按age从大到小排序，如何age相等则按name从小到大排序。
 */
class Person implements Comparable<Person> {
    String name;
    int age;

    Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int compareTo(Person o) {
        if (o.age == this.age) {
            return this.name.compareTo(o.name); //字典排序
        }
        return o.age - this.age; //大--》小
    }

    public String toString() {
        return "(姓名:" + name + ",年龄" + age + ")";
    }
}

/**
 * 输入格式
 *
 * 输入文件包含 n+1n+1 行:
 *
 * 第 11 行为一个正整数 nn，表示该校参加评选的学生人数。
 *
 * 第 22 到 n+1n+1 行，每行有 33 个用空格隔开的数字，每个数字都在 00 到 100100 之间，第 jj 行的 33 个数字依次表示学号为 j−1j−1 的学生的语文、数学、英语的成绩。
 *
 * 每个学生的学号按照输入顺序编号为 1∼n1∼n (恰好是输入数据的行号减 11)。
 *
 * 所给的数据都是正确的，不必检验。
 *
 * 输出格式
 *
 * 输出文件共有 55 行，每行是两个用空格隔开的正整数，依次表示前 55 名学生的学号和总分。
 */
class Person01 implements Comparable<Person01>{
    int id;
    int y;
    int s;
    int e;
    public int sum;
    Person01(int id,int y,int s,int e){
        this.id=id;
        this.y=y;
        this.s=s;
        this.e=e;
        this.sum=y+s+e;
    }
    @Override
    public int compareTo(Person01 o) {
        if(this.sum==o.sum){
            if(this.y==o.y){
                return this.id-o.id;   //小--大
            }
            return o.y-this.y;    //大--小
        }
        return o.sum-this.sum;   //大--小
    }
    @Override
    public String toString() {
        return id +" "+sum;
    }
}

/**
 *   Person类不实现Comparable接口，在排序时重载compare方法就可。
 */
class Person02 {
    String name;
    int age;
    Person02(String name, int age) {
        this.age = age;
        this.name = name;
    }
    public String toString() {
        return "(姓名:" + name + ",年龄" + age + ")";
    }
    public int getAge(){
        return this.age;
    }
    public String getName(){
        return this.name;
    }
}
//三数之和
//class Solution {
//    public List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        Arrays.sort(nums);  //双指针要排序
//        // 找出a + b + c = 0
//        // a = nums[i], b = nums[left], c = nums[right]
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] > 0) {
//                return result;
//            }
//            // 正确去重方法   可以包含（-1，-1,2）的特殊情况
//            if (i > 0 && nums[i] == nums[i - 1]) {
//                continue;
//            }
//            int left = i + 1;
//            int right = nums.length - 1;
//            while (right > left) {
//                int sum = nums[i] + nums[left] + nums[right];
//                if (sum > 0) {
//                    right--;
//                } else if (sum < 0) {
//                    left++;
//                } else {
//                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
//                    // 去重逻辑应该放在找到一个三元组之后
//                    while (right > left && nums[right] == nums[right - 1]) right--;
//                    while (right > left && nums[left] == nums[left + 1]) left++;
//                // 找到答案时，双指针同时收缩
//                    right--;
//                    left++;
//                }
//            }
//        }
//        return result;
//    }
//}