package work.douzi.report.util;

import org.apache.poi.hssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Ryan
 * @date 2021/6/1
 */
public class ExportExcel {

    public static void exportExcel(String title, String[] headers, List mapList, OutputStream out, String pattern) {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽度为15个字符
        sheet.setDefaultColumnWidth(20);
        //生成一个样式，用来设置标题样式
        HSSFCellStyle style = workbook.createCellStyle();
        //生成一个字体
        HSSFFont font = workbook.createFont();
        //把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式,用于设置内容样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        // 把字体应用到当前的样式
        style2.setFont(font2);
        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for(int i = 0; i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (int i=0;i<mapList.size();i++) {
            Map<String,Object> map = (Map<String, Object>) mapList.get(i);
            row = sheet.createRow(i+1);
            int j = 0;
            Object value = null;
            value=map.get("report_id");
            if(value instanceof Long){
                row.createCell(j++).setCellValue(String.valueOf(value));
            }
            value=map.get("report_name");
            if(value instanceof String){
                row.createCell(j++).setCellValue(String.valueOf(value));
            }
            value=map.get("achievement");
            if(value instanceof String){
                row.createCell(j++).setCellValue(String.valueOf(value));
            }
            value=map.get("problem");
            if(value instanceof String){
                row.createCell(j++).setCellValue(String.valueOf(value));
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
