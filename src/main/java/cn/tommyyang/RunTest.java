package cn.tommyyang;

import cn.tommyyang.Tools.ExcelTool;
import cn.tommyyang.Tools.FileHepler;
import cn.tommyyang.Tools.Utils;
import cn.tommyyang.Tools.WordTool;
import cn.tommyyang.model.Question;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by TommyYang on 2017/11/19.
 */
public class RunTest {


    public static void main(String[] args){
        runtxtToExcel();
        //runword();
        //runFormatTxtJiexi();
        //runTxtAddJiexi();
    }

    private static  void runtxtToExcel(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("设置试卷号(设置完点击Enter键即可):");
        Question.timustart = scanner.nextLine();

        String path = "E:\\runtest";
        File directory = new File(path);
        if(directory.isDirectory()){
            File[] files = directory.listFiles();
            for (File file:files) {
                String name = file.getName();
                String xlsName = Utils.getExcelFileName(name);
                String txtPath = path + "\\" + name;
                String xlsPath = path + "\\" + xlsName;
                String[] fileHeader = Utils.getHeaders();
                List<Question> questions = FileHepler.readFileContent(txtPath);
                List<String[]> datas = new RunTest().getDatas(questions);
                ExcelTool.writeExcel(xlsPath, "试卷内容",0,fileHeader, datas);
            }
        }

        System.out.println("转化完成!文件生成在E:/runtest目录下！");
        System.out.println("按Enter(回车)键退出！");
        while(true){
            try {
                if(System.in.read() == '\n')
                    System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void runFormatTxtJiexi(){
        String path = "E:\\runtest2\\答案5.txt";
        String content = FileHepler.generateJiexi(path);
        FileHepler.writeContentToTxt(content, "E:\\runtest2\\jiexiresult.txt");
    }

    private static void runTxtAddJiexi(){
        String txtJiexiPath = "E:\\runtest2\\jiexiresult.txt";
        String path = "E:\\runtest1";
        File directory = new File(path);
        if(directory.isDirectory()){
            File[] files = directory.listFiles();
            for (File file:files) {
                String name = file.getName();
                String txtPath = path + "\\" + name;
                FileHepler.combineTxt(txtPath, txtJiexiPath);
            }
        }

    }

    private static void runword(){
        String path = "E:\\runtest1";
        try {
            File directory = new File(path);
            if(directory.exists()){
                File[] files = directory.listFiles();
                for (File file: files) {
                    String wordPath = file.getAbsolutePath();
                    String content;
                    if(file.getName().endsWith("doc")){
                        content = WordTool.reaDoc(wordPath);
                    }else {
                        content= WordTool.reaDocx(wordPath);
                    }

                    String desPath = path +"\\"+ Utils.getFileName(file.getName());
                    FileHepler.writeContentToTxt(content, desPath);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<String[]> getDatas(List<Question> questions) {
        List<String[]> datas = new ArrayList<String[]>();
//        for (Question item : questions) {
//            String[] items = new String[]{ item.getTiMuContent(),item.getDifficulty().toString(), item.getKey(),
//                    item.getaContent(), item.getbContent(), item.getcContent(), item.getdContent(),item.getRightAnswer(),item.getParseContent()};
//            datas.add(items);
//        }

        for(int i= questions.size() - 1; i >=0; i--){
            Question item = questions.get(i);
            String[] items = new String[]{item.getTiMuContent(), item.getDifficulty().toString(), item.getKey(),
                    item.getaContent(), item.getbContent(), item.getcContent(), item.getdContent(), item.getRightAnswer(), item.getParseContent()};
            datas.add(items);
        }
        return datas;
    }

}
