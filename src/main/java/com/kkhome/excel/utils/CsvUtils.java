package com.kkhome.excel.utils;

import com.csvreader.CsvReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
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

    @SneakyThrows
    public static List<List<String>> readCsv2(String filepath, boolean needHeader) {
        // 第一参数：读取文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集
        CsvReader csvReader = new CsvReader(filepath, ',', Charset.forName(charsetName));
        // 如果你的文件没有表头，这行不用执行
        // 这行不要是为了从表头的下一行读，也就是过滤表头
        if (!needHeader) {
            csvReader.readHeaders();
        }
        // 读取每行的内容
        ArrayList<List<String>> allString = new ArrayList<>();
        while (csvReader.readRecord()) {
            // 获取内容的两种方式
            // 1. 通过下标获取
            System.out.print(csvReader.get(0));
            // 2. 通过表头的文字获取
            System.out.println(" " + csvReader.get("年龄"));
            List<String> cols = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                cols.add(csvReader.get(i));
            }
            allString.add(cols);
        }
        return allString;
    }
}
