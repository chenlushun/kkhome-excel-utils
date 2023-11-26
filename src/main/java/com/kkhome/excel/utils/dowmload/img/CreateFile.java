package com.kkhome.excel.utils.dowmload.img;

import com.kkhome.excel.utils.PathUtils;

import java.io.File;
import java.io.IOException;

public class CreateFile {

    private static String ids =
            "bblw30\n" +
                    "bblw31\n" +
                    "bblw32\n" +
                    "bblw33\n" +
                    "bbsk34\n" +
                    "bbsk35\n" +
                    "bbsk36\n" +
                    "bbsk37\n";

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
