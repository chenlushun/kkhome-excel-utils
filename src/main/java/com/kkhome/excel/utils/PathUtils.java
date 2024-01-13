package com.kkhome.excel.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 临时文件写入，读取
 */
@Slf4j
public class PathUtils {

    public static String getPathPrex() {
        return "C:\\Users\\Admin\\Desktop\\";
    }

    public static String read() {
        File file = getFile();
        InputStream inputStream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StringBuilder buffer = new StringBuilder();
        try {
            if (file.isFile() && file.exists()) { //判断文件是否存在
                // 一次读多个字节
                byte[] tempbytes = new byte[1024];
                int byteread;
                inputStream = new FileInputStream(file);
                while ((byteread = inputStream.read(tempbytes)) != -1) {
                    String str = new String(tempbytes, 0, byteread);
                    buffer.append(str);
                }
            } else {
                log.error(file.getPath());
                //file.createNewFile();
            }
            return buffer.toString();
        } catch (IOException e) {
            log.error("", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                bos.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
        return null;
    }


    public static boolean write(String text) {
        PrintStream stream;
        try {
            //写入的文件path
            stream = new PrintStream(getFile());
            //写入的字符串
            stream.print(text);
            return true;
        } catch (FileNotFoundException e) {
            log.error("", e);
        }
        return false;
    }

    public static File getFile() {
        log.error(System.getProperty("file.path"));
        return new File(System.getProperty("file.path"));
    }


}
