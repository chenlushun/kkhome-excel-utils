package com.kkhome.excel.utils.dowmload.img;

import com.kkhome.excel.utils.PathUtils;

import java.io.File;
import java.io.IOException;

public class CreateFile {

    private static String ids = "tz01ku\n" +
            "tz02ku\n" +
            "tz03ku\n" +
            "tz04ku\n" +
            "tz05ku\n" +
            "tz06ku\n" +
            "tz07ku\n" +
            "tz08ku\n" +
            "tz09ku\n" +
            "tz10ku\n" +
            "tz11ku\n" +
            "tz12ku\n" +
            "tz13ku\n" +
            "tz14ku\n" +
            "tz15ku\n" +
            "tz16ku\n" +
            "tz17ku\n" +
            "tz18ku\n" +
            "tz19ku\n" +
            "tz20ku\n" +
            "tz21ku\n" +
            "tz22ku\n" +
            "tz23ku\n";

    public static void main(String[] args) throws IOException {
        //  List<String> list = CsvUtils.readCsv("C:\\Users\\Administrator\\Desktop\\dl-item202305111642-1.csv");

//        File exFile = new File("C:\\Users\\Administrator\\Desktop\\6月请完成\\美图批处理照片");
        String[] id = ids.split("\n");
        for (String s : id) {

            File nfile = new File(PathUtils.getPathPrex() + "\\1688\\" + s + ".html");
            nfile.createNewFile();
        }


    }
}
