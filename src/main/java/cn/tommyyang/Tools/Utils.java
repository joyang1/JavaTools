package cn.tommyyang.Tools;

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
        for (int i = 1; i <= 125; i++) {
            set.add(i+"");
        }
        return set;
    }

    public static List getOptionFlags() {
        List<String> list = new ArrayList<String>();
        list.add("A.");
        list.add("B.");
        list.add("C.");
        list.add("D.");
        list.add("D．");
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
        if(line.contains(aerror)){
            line = line.replace(aerror, "A.");
        }else if(line.contains(berror)){
            line = line.replace(berror, "B.");
        }else if(line.contains(cerror)){
            line = line.replace(cerror, "C.");
        }else if(line.contains(derror)){
            line = line.replace(derror, "D.");
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
