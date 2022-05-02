package jianzhiOffer.offer5;

/**
 *剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class mySolution {
    public String replaceSpace(String s) {
        if(s==null){
            return null;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==' '){
                sb.append('%');
                sb.append('2');
                sb.append('0');
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}

class mySolution_1 {
    public String replaceSpace(String s) {
        if(s==null){
            return null;
        }
        int count=0;
        int length=s.length();
        for(int i=0;i<length;i++){
            if(s.charAt(i)==' '){
                count++;
            }
        }

        // 扩充字符串s的大小，也就是每个空格替换成"%20"之后的大小
        for (int i = 0; i < count * 2; i++) {
           s=s+" ";
        }
        char[] ch=s.toCharArray();
        int lastIndex=ch.length-1;
        for(int i=length-1;i>=0;i--){
            if(ch[i]==' '){
                ch[lastIndex--]='0';
                ch[lastIndex--]='2';
                ch[lastIndex--]='%';
            }else{
                ch[lastIndex--]=ch[i];
            }
        }
        return new String(ch);
    }
}