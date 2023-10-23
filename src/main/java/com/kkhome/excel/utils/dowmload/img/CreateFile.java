package com.kkhome.excel.utils.dowmload.img;

import com.kkhome.excel.utils.PathUtils;

import java.io.File;
import java.io.IOException;

public class CreateFile {

    private static String ids = "sf50\n" +
            "sf51\n" +
            "sf52\n" +
            "sf53\n" +
            "sf54\n" +
            "sf55\n" +
            "sf56\n" +
            "sf57\n" +
            "sf58\n" +
            "sf59\n" +
            "sf60\n" +
            "sf61\n" +
            "sf62\n" +
            "sf63\n" +
            "sf64\n" +
            "sf65\n" +
            "sf66\n" +
            "sf67\n" +
            "sf68\n" +
            "sf69\n" +
            "sf70\n" +
            "sf71\n" +
            "sf72\n" +
            "sf73\n" +
            "sf74\n" +
            "sf75\n" +
            "sf76\n" +
            "sf77\n" +
            "sf78\n";

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
