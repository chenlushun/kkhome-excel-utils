package com.kkhome.excel.listener.sx;

import com.kkhome.excel.choose.FileChoose;
import com.kkhome.excel.listener.AbstractExcelHandler;
import com.kkhome.excel.listener.ExcelHandler;
import com.kkhome.excel.utils.CsvUtils;
import com.kkhome.excel.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
public class ItemSkuMerge extends AbstractExcelHandler implements ExcelHandler {


    @Override
    public void handlerExcel(List<List<String>> result, List<String> stringList) {
        for (String s : stringList) {

        }
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
                to = from.substring(0, from.lastIndexOf("\\")) + "\\sku合并" + simpleDateFormat.format(date) + ".xlsx";
                readCsvToExcel(from, to);
                JOptionPane.showMessageDialog(null, "成功！！！,文件已经放置在：" + to, "转换成功", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "输入文件不存在，地址：" + from, "输入有错", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public void readCsvToExcel(String from, String to) {
        List<List<String>> result = new ArrayList<>();
        List<List<String>> list = CsvUtils.readCsv2(from, true);
        List<String> subList = list.get(0);
        int colorsIndex = 94;
        int sizeIndex = 95;
        for (int i = 0; i < subList.size(); i++) {
            String title = subList.get(i);
            if (title.equals("バリエーション1選択肢定義")) {
                colorsIndex = i;
            }
            if (title.equals("バリエーション2選択肢定義")) {
                sizeIndex = i;
            }
        }
        for (int i = 1; i < list.size(); i++) {
            List<String> strings = list.get(i);
            String colors = strings.get(colorsIndex);
            String code = strings.get(0);
            for (String s : colors.split("\\|")) {
                String sizes = strings.get(sizeIndex);
                for (String s1 : sizes.split("\\|")) {
                    List<String> sub = new ArrayList<>();
                    sub.add(code);
                    sub.add("カラー");
                    sub.add(s);
                    sub.add("サイズ");
                    sub.add(s1);
                    result.add(sub);
                }
            }
        }
        ExcelUtils.writeExcel(result, to);
    }
}
