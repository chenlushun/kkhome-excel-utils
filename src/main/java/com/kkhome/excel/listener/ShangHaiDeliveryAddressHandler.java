package com.kkhome.excel.listener;

import com.kkhome.excel.choose.FileChoose;
import com.kkhome.excel.utils.CsvUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kkhome.excel.constant.Constant.SHANG_HAI_DELIVERY_EXCEL_ADDRESS;
import static com.kkhome.excel.constant.Constant.SHANG_HAI_DELIVERY_EXCEL_ADDRESS_NEW;

public class ShangHaiDeliveryAddressHandler extends AbstractExcelHandler implements ExcelHandler {
    private final static String name = "%tm%td-陈录顺";

    @Override
    public void handlerExcel(List<List<String>> result, List<String> stringList) {
        for (int i = 1; i < stringList.size(); i++) {
            String str = stringList.get(i).replace("\"", "");
//            str = str.replace("カラー:", "/");
//            str = str.replace("サイズ:", "/");
//            str = str.replace("配送日時指定:", "");
//            str = str.replace("[]", "");
            String[] rows = str.split(",");
            List<String> rowList = new ArrayList<>();

            // A 1  客户单号
            rowList.add(rows[0]);
            // B 2  转单号
            rowList.add("");
            // C 3  重量
            rowList.add("");
            rowList.add("");
            rowList.add("");
            // D 4  收件人姓名
            rowList.add(rows[12] + rows[13]);
            // E 5  联系地址
            rowList.add(rows[16] + rows[17] + rows[18]);
            // F 6 城市
            rowList.add("");
            // G 7 州,省
            rowList.add("");
            // H 8 收件人电话
            rowList.add("" + rows[19] + "-" + rows[20] + "-" + rows[21]);
            // I 9 收件人邮编
            rowList.add(rows[14] + "-" + rows[15]);
            // J 10 英文品名1
            rowList.add("");
            // K 11 配货信息1（SKU）
            rowList.add("");
            // L 12 申报价值1（美元）
            rowList.add("");
            // M 13 英文品名2
            rowList.add("");
            // N 14 配货信息2（SKU）
            rowList.add("");
            // O 15 申报价值2
            rowList.add("");
            // P 16 英文品名3
            rowList.add("");
            // Q 17 海关报关品名3
            rowList.add("");
            // R 16 申报价值3
            rowList.add("");
            // S 16 申报品数量3
            rowList.add("");
            result.add(rowList);
        }
        inserSheetData(result, SHANG_HAI_DELIVERY_EXCEL_ADDRESS);

    }

//    public void readCsvToExcel(String from, String to) {
//        List<List<String>> result = new ArrayList<>();
//        List<String> stringList = CsvUtils.readCsv(from);
//        handlerExcel(result, stringList);
//        EasyExcel.write(to, ShangHaiDelivery.class)
////                .head(ShangHaiDelivery.class)
//                .autoTrim(true)
//                .needHead(false)
//                .automaticMergeHead(true)
//                .orderByIncludeColumn(true)
//                .excelType(ExcelTypeEnum.XLS)
//                .withTemplate(new File(SHANG_HAI_DELIVERY_EXCEL_ADDRESS))
//                .sheet("Sheet1")
//                .doWrite(() -> {
//                    // 分页查询数据
//                    return getShangHaiDeliveries(stringList);
//                });
//    }


    public static void inserSheetData(List<List<String>> dateList, String filePath) {
        // String filePath = "D:\\123.xls";
        try {
            filePath = fileCopy(filePath);
            FileInputStream fs = new FileInputStream(filePath);


            //使用POI提供的方法得到excel的信息
            POIFSFileSystem fileSystem = new POIFSFileSystem(fs);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileSystem);
            //获取到工作表，因为一个excel可能有多个工作表
            HSSFSheet InsertSheet = hssfWorkbook.getSheetAt(0);
            //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
            HSSFRow row = ((HSSFSheet) InsertSheet).getRow(0);
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

//    private static List<ShangHaiDelivery> getShangHaiDeliveries(List<String> stringList) {
//        List<ShangHaiDelivery> shangHaiDeliveries = new ArrayList<>();
//        for (int i = 1; i < stringList.size(); i++) {
//
//            String s = stringList.get(i);
//            s = s.replaceAll("\"", "");
//
//            String[] rows = s.split(csvSplitBy);
//            String splicing = "";
//            if (rows[0].equals("376890-20200704-00093823")) {
//                System.out.println(stringList.get(i));
//            }
//            ShangHaiDelivery delivery = new ShangHaiDelivery();
//            for (int i1 = 0; i1 < rows.length; i1++) {
//                if (i1 == 0) {
//                    delivery.setCustomNo(rows[0]);
//                }
//                if (i1 <= 11 && i1 >= 2) {
//                    continue;
//                }
//                // 送付先名字-送付先名前
//                if (i1 == 12) {
//                    splicing = "" + rows[12] + rows[13];
//                    i1++;
//                    delivery.setName(splicing);
//                    continue;
//                }
//                // 送付先郵便番号１-送付先郵便番号２
//                if (i1 == 14) {
//                    splicing = "" + rows[14] + "-" + rows[15];
//                    i1++;
//                    delivery.setPost(splicing);
//                    continue;
//                }
//
//                // 送付先住所
//                if (i1 == 16) {
//                    splicing = "" + rows[16] + rows[17] + rows[18];
//                    i1++;
//                    i1++;
//                    delivery.setAddress(splicing);
//                    continue;
//                }
//                // 送付先電話番号１-送付先電話番号２-送付先電話番号３
//                if (i1 == 19) {
//                    splicing = "" + rows[19] + "-" + rows[20] + "-" + rows[21];
//                    delivery.setPhone(splicing);
//                }
//            }
//            shangHaiDeliveries.add(delivery);
//        }
//        return shangHaiDeliveries;
//    }

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
//        String newF = "D:\\vitastudio" + LocalDateTime.now(ZoneOffset.of("+8")).format(df) + ".xls";
        String newF = SHANG_HAI_DELIVERY_EXCEL_ADDRESS_NEW + LocalDateTime.now(ZoneOffset.of("+8")).format(df) + "陈录顺.xls";
        try (InputStream is = new FileInputStream(path);
             OutputStream os = new FileOutputStream(newF, true)) {  //注意OS的第二参数，是否追加。
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
