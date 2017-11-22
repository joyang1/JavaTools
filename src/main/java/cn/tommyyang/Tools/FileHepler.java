package cn.tommyyang.Tools;

import cn.tommyyang.model.Question;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.System.in;

/**
 * Created by TommyYang on 2017/11/19.
 */
public class FileHepler {

    public static List<Question> readFileContent(String filePath) {
        List<String> tiGanTypes = Utils.getFlags();
        Set<String> qStart = Utils.getStartFlags();
        List<String> optionStart = Utils.getOptionFlags();
        String aStart = optionStart.get(0);
        String bStart = optionStart.get(1);
        String cStart = optionStart.get(2);
        String dStart = optionStart.get(3);
        String aContent = "";
        String bContent = "";
        String cContent = "";
        String dContent = "";
        String tiMuContent = "";
        List<Question> questions = new ArrayList<Question>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            //InputStreamReader inReader = new InputStreamReader(inputStream, "UTF-8");
            UnicodeReader ur = new UnicodeReader(inputStream, "UTF-8");
            BufferedReader bufReader = new BufferedReader(ur);
            String tiGanContent = "";
            while (bufReader.ready()) {
                String line = Utils.formatLine(bufReader.readLine().trim());
                if (line.equals("") || line.length() < 1) {
                    continue;
                }

                if (check(tiGanTypes, line)) {
                    tiGanContent = line;
                } else {
                    Boolean flag = line.contains(aStart);
                    if (qStart.contains(line.substring(0, 1)) || !flag) {
                        tiMuContent += line;
                    }
                    if (line.contains(aStart) && line.contains(bStart)) {
                        aContent = line.substring(line.indexOf(aStart), line.indexOf(bStart));
                        bContent = line.substring(line.indexOf(bStart), line.length());
                    }else if (line.contains(bStart) && line.contains(cStart) && line.contains(dStart)) {
                        bContent = line.substring(line.indexOf(bStart), line.indexOf(cStart));
                        cContent = line.substring(line.indexOf(cStart), line.indexOf(dStart));
                        dContent = line.substring(line.indexOf(dStart), line.length());
                        Question question = new Question();
                        question.setType(tiGanContent);
                        question.setQtype("单选");
                        question.setKey("");
                        question.setTiMuContent(tiMuContent);
                        question.setaContent(aContent);
                        question.setbContent(bContent);
                        question.setcContent(cContent);
                        question.setdContent(dContent);
                        questions.add(question);
                        aContent = "";
                        bContent = "";
                        cContent = "";
                        dContent = "";
                        tiMuContent = "";
                    } else if (line.contains(cStart) && line.contains(dStart)) {
                        cContent = line.substring(line.indexOf(cStart), line.indexOf(dStart));
                        dContent = line.substring(line.indexOf(dStart), line.length());
                        Question question = new Question();
                        question.setType(tiGanContent);
                        question.setQtype("单选");
                        question.setKey("");
                        question.setTiMuContent(tiMuContent);
                        question.setaContent(aContent);
                        question.setbContent(bContent);
                        question.setcContent(cContent);
                        question.setdContent(dContent);
                        questions.add(question);
                        aContent = "";
                        bContent = "";
                        cContent = "";
                        dContent = "";
                        tiMuContent = "";
                    }else if (flag) {
                        aContent = line;
                    } else if (line.contains(bStart)) {
                        bContent = line;
                    } else if (line.contains(cStart)) {
                        cContent = line;
                    } else if (line.contains(dStart)) {
                        dContent = line;
                        Question question = new Question();
                        question.setType(tiGanContent);
                        question.setQtype("单选");
                        question.setKey("");
                        question.setTiMuContent(tiMuContent);
                        question.setaContent(aContent);
                        question.setbContent(bContent);
                        question.setcContent(cContent);
                        question.setdContent(dContent);
                        questions.add(question);
                        aContent = "";
                        bContent = "";
                        cContent = "";
                        dContent = "";
                        tiMuContent = "";
                    }
                }
            }
            return questions;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static Boolean check(List<String> items, String checkItem){
        for (String item: items) {
            if(item.equals(checkItem)){
                return true;
            }
        }
        return false;
    }

}
