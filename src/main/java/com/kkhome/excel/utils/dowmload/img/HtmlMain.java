package com.kkhome.excel.utils.dowmload.img;

import com.kkhome.excel.utils.PathUtils;

import java.io.File;
import java.io.IOException;

/**
 * 商品详情页html生成
 */
public class HtmlMain {

    public static void main(String[] args) throws IOException {

        File file = new File(PathUtils.getPathPrex() + "1688");
        File[] files = file.listFiles();
        for (File file1 : files) {
            Find.test(file1.getName(), file1.getAbsolutePath());
        }
    }


}
