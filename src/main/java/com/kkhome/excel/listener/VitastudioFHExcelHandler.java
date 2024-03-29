package com.kkhome.excel.listener;

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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kkhome.excel.constant.Constant.DEFAULT_OPEN_URL;

/**
 * Vitastudio发货单处理
 */
public class VitastudioFHExcelHandler extends AbstractExcelHandler implements ExcelHandler {


    @Override
    public void handlerExcel(List<List<String>> result, List<String> stringList) {

        List<Data> list = new ArrayList<>();
        for (int i = 1; i < stringList.size(); i++) {
            String str = stringList.get(i).replace("\"", "");
            String[] rows = str.split(",");
            Data data = new Data();
            // B 2
            data.setB("7");
            // E 5
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            data.setE(LocalDateTime.now(ZoneOffset.of("+8")).format(df));
            // F 6
            data.setF(rows[0]);
            // G 7
            data.setG(rows[1]);
            // I 9
            data.setI(rows[2] + "-" + rows[3] + "-" + rows[4]);
            // K 11
            data.setK(rows[5] + "-" + rows[6]);
            // L 12
            data.setL(rows[7] + rows[8] + rows[9]);
            // P 16
            data.setP(rows[10] + rows[11]);
            // T 16
            data.setT("024-572-3218");

            // V 16
            data.setV("1400002");
            // W 16
            data.setW("東京都品川区東品川３－５－１５－２０６号");
            // Y 16
            data.setY("vitastudio");
            // AB 16
            // N列如果不存在，则取Q列的数据，同时将カラー替换掉
            String sku = rows[13];
            if (StringUtils.isEmpty(sku)) {
                sku = rows[16];
            }
            if (StringUtils.isNotEmpty(sku)) {
                sku = sku.replace("カラー:", "/").replace("サイズ:", "/");
            }
            data.setAB("【" + rows[12] + "】" + sku + "/" + rows[14] + "個");
            // AG 16
            data.setAG(rows[17]);
            list.add(data);
        }

        Map<String, List<Data>> map = list.stream().collect(Collectors.groupingBy(Data::getAG));
        map.forEach((k, v) -> {
            Data data = v.get(0);
            List<String> rowList = new ArrayList<>();

            // A 1
            rowList.add("");
            // B 2
            rowList.add("7");
            // C 3
            rowList.add("");
            // D 4
            rowList.add("");
            // E 5
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            rowList.add(LocalDateTime.now(ZoneOffset.of("+8")).format(df));
            // F 6
            rowList.add(data.getF());
            // G 7
            rowList.add(data.getG());
            // H 8
            rowList.add("");
            // I 9
            rowList.add(data.getI());
            // J 10
            rowList.add("");
            // K 11
            rowList.add(data.getK());
            // L 12
            rowList.add(data.getL());
            // M 13
            rowList.add("");
            // N 14
            rowList.add("");
            // O 15
            rowList.add("");
            // P 16
            rowList.add(data.getP());
            // Q 17
            rowList.add("");
            // R 16
            rowList.add("");
            // S 16
            rowList.add("");
            // T 16
            rowList.add(data.getT());
            // U 16
            rowList.add("");
            // V 16
            rowList.add(data.getV());
            // W 16
            rowList.add("東京都品川区東品川３－５－１５－２０６号");
            // X 16
            rowList.add("");
            // Y 16
            rowList.add("vitastudio");
            // Z 16
            rowList.add("");
            // AA 16
            rowList.add("");
            // AB 16

            StringBuilder ab = new StringBuilder();
            for (Data data1 : v) {
                if (ab.length() != 0) {
                    ab.append("+");
                }
                ab.append(data1.getAB());
            }
            rowList.add(ab.toString());

            // AC 16
            rowList.add("");
            // AD 16
            rowList.add("");
            // AE 16
            rowList.add("");
            // AF 16
            rowList.add("");
            // AG 16
            rowList.add(data.getAG());
            result.add(rowList);
        });

        inserSheetData(result, "D:\\template\\vitastudio20230703.xls");

    }

    @Override
    public void doHandler() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File(DEFAULT_OPEN_URL));
        int returnVal = fileChooser.showOpenDialog(fileChooser);
        String from = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //这个就是你选择的文件夹的路径
            from = fileChooser.getSelectedFile().getAbsolutePath();
        }
        if (StringUtils.isEmpty(from)) {
            JOptionPane.showMessageDialog(null, "地址不能为空" + from, "输入有错", JOptionPane.PLAIN_MESSAGE);
        } else {
            File file = new File(from);
            if (file.isFile() && from.endsWith("csv")) {
                String to = from.replace("csv", "xlsx");
                readCsvToExcel(from, to);
                JOptionPane.showMessageDialog(null, "成功！！！文件在D盘目录查看", "转换成功", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "输入文件不存在，地址：" + from, "输入有错", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public void readCsvToExcel(String from, String to) {
        List<List<String>> result = new ArrayList<>();
        List<String> stringList = CsvUtils.readCsv(from);
        handlerExcel(result, stringList);
    }


    /**
     * 向已知表中插入数据，累计追加
     * 写入前先判断表是否存在，表中是否有数据
     *
     * @param dateList list实体类对象
     * @param filePath excel的路径 D:\\123.xls
     * @throws Exception
     */
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

    public static String fileCopy(String path) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String newF = "D:\\vitastudio" + LocalDateTime.now(ZoneOffset.of("+8")).format(df) + ".xls";
        String newF = "C:\\Users\\Admin\\Desktop\\vita日本仓发货\\vita" + LocalDateTime.now(ZoneOffset.of("+8")).format(df) + ".xls";
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

    @lombok.Data
    public static class Data {
        private String B;
        private String E;
        private String F;
        private String G;
        private String I;
        private String K;
        private String L;
        private String P;
        private String T;
        private String V;
        private String W;
        private String Y;
        private String AB;
        private String AG;
    }

}
