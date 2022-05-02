package classSort;

/**
 * * 定义类Student、包含三个属性：学号number（int），年级state（int、成绩score（int）
 *
 * * 创建20个学生对象，学号为1到20，年级（小学）和成绩（百以内）都有随机数确定
 * * 问题一：打印出三年级（state值为3）的学生信息
 * * 问题二：使用冒泡排序按学生成绩排序，并遍历所有学生信息
 * * 提示：
 * * 1）生成随机数：Math.random(),返回值类型Double
 * * 2）四舍五入取整数：Math.round(double d),返回值类型long
 */
//冒泡排序
public class studentSort {
    public static void main(String[] args) {
        Student[] students = new Student[10];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(i + 1, (int) (Math.random() * (6 - 1 + 1) + 1), (int) (Math.random() * (100 - 0 + 1) + 1));
        }
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].info());
        }
        System.out.println("三年级学生");
        for (int i = 0; i < students.length; i++) {
            if (students[i].state == 3) {
                System.out.println(students[i].info());
            }
        }
        //冒泡排序按学生成绩排序，并遍历所有学生信息
        System.out.println("冒泡排序按学生成绩排序");
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - 1 - i; j++) {
                if (students[j].score > students[j + 1].score) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].info());
        }
    }
}

class Student {
    int number;
    int state;
    int score;

    Student(int number, int state, int score) {
        this.number = number;
        this.state = state;
        this.score = score;
    }

    public String info() {//将输出方式定义为方法，输出时直接调用方法
        return "学号：" + number + "，年级：" + state + "，成绩为" + score;
    }
}
