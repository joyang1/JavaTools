package cn.tommyyang.Tools;

import cn.tommyyang.Constant.Constant;
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
        String dananContent = "";
        String jiexiContent = "";
        Boolean isTimuNow = false;
        Boolean isJiexiNow = false;
        List<Question> questions = new ArrayList<Question>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            //InputStreamReader inReader = new InputStreamReader(inputStream, "UTF-8");
            UnicodeReader ur = new UnicodeReader(inputStream, "UTF-8");
            BufferedReader bufReader = new BufferedReader(ur);
            String tiGanContent = "";
            while (bufReader.ready()) {
                try {
                    String line = Utils.formatLine(bufReader.readLine().trim());
                    if (line.equals("") || line.length() < 1) {
                        continue;
                    }

                    Boolean timuStartFlag = false;
                    if(line.length() > 3){
                        timuStartFlag = qStart.contains(line.substring(0, 1)) || qStart.contains(line.substring(0, 2)) || qStart.contains(line.substring(0, 3));
                    }
                    if(timuStartFlag){
                        isTimuNow = true;
                    }

                    Boolean isJieXiStart = line.contains(Constant.JieXiStart1) || line.contains(Constant.JieXiStart2) || line.startsWith(Constant.JieXiStart3);
                    if(isJieXiStart){
                        isJiexiNow = true;
                    }

                    Boolean aflag = line.startsWith(aStart);
                    if(aflag){
                        isTimuNow = false;
                    }

                    if (line.startsWith(aStart) && line.startsWith(bStart) && !isJiexiNow && !isTimuNow) {
                        aContent = line.substring(line.indexOf(aStart), line.indexOf(bStart));
                        bContent = line.substring(line.indexOf(bStart), line.length());
                        continue;
                    } else if (line.startsWith(bStart) && line.startsWith(cStart) && line.startsWith(dStart)  && !isJiexiNow && !isTimuNow) {
                        bContent = line.substring(line.indexOf(bStart), line.indexOf(cStart));
                        cContent = line.substring(line.indexOf(cStart), line.indexOf(dStart));
                        dContent = line.substring(line.indexOf(dStart), line.length());
                        continue;
                    } else if (line.startsWith(cStart) && line.startsWith(dStart)  && !isJiexiNow && !isTimuNow) {
                        cContent = line.substring(line.indexOf(cStart), line.indexOf(dStart));
                        dContent = line.substring(line.indexOf(dStart), line.length());
                        continue;
                    } else if (aflag  && !isJiexiNow && !isTimuNow) {
                        aContent = line;
                        continue;
                    } else if (line.startsWith(bStart)  && !isJiexiNow && !isTimuNow) {
                        bContent = line;
                        continue;
                    } else if (line.startsWith(cStart)  && !isJiexiNow && !isTimuNow) {
                        cContent = line;
                        continue;
                    } else if (line.startsWith(dStart)  && !isJiexiNow && !isTimuNow) {
                        dContent = line;
                        continue;
                    }

                    if ((line.contains(Constant.DanAnStart1) || line.startsWith(Constant.DanAnStart2) || line.contains(Constant.DanAnStart3) || line.startsWith(Constant.DanAnStart4)) && !isJiexiNow) {
                        dananContent = line;
                        continue;
                    }

                    if(timuStartFlag && !jiexiContent.equals("")){
                        Question question = new Question();
                        question.setKey("");
                        question.setTiMuContent(tiMuContent);
                        question.setaContent(aContent);
                        question.setbContent(bContent);
                        question.setcContent(cContent);
                        question.setdContent(dContent);
                        question.setRightAnswer(dananContent);
                        question.setParseContent(jiexiContent);
                        question.setDifficulty(1);
                        questions.add(question);
                        aContent = "";
                        bContent = "";
                        cContent = "";
                        dContent = "";
                        tiMuContent = "";
                        dananContent = "";
                        jiexiContent = "";
                        isJiexiNow = false;
                    }

                    if (isJieXiStart || isJiexiNow) {
                        jiexiContent += line;
                        continue;
                    }
                    if (timuStartFlag || isTimuNow) {
                        tiMuContent += line;
                        continue;
                    }
                }catch (Exception e){
                    aContent = "";
                    bContent = "";
                    cContent = "";
                    dContent = "";
                    tiMuContent = "";
                    dananContent = "";
                    jiexiContent = "";
                    isTimuNow = false;
                    isJiexiNow = false;
                }
            }
            if(!jiexiContent.equals("")){
                Question question = new Question();
                question.setKey("");
                question.setTiMuContent(tiMuContent);
                question.setaContent(aContent);
                question.setbContent(bContent);
                question.setcContent(cContent);
                question.setdContent(dContent);
                question.setRightAnswer(dananContent);
                question.setParseContent(jiexiContent);
                question.setDifficulty(1);
                questions.add(question);
            }
            return questions;
        } catch (Exception e) {
            if(questions.size()>0){
                return questions;
            }
            System.out.println(e);
        }
        return null;
    }

    private static Boolean check(List<String> items, String checkItem) {
        for (String item : items) {
            if (item.equals(checkItem)) {
                return true;
            }
        }
        return false;
    }

}
