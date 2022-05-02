package test;

import java.util.*;

//public class rongyao {
//    public static void main(String[] args) {
//        Scanner scan=new Scanner(System.in);
//        String digits= scan.nextLine();
//        Solution solution=new Solution();
//        solution.letterCombinations(digits);
//    }
//}
//
//class Solution {
//    public void letterCombinations(String digits) {
//        String str = "";
//        if (digits == null || digits.length() == 0) {
//            System.out.println(str);
//        }
//        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//        //迭代处理
//        for (int i = 0; i < digits.length(); i++) {
//            char c = digits.charAt(i);
//            char temp = c;
//            if (Character.isDigit(c)) {
//                str = str + c;
//            } else if (c >= 'A' && c <= 'Z') {
//                temp = (char) (c + 32 - 1);  //变为小写字母 然后向前移一位
//            } else if (c >= 'a' && c <= 'z') {
//                temp = c;
//            } else {
//                continue;
//            }
//            for (int j = 0; j < 10; j++) {
//                for (int k = 0; k < numString[j].length(); k++) {
//                    if (numString[j].charAt(k) == temp) {
//                        str = str + j;
//                    }
//                }
//            }
//
//        }
//        System.out.println(str);
//
//    }
//}

public class rongyao {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String digits= scan.nextLine();
        Solution solution=new Solution();
        solution.a2(digits);
    }
}

class Solution {
    public void a2(String s) {
        int cur=0;
        while(cur<s.length()){

        }
    }
}