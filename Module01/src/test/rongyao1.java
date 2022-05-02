//package test;
//import java.util.*;
//
//public class rongyao1 {
//    List<String> setY;
//    public static void main(String[] args) {
//        string s;
//        getline(cin, s);
//        int cur = 0;
//        string a = s.substr(0, s.find(";"));
//        string b = s.substr(s.find(";") + 1);
//
//        cout << "OUT:";
//
//        fileY(a);
//        fileY(b);
//
//        int curA = 0;
//        cout << "a.so:";
//        string s1;
//        while (curA < a.size()) {
//            int pos = a.find(" N", curA + 1);
//            if (pos == -1) break;
//            curA = pos;
//            string file = a.substr(pos - 2, 2);
//            if (!setY.count(file)) s1 = s1 + file + ',';
//        }
//        s1.pop_back();
//        cout << s1;
//        cout << ";b.so:";
//
//        int curB = 0;
//        string s2;
//        while (curB < b.size()) {
//            int pos = b.find(" N", curB + 1);
//            if (pos == -1) break;
//            curB = pos;
//            string file = b.substr(pos - 2, 2);
//            if (!setY.count(file)) s2 = s2 + file + ',';
//        }
//        s2.pop_back();
//        cout << s2;
//
//        return 0;
//    }
//    void fileY(string s) {
//        int cur = 0;
//        while (cur < s.size()) {
//            int pos = s.find(" Y", cur + 1);
//            if (pos == -1) break;
//            cur = pos;
//            string file = s.substr(pos - 2, 2);
//            setY.insert(file);
//        }
//    }
//}
//
//
//
//
