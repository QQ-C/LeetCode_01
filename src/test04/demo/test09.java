package test04.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test09 {
    public static void main(String[] args) {
//        Solution093 solution093 = new Solution093();
//        String ans = solution093.replaceSpace("We are happy.");
//        System.out.println(ans);
//        Solution096 solution096 = new Solution096();
//        int ans = solution096.strStr("aabaabaafa","aabaaf");
//        System.out.println(ans);
        Solution097 solution097 = new Solution097();
        boolean ans = solution097.repeatedSubstringPattern("ababab");
        System.out.println(ans);
    }
}

class Solution091 {
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            s[l] ^= s[r];  //构造 a ^ b 的结果，并放在 a 中
            s[r] ^= s[l];  //将 a ^ b 这一结果再 ^ b ，存入b中，此时 b = a, a = a ^ b
            s[l] ^= s[r];  //a ^ b 的结果再 ^ a ，存入 a 中，此时 b = a, a = b 完成交换
            l++;
            r--;
        }
    }
}

//class Solution092{
//    public String reverseStr(String s ,int k){
//        char[] ch = s.toCharArray();
//        for (int i = 0; i < ch.length; i+=2*k) {
//            // 1. 每隔 2k 个字符的前 k 个字符进行反转
//            // 2. 剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符
//            if(i+k<=ch.length){
//                reverse(ch,i,i+k-1);
//                continue;
//            }
//            // 3. 剩余字符少于 k 个，则将剩余字符全部反转。
//            reverse(ch,i,ch.length-1);
//        }
//        return new String(ch);
//    }
//    void reverse(char[] ch,int start,int end){
//
//        for (int i = start,j=end; i <j ; i++,j--) {
//            char temp  = ch[i];
//            ch[i]=ch[j];
//            ch[j]=temp;
//        }
//    }
//}

//class Solution092 {
//    public String reverseStr(String s, int k) {
//        StringBuffer res = new StringBuffer();
//        int length = s.length();
//        int start = 0;
//        while (start < length) {
//            //找到k和2k处
//            StringBuffer temp = new StringBuffer();
//            // 与length进行判断，如果大于length了，那就将其置为length
//            int firstK = (start + k > length) ? length : start + k;
//            int secondK = (start + 2*k > length)?length:start+2*k;
//            //无论start所处位置，至少会反转一次
//            temp.append(s.substring(start,firstK));
//            res.append(temp.reverse());
//            // 如果firstK到secondK之间有元素，这些元素直接放入res里即可。
//            if (firstK < secondK) { //此时剩余长度一定大于k。
//                res.append(s.substring(firstK, secondK));
//            }
//            start += (2 * k);
//        }
//        return res.toString();
//    }
//}

//
//class Solution092 {
//    public String reverseStr(String s, int k) {
//        char[] ch = s.toCharArray();
//        for(int i = 0; i < ch.length; i += 2 * k){
//            int start = i;
//            //这里是判断尾数够不够k个来取决end指针的位置
//            int end = Math.min(ch.length - 1, start + k - 1);
//            //用异或运算反转
//            while(start < end){
//                ch[start] ^= ch[end];
//                ch[end] ^= ch[start];
//                ch[start] ^= ch[end];
//                start++;
//                end--;
//            }
//        }
//        return new String(ch);
//    }
//}


//class Solution093{
////使用一个新的对象，复制 str，复制的过程对其判断，是空格则替换，否则直接复制，类似于数组复制
//    public  String replaceSpace(String s) {
//        StringBuffer str = new StringBuffer();
//        str.append(s);
//        if(str==null){
//            return null;
//        }
//        //选用 StringBuilder 单线程使用，比较快，选不选都行
//        StringBuilder sb = new StringBuilder();
//        //使用 sb 逐个复制 str ，碰到空格则替换，否则直接复制
//        for (int i = 0; i < str.length(); i++) {
//            //str.charAt(i) 为 char 类型，为了比较需要将其转为和 " " 相同的字符串类型
//            //if (" ".equals(String.valueOf(str.charAt(i)))){
//            if (s.charAt(i) == ' ') {
//                sb.append("%20");
//            } else {
//                sb.append(str.charAt(i));
//            }
//        }
//        return sb.toString();
//    }
//}

//方式二：双指针法
class Solution093 {
    //方式二：双指针法
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        //扩充空间，空格数量2倍
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        //若是没有空格直接返回
        if (str.length() == 0) {
            return s;
        }
        //有空格情况 定义两个指针
        int left = s.length() - 1;//左指针：指向原始字符串最后一个位置
        s += str.toString();
        int right = s.length() - 1;//右指针：指向扩展字符串的最后一个位置
        char[] chars = s.toCharArray();
        while (left >= 0) {
            if (chars[left] == ' ') {
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            } else {
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }
}
////////自己写的，多了一个循环，不要了
//class Solution093 {
//    public String replaceSpace(String s) {
//        int count = 0;//统计空格的个数
//        int sOldSize = s.length();
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == ' ') {
//                count++;
//            }
//        }
//        // 扩充字符串s的大小，也就是每个空格替换成"%20"之后的大小
//        StringBuffer ans=new StringBuffer();
//
//        for (int i = 0; i < count * 2; i++) {
//            ans.append(" ");
//        }
//        s+=ans.toString();
//        int sNewSize=s.length();
//        char[] ch=s.toCharArray();
//        // 从后先前将空格替换为"%20"
//        int i = sNewSize-1,j=sOldSize-1;
//        while (j>=0){
//            if(ch[j]!=' '){
//                ch[i]=ch[j];
//            }else{
//                ch[i]='0';
//                ch[i-1]='2';
//                ch[i-2]='%';
//                i-=2;
//            }
//            j--;
//            i--;
//        }
//        return new String(ch);
//    }
//}

class Solution094 {
    /**
     * 不使用Java内置方法实现
     * <p>
     * 1.去除首尾以及中间多余空格
     * 2.反转整个字符串
     * 3.反转各个单词
     */
    public String reverseWords(String s) {
        // System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
        // 1.去除首尾以及中间多余空格
        StringBuilder sb = removeSpace(s);
        // 2.反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        // 3.反转各个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    private StringBuilder removeSpace(String s) {
        // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        // System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
        return sb;
    }

    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public void reverseString(StringBuilder sb, int start, int end) {
        // System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
        // System.out.println("ReverseWords.reverseString returned: sb = [" + sb + "]");
    }

    public void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;  //end++;
        }
    }

}

//class Solution094 {
//    public String reverseWords(String s) {
//        // 除去开头和末尾的空白字符
//        s = s.trim();
//        // 正则匹配连续的空白字符作为分隔符分割
//        List<String> wordList = Arrays.asList(s.split("\\s+"));
//        Collections.reverse(wordList);
//        return String.join(" ", wordList);
//    }
//}
////解法二：创建新字符数组填充。时间复杂度O(n)
//class Solution094 {
//    public String reverseWords(String s) {
//        //源字符数组
//        char[] initialArr = s.toCharArray();
//        //新字符数组
//        char[] newArr = new char[initialArr.length+1];//下面循环添加"单词 "，最终末尾的空格不会返回
//        int newArrPos = 0;
//        //i来进行整体对源字符数组从后往前遍历
//        int i = initialArr.length-1;
//        while(i>=0){
//            while(i>=0 && initialArr[i] == ' '){i--;}  //跳过空格
//            //此时i位置是边界或!=空格，先记录当前索引，之后的while用来确定单词的首字母的位置
//            int right = i;
//            while(i>=0 && initialArr[i] != ' '){i--;}
//            //指定区间单词取出(由于i为首字母的前一位，所以这里+1,)，取出的每组末尾都带有一个空格
//            for (int j = i+1; j <= right; j++) {
//                newArr[newArrPos++] = initialArr[j];
//                if(j == right){
//                    newArr[newArrPos++] = ' ';//空格
//                }
//            }
//        }
//        //若是原始字符串没有单词，直接返回空字符串；若是有单词，返回0-末尾空格索引前范围的字符数组(转成String返回)
//        if(newArrPos == 0){
//            return "";
//        }else{
//            return new String(newArr,0,newArrPos-1);
//        }
//    }
//}

////解法三：双反转+移位，在原始数组上进行反转。空间复杂度O(1)
//class Solution094 {
//    /**
//     * 思路：
//     *	①反转字符串  "the sky is blue " => " eulb si yks eht"
//     *	②遍历 " eulb si yks eht"，每次先对某个单词进行反转再移位
//     *	   这里以第一个单词进行为演示：" eulb si yks eht" ==反转=> " blue si yks eht" ==移位=> "blue si yks eht"
//     */
//    public String reverseWords(String s) {
//        //步骤1：字符串整体反转（此时其中的单词也都反转了）
//        char[] initialArr = s.toCharArray();
//        reverse(initialArr, 0, s.length() - 1);
//        int k = 0;
//        for (int i = 0; i < initialArr.length; i++) {
//            if (initialArr[i] == ' ') {
//                continue;
//            }
//            int tempCur = i;
//            while (i < initialArr.length && initialArr[i] != ' ') {
//                i++;
//            }
//            for (int j = tempCur; j < i; j++) {
//                if (j == tempCur) { //步骤二：二次反转
//                    reverse(initialArr, tempCur, i - 1);//对指定范围字符串进行反转，不反转从后往前遍历一个个填充有问题
//                }
//                //步骤三：移动操作
//                initialArr[k++] = initialArr[j];
//                if (j == i - 1) { //遍历结束
//                    //避免越界情况，例如=> "asdasd df f"，不加判断最后就会数组越界
//                    if (k < initialArr.length) {
//                        initialArr[k++] = ' ';
//                    }
//                }
//            }
//        }
//        if (k == 0) {
//            return "";
//        } else {
//            //参数三：以防出现如"asdasd df f"=>"f df asdasd"正好凑满不需要省略空格情况
//            return new String(initialArr, 0, (k == initialArr.length) && (initialArr[k - 1] != ' ') ? k : k - 1);
//        }
//    }
//
//    public void reverse(char[] chars, int begin, int end) {
//        for (int i = begin, j = end; i < j; i++, j--) {
//            chars[i] ^= chars[j];
//            chars[j] ^= chars[i];
//            chars[i] ^= chars[j];
//        }
//    }
//}

class Solution095 {
    public String reverseLeftWords(String s, int n) {
        int len=s.length();
        StringBuilder sb=new StringBuilder(s);
        reverseString(sb,0,n-1);
        reverseString(sb,n,len-1);
        return sb.reverse().toString();
    }
    public void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }
}


//class Solution096 {
//    /**
//     * 基于窗口滑动的算法
//     * <p>
//     * 时间复杂度：O(m*n)
//     * 空间复杂度：O(1)
//     * 注：n为haystack的长度，m为needle的长度
//     */
//    public int strStr(String haystack, String needle) {
//        int m=needle.length();
//        // 当 needle 是空字符串时我们应当返回 0
//        if(m==0){
//            return 0;
//        }
//        int n=haystack.length();
//        if (n<m){
//            return -1;
//        }
//        int i=0;
//        int j=0;
//        while(i<n-m+1){
//            // 找到首字母相等
//            while(i<n&&haystack.charAt(i)!=needle.charAt(j)){
//                i++;
//            }
//            if(i==n){  // 没有首字母相等的
//                return -1;
//            }
//            // 遍历后续字符，判断是否相等
//            i++;
//            j++;
//            while(i<n&&j<m&&haystack.charAt(i)==needle.charAt(j)){
//                i++;
//                j++;
//            }
//            if(j==m){
//                return i-j;
//            }else{
//                i-=j-1;
//                j=0;
//            }
//        }
//        return -1;
//    }
//}

class Solution096 {
    public int strStr(String haystack, String needle) {
        if (needle.length()==0){
            return 0;
        }
        int[] next = new int[needle.length()];
        getNext(next,needle);
        int j=-1;  // 因为next数组里记录的起始位置为-1
        for (int i=0;i<haystack.length();i++){  // 注意i就从0开始
            while(j>=0&&haystack.charAt(i)!=needle.charAt(j+1)){ // 不匹配
                j=next[j];  // j 寻找之前匹配的位置
            }
            if(haystack.charAt(i)==needle.charAt(j+1)){  // 匹配，j和i同时向后移动
                j++;    // i的增加在for循环里
            }
            if(j==needle.length()-1){  // 文本串s里出现了模式串t
                return (i-needle.length()+1);
            }
        }
        return -1;
    }
    public void getNext(int[] next,String s){
        //1.初始化
        int j=-1;        //是因为减一了
        next[0]=j;        //  next数组进行初始化赋值   next[i]表示 i（包括i）之前最长相等的前后缀长度（其实就是j）

        // 2.处理前后缀不相同的情况
        //因为j初始化为-1，那么i就从1开始，进行s[i] 与 s[j+1]的比较。
        for (int i = 1; i <s.length() ; i++) {
            while(j>=0&&s.charAt(i)!=s.charAt(j+1)){   //如果 s[i] 与 s[j+1]不相同，也就是遇到 前后缀末尾不相同的情况，就要向前回退。
                j=next[j];  //向前回退。
            }
            // 3.处理前后缀相同的情况
            if(s.charAt(i)==s.charAt(j+1)){  //如果s[i] 与 s[j + 1] 相同，那么就同时向后移动i 和j 说明找到了相同的前后缀，同时还要将j（前缀的长度）赋给next[i], 因为next[i]要记录相同前后缀的长度。
                j++;
            }
            next[i]=j;  // 将j（前缀的长度）赋给next[i]
        }
    }
}


//class Solution096 {
//    //前缀表（不减一）Java实现
//    public int strStr(String haystack, String needle) {
//        if (needle.length() == 0) return 0;
//        int[] next = new int[needle.length()];
//        getNext(next, needle);
//
//        int j = 0;
//        for (int i = 0; i < haystack.length(); i++) {
//            while (j > 0 && needle.charAt(j) != haystack.charAt(i))
//                j = next[j - 1];
//            if (needle.charAt(j) == haystack.charAt(i))
//                j++;
//            if (j == needle.length())
//                return i - needle.length() + 1;
//        }
//        return -1;
//
//    }
//
//    private void getNext(int[] next, String s) {
//        int j = 0;
//        next[0] = 0;
//        for (int i = 1; i < s.length(); i++) {
//            while (j > 0 && s.charAt(j) != s.charAt(i))
//                j = next[j - 1];
//            if (s.charAt(j) == s.charAt(i))
//                j++;
//            next[i] = j;
//        }
//    }
//}

class Solution097 {
    public boolean repeatedSubstringPattern(String s) {
//        if(s.length()==0){
//            return false;
//        }

        if(s.equals("")){
            return false;
        }
        int[] next=new int[s.length()];
        getNext(next,s);
        int length = s.length();
        if(next[length-1]!=-1&&length%(length-(next[length-1]+1))==0){
            return true;
        }
        return false;
    }
    private void getNext(int[] next, String s){
        next[0]=-1;
        int j=-1;
        for (int i = 1; i < s.length(); i++) {
            while(j>=0&&s.charAt(i)!=s.charAt(j+1)){
                j=next[j];
            }
            if(s.charAt(i)==s.charAt(j+1)){
                j++;
            }
            next[i]=j;
        }
    }
}