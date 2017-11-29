package cn.tommyyang.model;

import cn.tommyyang.Constant.Constant;

/**
 * Created by TommyYang on 2017/11/19.
 */
public class Question {

    public static String timustart = "";

    private String type;
    private String qtype;
    private String key;
    private String tiMuContent;
    private String aContent;
    private String bContent;
    private String cContent;
    private String dContent;
    private String rightAnswer;
    private String parseContent;
    private Integer difficulty;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTiMuContent() {
        return tiMuContent;
    }

    public void setTiMuContent(String tiMuContent) {
        tiMuContent = "(" + timustart + ")" + tiMuContent;
        this.tiMuContent = tiMuContent;
    }

    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        aContent = aContent.replace("A.", "");
        this.aContent = aContent;
    }

    public String getbContent() {
        return bContent;
    }

    public void setbContent(String bContent) {
        bContent = bContent.replace("B.", "");
        this.bContent = bContent;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        cContent = cContent.replace("C.", "");
        this.cContent = cContent;
    }

    public String getdContent() {
        return dContent;
    }

    public void setdContent(String dContent) {
        dContent = dContent.replace("D.", "");
        this.dContent = dContent;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        if (rightAnswer.contains(Constant.DanAnStart1)) {
            rightAnswer = rightAnswer.replace(Constant.DanAnStart1, "");
        } else if (rightAnswer.startsWith(Constant.DanAnStart2)) {
            rightAnswer = rightAnswer.replace(Constant.DanAnStart2, "");
        } else if (rightAnswer.contains(Constant.DanAnStart3)) {
            rightAnswer = rightAnswer.replace(Constant.DanAnStart3, "");
        } else if (rightAnswer.startsWith(Constant.DanAnStart4)) {
            rightAnswer = rightAnswer.replace(Constant.DanAnStart4, "");
        } else if (rightAnswer.startsWith(Constant.DanAnStart5)) {
            rightAnswer = rightAnswer.replace(Constant.DanAnStart5, "");
        }
        if (rightAnswer.trim().equals("A")) {
            rightAnswer = 1 + "";
        } else if (rightAnswer.trim().equals("B")) {
            rightAnswer = 2 + "";
        } else if (rightAnswer.trim().equals("C")) {
            rightAnswer = 3 + "";
        } else if (rightAnswer.trim().equals("D")) {
            rightAnswer = 4 + "";
        }
        this.rightAnswer = rightAnswer;
    }

    public String getParseContent() {
        return parseContent;
    }

    public void setParseContent(String parseContent) {
        if (parseContent.contains(Constant.JieXiStart1)) {
            parseContent = parseContent.replace(Constant.JieXiStart1, "");
        } else if (parseContent.contains(Constant.JieXiStart2)) {
            parseContent = parseContent.replace(Constant.JieXiStart2, "");
        } else if (parseContent.contains(Constant.JieXiStart4)) {
            parseContent = parseContent.replace(Constant.JieXiStart4, "");
        } else if (parseContent.contains(Constant.JieXiStart3)) {
            parseContent = parseContent.substring(2, parseContent.length());
        }
        this.parseContent = parseContent;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }
}
