package com.kkhome.excel.listener;

import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合并的excel中的相同列
 */
public class MergeAndDistinctExcelHandler extends AbstractExcelHandler implements ExcelHandler {

    @Override
    public void doHandler() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        //fileChooser.setCurrentDirectory(new File("/Users/kinoko/Desktop/kkhome/合并excel"));
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
                JOptionPane.showMessageDialog(null, "成功！！！,文件已经放置在：" + to, "转换成功", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "输入文件不存在，地址：" + from, "输入有错", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    @Override
    public void handlerExcel(List<List<String>> result, List<String> stringList) {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i < stringList.size(); i++) {
            String s = stringList.get(i);
            String[] rows = s.split(csvSplitBy);
            String orderId = rows[0];
            String value = map.get(orderId);
            if (null == value) {
                map.put(rows[0], rows[1] + rows[2]);
            } else {
                map.put(rows[0], value + "+" + rows[1] + rows[2]);
            }
        }
        List<String> head = new ArrayList<>();
        head.add("注文番号");
        head.add("merge");
        result.add(head);

        map.forEach((k, v) -> {
            List<String> rows = new ArrayList<>();
            rows.add(k);
            rows.add(v);
            result.add(rows);
        });
    }
}