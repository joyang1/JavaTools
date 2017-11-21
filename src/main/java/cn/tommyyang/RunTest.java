package cn.tommyyang;

import cn.tommyyang.Tools.ExcelTool;
import cn.tommyyang.Tools.FileHepler;
import cn.tommyyang.Tools.Utils;
import cn.tommyyang.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TommyYang on 2017/11/19.
 */
public class RunTest {

    public static void main(String[] args){
        String path = "E:\\test.txt";
        String destPath = "E:\\test.xls";
        String[] fileHeader = Utils.getHeaders();
        List<Question> questions = FileHepler.readFileContent(path);
        List<String[]> datas = new RunTest().getDatas(questions);
        ExcelTool.writeExcel(destPath, "试卷内容",0,fileHeader, datas);
    }

    private List<String[]> getDatas(List<Question> questions) {
        List<String[]> datas = new ArrayList<String[]>();
        for (Question item : questions) {
            String[] items = new String[]{item.getType(), item.getQtype(), item.getTiMuContent(),
                    item.getaContent(), item.getbContent(), item.getcContent(), item.getdContent()};
            datas.add(items);
        }
        return datas;
    }

}
