package com.kkhome.excel.listener;

import com.kkhome.excel.choose.FileChoose;
import com.kkhome.excel.utils.CsvUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kkhome.excel.constant.Constant.*;

public class YnHmHandler extends AbstractExcelHandler implements ExcelHandler {

    private final static String name = "%tm%td-陈录顺";

    private boolean checkBoxSelected;

    public YnHmHandler(boolean checkBoxSelected) {
        this.checkBoxSelected = checkBoxSelected;
    }

    @Override
    public void handlerExcel(List<List<String>> result, List<String> stringList) {
        for (int i = 1; i < stringList.size(); i++) {
            String str = stringList.get(i).replace("\"", "");
            String[] rows = str.split(",");
            List<String> rowList = new ArrayList<>();

            // A  orderNo
            rowList.add(rows[0]);
            // B  运单号
            rowList.add("");
            // C  发件人
            rowList.add("vita");
            // D  发件单位
            rowList.add("");
            // E  发件电话
            rowList.add("024-572-3218");
            // F  发件邮编
            rowList.add("960-0711");
            // G  发件地址
            rowList.add("福島県伊達市梁川町粟野字前93-1");
            // H  发件国家
            rowList.add("");
            // I  收件人
            rowList.add(rows[12] + rows[13]);
            // J  收件单位
            rowList.add("");
            // K  收件电话
            rowList.add("" + rows[19] + "-" + rows[20] + "-" + rows[21]);
            // L  收件邮编
            rowList.add(rows[14] + "-" + rows[15]);
            // M  收件地址
            rowList.add(rows[16] + rows[17] + rows[18]);
            // N  收件国家
            rowList.add("日本");
            result.add(rowList);
        }
        if (checkBoxSelected) {
            List<String> rowList = new ArrayList<>();
            // A  orderNo
            rowList.add("");
            // B  运单号
            rowList.add("");
            // C  发件人
            rowList.add("vita");
            // D  发件单位
            rowList.add("");
            // E  发件电话
            rowList.add("024-572-3218");
            // F  发件邮编
            rowList.add("960-0711");
            // G  发件地址
            rowList.add("福島県伊達市梁川町粟野字前93-1");
            // H  发件国家
            rowList.add("");
            // I  收件人
            rowList.add("vita(品和株式会社)");
            // J  收件单位
            rowList.add("");
            // K  收件电话
            rowList.add("024-572-3218");
            // L  收件邮编
            rowList.add("");
            // M 收件地址
            rowList.add("福島県伊達市梁川町粟野字前93-1");
            // N 收件国家
            rowList.add("日本");
            result.add(rowList);
        }
        inserSheetData(result, YN_HM_EXCEL_ADDRESS);
    }


    public static void inserSheetData(List<List<String>> dateList, String filePath) {
        // String filePath = "D:\\123.xls";
        try {
            filePath = fileCopy(filePath);
            FileInputStream fs = new FileInputStream(filePath);


            //使用POI提供的方法得到excel的信息
//            POIFSFileSystem fileSystem = new POIFSFileSystem(fs);
            XSSFWorkbook hssfWorkbook = new XSSFWorkbook(fs);
            //获取到工作表，因为一个excel可能有多个工作表
            XSSFSheet InsertSheet = hssfWorkbook.getSheetAt(0);
            //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
            XSSFRow row = ((XSSFSheet) InsertSheet).getRow(0);
            //分别得到最后一行的行号，和一条记录的最后一个单元格
            System.out.println("最后一行的行 " + InsertSheet.getLastRowNum());
            //向文件中写入数据
            FileOutputStream out = new FileOutputStream(filePath);

            //指定行
            // int lastRowNum = 20;
            for (List<String> date : dateList) {
                // 获取存在数据最大的行+1
                int lastRowNum = InsertSheet.getLastRowNum() + 1;
                //在指定行后追加数据
                row = InsertSheet.createRow(lastRowNum);
                //设置第一个（从0开始）单元格的数据

                for (int i = 0; i < date.size(); i++) {
                    row.createCell(i).setCellValue(date.get(i));
                }
            }
            out.flush();
            hssfWorkbook.write(out);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void readCsvToExcel(String from, String to) {
        List<List<String>> result = new ArrayList<>();
        List<String> stringList = CsvUtils.readCsv(from);
        handlerExcel(result, stringList);
    }

    @Override
    public void doHandler() {
        FileChoose fileChoose = new FileChoose();
        String from = fileChoose.doSelect();
        if (StringUtils.isEmpty(from)) {
            JOptionPane.showMessageDialog(null, "地址不能为空" + from, "输入有错", JOptionPane.PLAIN_MESSAGE);
        } else {
            File file = new File(from);
            if (file.isFile() && from.endsWith("csv")) {
                String to = from.replace("csv", "xlsx");
                Date date = new Date();
                to = from.substring(0, to.lastIndexOf("RB")) + String.format(name, date, date) + ".xls";
                readCsvToExcel(from, to);
                JOptionPane.showMessageDialog(null, "成功！！！\n,文件已经放置在：" + SHANG_HAI_DELIVERY_EXCEL_ADDRESS_NEW, "转换成功", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "输入文件不存在，地址：" + from, "输入有错", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }


    public static String fileCopy(String path) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String newF = SHANG_HAI_DELIVERY_EXCEL_ADDRESS_NEW + "黑猫小包" + LocalDateTime.now(ZoneOffset.of("+8")).format(df) + "-YN100127.xlsx";

        try (InputStream is = new FileInputStream(path); FileOutputStream os = new FileOutputStream(newF, true)) {  //注意OS的第二参数，是否追加。
            byte[] buffer = new byte[1024 * 1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            System.out.println("拷贝完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newF;
    }
}
