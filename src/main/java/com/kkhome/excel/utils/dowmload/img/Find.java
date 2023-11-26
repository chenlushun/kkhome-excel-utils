package com.kkhome.excel.utils.dowmload.img;

import com.google.common.base.Joiner;
import com.kkhome.excel.utils.PathUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {
    public static final String downLoadPath = PathUtils.getPathPrex() + "1688下载\\";

    public static List<String> test(String huohao, String path) throws IOException {
        String h = huohao.split("\\.")[0];
        Document doc = Jsoup.parse(new File(path), "utf-8");
        List<Element> skuImgEles = doc.select(".detail-gallery-img");
        int i = 1;
        for (Element element : skuImgEles) {
            String sr = element.attr("src");
            downLoadFromUrl(sr, "窗口" + i + ".jpg", downLoadPath + h);
            i++;
        }
        List<Element> detailEles = doc.select(".content-detail").select("img");
        i = 0;
        for (Element element : detailEles) {
            String sr = element.attr("data-lazyload-src");
            try {
                downLoadFromUrl(sr, "详情" + i + ".jpg", downLoadPath + h);
            } catch (IOException e) {
                System.out.println(sr);
            }
            i++;
        }

        return null;
    }


    public static void dataToFile(String txt, String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(txt);//换行符，等价于 = bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream;
        try {

            inputStream = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return;

        }
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File(saveDir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        System.out.println(fileName + "info:" + url + " download success");
    }


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    public static SkuEntity test2(String huohao, String path) throws IOException {
        String h = huohao.split("\\.")[0];
        Document doc = Jsoup.parse(new File(path), "utf-8");
        List<Element> skuImgEles = doc.select(".prop-item-wrapper .prop-name");

        List<String> cols = new ArrayList<>();
        for (Element skuImgEle : skuImgEles) {
            cols.add(skuImgEle.text());
        }

        List<Element> colEles = doc.select(".sku-item-name");

        List<String> sizes = new ArrayList<>();
        for (Element colEle : colEles) {
            sizes.add(colEle.text().replace("*", "x"));
        }


        SkuEntity skuEntity = new SkuEntity();
        skuEntity.setName(h);
        skuEntity.setSize(Joiner.on("|").join(sizes));
        skuEntity.setCol(Joiner.on("|").join(cols));


        return skuEntity;
    }
}
