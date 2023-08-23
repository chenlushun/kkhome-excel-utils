package com.kkhome.excel.listener;

import com.kkhome.excel.choose.FileChoose;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZIFUTranslate extends AbstractExcelHandler implements ExcelHandler {

    private final static String name = "转换文件-%tm%td";

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
                to = from.substring(0, from.lastIndexOf("\\")) + "\\" + String.format(name, date, date) + ".xlsx";
                readCsvToExcel(from, to);
                JOptionPane.showMessageDialog(null, "成功！！！,文件已经放置在：" + to, "转换成功", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "输入文件不存在，地址：" + from, "输入有错", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    @Override
    public void handlerExcel(List<List<String>> result, List<String> stringList) {
        for (int i = 0; i < stringList.size(); i++) {
            String[] rows = stringList.get(i).split(",");
            List<String> rowList = new ArrayList<>();

            for (int i1 = 0; i1 < rows.length; i1++) {
                rowList.add(rows[i1]);
            }
            result.add(rowList);
        }
    }
}
