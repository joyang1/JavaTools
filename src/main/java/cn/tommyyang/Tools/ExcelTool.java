package cn.tommyyang.Tools;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by TommyYang on 2017/11/19.
 */
public class ExcelTool {

    /**
     * 导出 excel
     *
     * @param filePath   文件全路径
     * @param sheetName  sheet页名称
     * @param sheetIndex 当前sheet下表  从0开始
     * @param fileHeader 头部
     * @param datas      内容
     */
    public static void writeExcel(String filePath, String sheetName,
                                  int sheetIndex,
                                  String[] fileHeader,
                                  List<String[]> datas) {
        // 创建工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建工作表 sheet
        Sheet s = wb.createSheet();

        wb.setSheetName(sheetIndex, sheetName);

        Row r = s.createRow(0);
        Cell c = null;
        Font font = null;
        CellStyle styleHeader = null;
        CellStyle styleContent = null;


        //粗体
        font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置头样式
        styleHeader = wb.createCellStyle();
        styleHeader.setFont(font);
        styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        // 设置内容样式
        styleContent = wb.createCellStyle();
        styleContent.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        styleContent.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        styleContent.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        styleContent.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


        //设置头
        for (int i = 0; i < fileHeader.length;i++) {
            c = r.createCell(i);
            c.setCellStyle(styleHeader);
            c.setCellValue(fileHeader[i]);
        }

        //设置内容
        for (int rownum = 0; rownum < datas.size(); ) { // 行 row   datas.size()
            r = s.createRow(rownum + 1); //创建行
            for (int cellnum = 0; cellnum < fileHeader.length; ) {
                c = r.createCell(cellnum);

                c.setCellValue(datas.get(rownum)[cellnum]);
                c.setCellStyle(styleContent);
                cellnum++;
            }

            rownum++;
        }
        FileOutputStream out = null;
        try {
            // 创建文件或者文件夹,将内容写进去
            out = new FileOutputStream(filePath);
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
