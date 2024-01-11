package com.kkhome.excel.utils.dowmload.img;

import com.kkhome.excel.utils.PathUtils;

import java.io.File;
import java.io.IOException;

public class CreateFile {

    private static String ids =
            "sf79\n" +
                    "sf80\n" +
                    "sf81\n" +
                    "sf82\n" +
                    "sf83\n" +
                    "sf84\n" +
                    "sf85\n" +
                    "sf86\n" +
                    "sf87\n" +
                    "sf88\n" +
                    "sf89\n" +
                    "sf90\n" +
                    "sf91\n" +
                    "sf92\n" +
                    "sf93\n" +
                    "sf94\n" +
                    "sf95\n" +
                    "sf96\n" +
                    "sf97\n" +
                    "sf98\n" +
                    "sf99\n" +
                    "sf100\n" +
                    "sf101\n" +
                    "sf102\n" +
                    "sf103\n" +
                    "sf104\n" +
                    "sf105\n" +
                    "sf106\n" +
                    "sf107\n" +
                    "sf108\n" +
                    "sf109\n" +
                    "sf110\n" +
                    "sf111\n" +
                    "sf112\n" +
                    "sf113\n" +
                    "sf114\n" +
                    "sf115\n" +
                    "sf116\n" +
                    "sf117\n" +
                    "sf118\n" +
                    "sf119\n" +
                    "sf120\n";

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
