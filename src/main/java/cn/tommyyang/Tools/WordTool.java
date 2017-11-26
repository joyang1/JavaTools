package cn.tommyyang.Tools;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by TommyYang on 2017/11/26.
 */
public class WordTool {

    public static String[] reaDoc(String docPath) throws IOException {
        FileInputStream in;
        in = new FileInputStream(docPath);
        //HWPFDocument doc = new HWPFDocument(in);
        WordExtractor extractor = new WordExtractor(in);
        //XWPFDocument doc = new XWPFDocument(in);
        String[] strs = extractor.getParagraphText();
        return strs;
    }
}
