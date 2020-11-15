package com.kkhome.excel.listener;

import com.kkhome.excel.choose.FileChoose;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 佐川地址处理
 */
public class ZuoChuanAddressHandler extends AbstractExcelHandler implements ExcelHandler {

    private final static String name = "李可可-%tm%td-出";

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
                to = from.substring(0, to.lastIndexOf("RB")) + String.format(name, date, date) + ".xlsx";
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
            String[] rows = stringList.get(i).split(csvSplitBy);
            List<String> rowList = new ArrayList<>();
            String splicing = "";
            if (rows[0].equals("376890-20200704-00093823")) {
                System.out.println(stringList.get(i));
            }
            for (int i1 = 0; i1 < rows.length; i1++) {
                if (i1 <= 11 && i1 >= 2) {
                    continue;
                }

                // 送付先名字-送付先名前
                if (i1 == 12) {
                    if (i == 0) {
                        splicing = "送付先名";
                    } else {
                        splicing = "" + rows[12] + rows[13];
                    }
                    i1++;
                    rowList.add(splicing);
                    continue;
                }

                // 送付先郵便番号１-送付先郵便番号２
                if (i1 == 14) {
                    if (i == 0) {
                        splicing = "送付先郵便番号";
                    } else {
                        splicing = "" + rows[14] + "-" + rows[15];
                    }
                    i1++;
                    rowList.add(splicing);
                    continue;
                }

                // 送付先住所
                if (i1 == 16) {
                    if (i == 0) {
                        splicing = "送付先住所";
                    } else {
                        splicing = "" + rows[16] + rows[17] + rows[18];
                    }
                    i1++;
                    i1++;
                    rowList.add(splicing);
                    continue;
                }

                // 送付先電話番号１-送付先電話番号２-送付先電話番号３
                if (i1 == 19) {
                    if (i == 0) {
                        splicing = "送付先電話番号";
                    } else {
                        splicing = "" + rows[19] + "-" + rows[20] + "-" + rows[21];
                    }
                    rowList.add(splicing);
                    //continue;
                    break;
                }
                rowList.add(rows[i1]);
            }
            result.add(rowList);
        }
    }
}
