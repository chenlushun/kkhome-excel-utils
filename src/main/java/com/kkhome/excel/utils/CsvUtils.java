package com.kkhome.excel.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CsvUtils {
    private static final String charsetName = "Shift_JIS";

    // 读取csv文件的内容
    public static List<String> readCsv(String filepath) {
        File csv = new File(filepath); // CSV文件路径
        csv.setReadable(true);//设置可读
        csv.setWritable(true);//设置可写
        BufferedReader br = null;
        try {
            InputStream inputStream = new FileInputStream(filepath);
            br = new BufferedReader(new InputStreamReader(inputStream, charsetName));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line = "";
        ArrayList<String> allString = new ArrayList<>();
        try {
            // 读取到的内容给line变量
            StringBuilder temp = new StringBuilder();
            while ((line = br.readLine()) != null) {
//                allString.add(line);
                if (line.endsWith("\"")) {
                    temp.append(line);
                    if (temp.length() == 0) {
                        System.out.println(line);
                        allString.add(line);
                    } else {
                        //temp.append(line);
                        temp.append("\"");
                        System.out.println(temp.toString());
                        allString.add(temp.toString());
                    }
                    temp = new StringBuilder();
                } else {
                    // 处理内部换行
                    temp.append(line);
                }
            }
            System.out.println("csv表格中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allString;
    }
}
