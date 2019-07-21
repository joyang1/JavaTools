package cn.tommyyang.tools.filetool.Tools;

import cn.tommyyang.tools.filetool.Constant.Constant;
import cn.tommyyang.tools.filetool.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filePath));
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
                    if (line.length() >= 3) {
                        timuStartFlag = qStart.contains(line.substring(0, 3)) || qStart.contains(line.substring(0, 2)) || qStart.contains(line.substring(0, 1));
                    } else if (line.length() >= 2) {
                        timuStartFlag = qStart.contains(line.substring(0, 2)) || qStart.contains(line.substring(0, 1));
                    } else if (line.length() >= 1) {
                        timuStartFlag = qStart.contains(line.substring(0, 1));
                    }
                    if (timuStartFlag) {
                        isTimuNow = true;
                    }

                    Boolean isJieXiStart = line.contains(Constant.JieXiStart1) || line.contains(Constant.JieXiStart2) || line.startsWith(Constant.JieXiStart3) || line.startsWith(Constant.JieXiStart4);
                    if (isJieXiStart) {
                        isJiexiNow = true;
                    }

                    Boolean aflag = line.startsWith(aStart);
                    if (aflag) {
                        isTimuNow = false;
                    }

                    if (line.startsWith(aStart) && line.contains(bStart) && line.contains(cStart) && line.contains(dStart) && !isJiexiNow && !isTimuNow) {
                        aContent = line.substring(line.indexOf(aStart), line.indexOf(bStart));
                        bContent = line.substring(line.indexOf(bStart), line.indexOf(cStart));
                        cContent = line.substring(line.indexOf(cStart), line.indexOf(dStart));
                        dContent = line.substring(line.indexOf(dStart), line.length());
                        continue;
                    } else if (line.startsWith(aStart) && line.contains(bStart) && !isJiexiNow && !isTimuNow) {
                        aContent = line.substring(line.indexOf(aStart), line.indexOf(bStart));
                        bContent = line.substring(line.indexOf(bStart), line.length());
                        continue;
                    } else if (line.startsWith(bStart) && line.contains(cStart) && line.contains(dStart) && !isJiexiNow && !isTimuNow) {
                        bContent = line.substring(line.indexOf(bStart), line.indexOf(cStart));
                        cContent = line.substring(line.indexOf(cStart), line.indexOf(dStart));
                        dContent = line.substring(line.indexOf(dStart), line.length());
                        continue;
                    } else if (line.startsWith(cStart) && line.contains(dStart) && !isJiexiNow && !isTimuNow) {
                        cContent = line.substring(line.indexOf(cStart), line.indexOf(dStart));
                        dContent = line.substring(line.indexOf(dStart), line.length());
                        continue;
                    } else if (aflag && !isJiexiNow && !isTimuNow) {
                        aContent = line;
                        continue;
                    } else if (line.startsWith(bStart) && !isJiexiNow && !isTimuNow) {
                        bContent = line;
                        continue;
                    } else if (line.startsWith(cStart) && !isJiexiNow && !isTimuNow) {
                        cContent = line;
                        continue;
                    } else if (line.startsWith(dStart) && !isJiexiNow && !isTimuNow) {
                        dContent = line;
                        continue;
                    }

                    if ((line.contains(Constant.DanAnStart1) || line.startsWith(Constant.DanAnStart2) || line.contains(Constant.DanAnStart3) || line.startsWith(Constant.DanAnStart4) || line.startsWith(Constant.DanAnStart5) || line.startsWith(Constant.DanAnStart6) || line.startsWith(Constant.DanAnStart7) ||
                            line.contains(Constant.DanAnStart8)) && !isJiexiNow) {
                        dananContent = line;
                        continue;
                    }

                    if (timuStartFlag && !jiexiContent.equals("")) {
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
                } catch (Exception e) {
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
            if (!jiexiContent.equals("")) {
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
            if (questions.size() > 0) {
                return questions;
            }
            System.out.println(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void writeContentToTxt(String content, String path) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path, true);
            byte[] contentBytes = content.getBytes();
            fileOutputStream.write(contentBytes);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String generateJiexi(String filePath) {
        //List<String> jiexis = Utils.getJiexiStartFlag();
        Set<String> qStart = Utils.getStartFlags();
        List<String> jiexiOptionFlags = Utils.getJiexiOptionFlags();
        String aFlag = jiexiOptionFlags.get(0);
        String bFlag = jiexiOptionFlags.get(1);
        String cFlag = jiexiOptionFlags.get(2);
        String dFlag = jiexiOptionFlags.get(3);
        FileInputStream inputStream = null;
        StringBuilder sBuilder = new StringBuilder();
        String line = "";
        try {
            inputStream = new FileInputStream(filePath);
            UnicodeReader unicodeReader = new UnicodeReader(inputStream, "UTF-8");
            BufferedReader bfReader = new BufferedReader(unicodeReader);
            while (bfReader.ready()) {
                line = Utils.formatLine(bfReader.readLine().trim());
                String jieXi = Constant.JieXi;
                String danAn = Constant.DanAn;
                Boolean tiHaoStartFlag = false;
                if (line.length() > 3) {
                    tiHaoStartFlag = qStart.contains(line.substring(0, 1)) || qStart.contains(line.substring(0, 2)) || qStart.contains(line.substring(0, 3));
                }
                int index = 0;
                Boolean abcdflag = line.contains(aFlag) || line.contains(bFlag) || line.contains(cFlag) || line.contains(dFlag);
                if (line.contains(jieXi)) {
                    if (line.contains(jieXi + Constant.COMMA)) {
                        line = line.replace(jieXi, Constant.DanAnStart1);
                        index = line.indexOf(Constant.COMMA);
                    } else if (line.contains(Constant.AJieXi) || line.contains(Constant.AJieXi1)) {
                        line = line.contains(Constant.AJieXi) ?
                                line.replace(Constant.AJieXi, Constant.DanAnStart1 + "A" + Constant.COMMA)
                                : line.replace(Constant.AJieXi1, Constant.DanAnStart1 + "A" + Constant.COMMA);
                        index = line.indexOf(Constant.COMMA);
                    } else if (line.contains(Constant.BJieXi) || line.contains(Constant.BJieXi1)) {
                        line = line.contains(Constant.BJieXi) ?
                                line.replace(Constant.BJieXi, Constant.DanAnStart1 + "B" + Constant.COMMA)
                                : line.replace(Constant.BJieXi1, Constant.DanAnStart1 + "B" + Constant.COMMA);
                        index = line.indexOf(Constant.COMMA);
                    } else if (line.contains(Constant.CJieXi) || line.contains(Constant.CJieXi1)) {
                        line = line.contains(Constant.CJieXi) ?
                                line.replace(Constant.CJieXi, Constant.DanAnStart1 + "C" + Constant.COMMA)
                                : line.replace(Constant.CJieXi1, Constant.DanAnStart1 + "C" + Constant.COMMA);
                        index = line.indexOf(Constant.COMMA);
                    } else if (line.contains(Constant.DJieXi) || line.contains(Constant.DJieXi1)) {
                        line = line.contains(Constant.DJieXi) ?
                                line.replace(Constant.DJieXi, Constant.DanAnStart1 + "D" + Constant.COMMA)
                                : line.replace(Constant.DJieXi1, Constant.DanAnStart1 + "D" + Constant.COMMA);
                        index = line.indexOf(Constant.COMMA);
                    }

                } else if (line.contains(danAn)) {
                    line = line.replace(danAn, Constant.DanAnStart1);
                    index = line.length();
                } else if (line.contains(aFlag)) {
                    line = line.replace(aFlag, Constant.DanAnStart1 + "A");
                    index = line.contains(Constant.JieXiStart1) ? line.indexOf(Constant.JieXiStart1) : line.indexOf(Constant.JieXiStart2);
                } else if (line.contains(bFlag)) {
                    line = line.replace(bFlag, Constant.DanAnStart1 + "B");
                    index = line.contains(Constant.JieXiStart1) ? line.indexOf(Constant.JieXiStart1) : line.indexOf(Constant.JieXiStart2);
                } else if (line.contains(cFlag)) {
                    line = line.replace(cFlag, Constant.DanAnStart1 + "C");
                    index = line.contains(Constant.JieXiStart1) ? line.indexOf(Constant.JieXiStart1) : line.indexOf(Constant.JieXiStart2);
                } else if (line.contains(dFlag)) {
                    line = line.replace(dFlag, Constant.DanAnStart1 + "D");
                    index = line.contains(Constant.JieXiStart1) ? line.indexOf(Constant.JieXiStart1) : line.indexOf(Constant.JieXiStart2);
                }
                if (tiHaoStartFlag && abcdflag) {
                    sBuilder.append(line.substring(0, index)).append(Constant.LineFlag).append(line.substring(index, line.length())).append(Constant.LineFlag);
                } else if (tiHaoStartFlag && !line.contains(Constant.JieXiStart1) && !line.contains(Constant.JieXiStart2) && line.length() > index) {
                    sBuilder.append(line.substring(0, index)).append(Constant.LineFlag).append("解析:").append(line.substring(index + 1, line.length())).append(Constant.LineFlag);
                } else if (tiHaoStartFlag && line.contains(Constant.DanAnStart1) &&
                        (line.contains(Constant.JieXiStart1) || line.contains(Constant.JieXiStart2))) {
                    int pos = line.contains(Constant.JieXiStart1) ? line.indexOf(Constant.JieXiStart1) : line.indexOf(Constant.JieXiStart2);
                    sBuilder.append(line.substring(0, pos)).append(Constant.LineFlag).append(line.substring(pos, line.length())).append(Constant.LineFlag);
                } else {
                    sBuilder.append(line).append(Constant.LineFlag);
                }
            }
            return sBuilder.toString();
        } catch (Exception e) {
            System.out.println(line);
            e.printStackTrace();
        }
        return null;
    }


    public static void combineTxt(String txtContentPath, String txtJiexiPath) {
        FileInputStream contentInputStream = null;
        FileInputStream jiexiInputStream = null;
        List<String> optionStart = Utils.getOptionFlags();
        Set<String> qStart = Utils.getStartFlags();
        String aStart = optionStart.get(0);
        String bStart = optionStart.get(1);
        String cStart = optionStart.get(2);
        String dStart = optionStart.get(3);
        StringBuilder sb = new StringBuilder();
        StringBuilder sbJiexi = new StringBuilder();
        try {
            contentInputStream = new FileInputStream(txtContentPath);
            jiexiInputStream = new FileInputStream(txtJiexiPath);
            UnicodeReader contentReader = new UnicodeReader(contentInputStream, "UTF-8");
            BufferedReader contentBfReader = new BufferedReader(contentReader);
            Integer tiHao = 0;
            while (contentBfReader.ready()) {
                String conLine = contentBfReader.readLine();
                String contentLine = Utils.formatLine(conLine);
                Boolean timuStartFlag = false;
                if (contentLine.length() > 3) {
                    timuStartFlag = qStart.contains(contentLine.substring(0, 3)) || qStart.contains(contentLine.substring(0, 2)) || qStart.contains(contentLine.substring(0, 1));
                }
                if (timuStartFlag) {
                    if (qStart.contains(contentLine.substring(0, 3))) {
                        String tiHaoStr = contentLine.substring(0, 3);
                        if (tiHaoStr.contains(Constant.PAUSE) || tiHaoStr.contains(Constant.POINT_ZH) || tiHaoStr.contains(Constant.POINT)) {
                            tiHao = Integer.parseInt(tiHaoStr.substring(0, 2));
                        } else {
                            tiHao = Integer.parseInt(tiHaoStr);
                        }
                    } else if (qStart.contains(contentLine.substring(0, 2))) {
                        String tiHaoStr = contentLine.substring(0, 2);
                        if (tiHaoStr.contains(Constant.PAUSE) || tiHaoStr.contains(Constant.POINT_ZH) || tiHaoStr.contains(Constant.POINT)) {
                            tiHao = Integer.parseInt(tiHaoStr.substring(0, 1));
                        } else {
                            tiHao = Integer.parseInt(tiHaoStr);
                        }
                    } else if (qStart.contains(contentLine.substring(0, 1))) {
                        String tiHaoStr = contentLine.substring(0, 1);
                        tiHao = Integer.parseInt(tiHaoStr);
                    }
                }
                if ((contentLine.startsWith(aStart) && contentLine.contains(dStart)) ||
                        (contentLine.startsWith(bStart) && contentLine.contains(dStart)) ||
                        (contentLine.startsWith(cStart) && contentLine.contains(dStart)) ||
                        contentLine.startsWith(dStart)) {
                    Integer jiexiTihao = 0;
                    jiexiInputStream = new FileInputStream(txtJiexiPath);
                    UnicodeReader jiexiReader = new UnicodeReader(jiexiInputStream, "UTF-8");
                    BufferedReader jiexiBfReader = new BufferedReader(jiexiReader);
                    while (jiexiBfReader.ready()) {
                        String jiexiLine = jiexiBfReader.readLine();
                        Boolean jiexiStartFlag = false;
                        if (jiexiLine.length() > 3) {
                            jiexiStartFlag = qStart.contains(jiexiLine.substring(0, 3)) || qStart.contains(jiexiLine.substring(0, 2)) || qStart.contains(jiexiLine.substring(0, 1));
                        }
                        if (sbJiexi.length() > 0 && jiexiStartFlag) {
                            break;
                        }
                        if (jiexiStartFlag && tiHao >= 100 && jiexiLine.substring(0, 3).equals(tiHao + "")) {
                            jiexiTihao = Integer.parseInt(jiexiLine.substring(0, 3));
                            String addLine = "";
                            if (jiexiLine.startsWith(jiexiTihao + Constant.POINT)) {
                                addLine = jiexiLine.replace(jiexiTihao + Constant.POINT, "");
                            } else if (jiexiLine.startsWith(jiexiTihao + Constant.POINT_ZH)) {
                                addLine = jiexiLine.replace(jiexiTihao + Constant.POINT_ZH, "");
                            }
                            sbJiexi.append(addLine).append(Constant.LineFlag);
                        } else if (jiexiStartFlag && tiHao >= 10 && jiexiLine.substring(0, 2).equals(tiHao + "")) {
                            jiexiTihao = Integer.parseInt(jiexiLine.substring(0, 2));
                            String addLine = "";
                            if (jiexiLine.startsWith(jiexiTihao + Constant.POINT)) {
                                addLine = jiexiLine.replace(jiexiTihao + Constant.POINT, "");
                            } else if (jiexiLine.startsWith(jiexiTihao + Constant.POINT_ZH)) {
                                addLine = jiexiLine.replace(jiexiTihao + Constant.POINT_ZH, "");
                            }
                            sbJiexi.append(addLine).append(Constant.LineFlag);
                        } else if (jiexiStartFlag && tiHao >= 1 && jiexiLine.substring(0, 1).equals(tiHao + "")) {
                            jiexiTihao = Integer.parseInt(jiexiLine.substring(0, 1));
                            String addLine = "";
                            if (jiexiLine.startsWith(jiexiTihao + Constant.POINT)) {
                                addLine = jiexiLine.replace(jiexiTihao + Constant.POINT, "");
                            } else if (jiexiLine.startsWith(jiexiTihao + Constant.POINT_ZH)) {
                                addLine = jiexiLine.replace(jiexiTihao + Constant.POINT_ZH, "");
                            }
                            sbJiexi.append(addLine).append(Constant.LineFlag);
                        }
                        if (!jiexiStartFlag && jiexiTihao == tiHao) {
                            sbJiexi.append(jiexiLine).append(Constant.LineFlag);
                        }
                    }
                    sb.append(conLine).append(Constant.LineFlag);
                    if (sbJiexi.length() > 0) {
                        sb.append(sbJiexi).append(Constant.LineFlag);
                        sbJiexi = new StringBuilder();
                        tiHao = 0;
                    }
                } else {
                    sb.append(conLine).append(Constant.LineFlag);
                }
            }
            if (sb.length() > 0) {
                File file = new File(txtContentPath);
                if (file.exists()) {
                    String newTxtName = file.getName().replace(".txt", "") + "_new.txt";
                    writeContentToTxt(sb.toString(), "E:\\runtest1\\" + newTxtName);
                }
            }
        } catch (Exception e) {
            if (sb.length() > 0) {
                File file = new File(txtContentPath);
                if (file.exists()) {
                    String newTxtName = file.getName().replace(".txt", "") + "_new.txt";
                    writeContentToTxt(sb.toString(), "E:\\runtest1\\" + newTxtName);
                }
            }
        } finally {
            try {
                if (contentInputStream != null) {
                    contentInputStream.close();
                }
                if (jiexiInputStream != null) {
                    jiexiInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
