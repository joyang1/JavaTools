package cn.tommyyang.Tools;

import cn.tommyyang.Constant.Constant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TommyYang on 2017/11/19.
 */
public class Utils {

    public static Set getStartFlags() {
        Set<String> set = new HashSet<String>();
        for (int i = 1; i <= 135; i++) {
            set.add(i+".");
        }
        for (int i = 1; i <= 135; i++) {
            set.add(i+"、");
        }
        for (int i = 1; i <= 135; i++) {
            set.add(i+"");
        }
        return set;
    }

    public static List getJiexiStartFlag(){
        List<String> list = new ArrayList<String>();
        for (int i=1; i <= 135; i++){
            list.add(i + Constant.JieXi);
        }
        return list;
    }

    public static List getOptionFlags() {
        List<String> list = new ArrayList<String>();
        list.add("A.");
        list.add("B.");
        list.add("C.");
        list.add("D.");
        return list;
    }

    public static List getJiexiOptionFlags() {
        List<String> list = new ArrayList<String>();
        list.add("【A】");
        list.add("【B】");
        list.add("【C】");
        list.add("【D】");
        return list;
    }

    public static List getFlags() {
        List<String> list = new ArrayList<String>();
        list.add("常识判断");
        list.add("判断推理");
        return list;
    }

    public static String[] getHeaders() {
        String[] strings = new String[]{"题目", "难度",
                "关键词", "答案选项1", "答案选项2", "答案选项3", "答案选项4","正确答案","解析"};
        return strings;
    }

    public static String formatLine(String line){
        String aerror = "A．";
        String berror = "B．";
        String cerror = "C．";
        String derror = "D．";
        String d_unknowerror = "D.";
        String a_error = "A " + Constant.JieXi;
        String b_error = "B " + Constant.JieXi;
        String c_error = "C " + Constant.JieXi;
        String d_error = "D " + Constant.JieXi;
        if(line.contains(aerror)){
            line = line.replace(aerror, "A.");
        }
        if(line.contains(berror)){
            line = line.replace(berror, "B.");
        }
        if(line.contains(cerror)){
            line = line.replace(cerror, "C.");
        }
        if(line.contains(derror)){
            line = line.replace(derror, "D.");
        }
        if(line.contains(d_unknowerror)){
            line = line.replace(d_unknowerror, "D.");
        }
        if(line.contains("?")){
            line = line.replace("?","");
        }
        if(line.contains(a_error)){
            line = line.replace(a_error, Constant.AJieXi);
        }
        if(line.contains(b_error)){
            line = line.replace(b_error, Constant.BJieXi);
        }
        if(line.contains(c_error)){
            line = line.replace(c_error, Constant.CJieXi);
        }
        if(line.contains(d_error)){
            line = line.replace(d_error, Constant.DJieXi);
        }
        return line;
    }

    public static String getExcelFileName(String txtName){
        if(txtName.endsWith(".txt")){
            return txtName.replace(".txt",".xls");
        }
        return "";
    }

}
