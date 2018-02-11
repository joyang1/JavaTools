package cn.tommyyang.tools.filetool.Tools;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by TommyYang on 2017/11/26.
 */
public class WordTool {

    public static String reaDoc(String docPath) throws IOException {
        FileInputStream in;
        in = new FileInputStream(docPath);
        //HWPFDocument doc = new HWPFDocument(in);
        WordExtractor extractor = new WordExtractor(in);
        //XWPFDocument doc = new XWPFDocument(in);
        String[] strs = extractor.getParagraphText();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str: strs) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static String reaDocx(String docPath) throws IOException {
        FileInputStream in;
        in = new FileInputStream(docPath);
        XWPFDocument xdoc = new XWPFDocument(in);
        XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
        String text = extractor.getText();
        return text;
    }
}
