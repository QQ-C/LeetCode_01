//package test;
//import java.util.*;
//
//public class rongyao2 {
//    List<String> setY;
//    public static void main(String[] args) {
//        string s;
//        getline(cin, s);
//        int cur = 0;
//        vector<string> sos;
//        stringstream ss(s);
//        char c=';';
//        string str;
//        while(getline(ss,str,c)){
//            sos.push_back(str);
//        }
//        cout << "OUT:";
//        for(auto so:sos){
//            fileY(so);
//        }
//        for(int i=0;i<sos.size();i++){
//            int cur = 0;
//            cout << sos[i].substr(0,sos[i].find(':'))<<':';
//            string s1;
//            while (cur < sos[i].size()) {
//                int pos = sos[i].find(" N", cur + 1);
//                if (pos == -1) break;
//                cur = pos;
//                string file = sos[i].substr(pos - 2, 2);
//                if (!setY.count(file)) s1 = s1 + file + ',';
//            }
//            s1.pop_back();
//            cout << s1;
//            if(i!=sos.aize()-1) cout<<';';
//        }
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
