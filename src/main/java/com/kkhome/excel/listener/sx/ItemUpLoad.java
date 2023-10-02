package com.kkhome.excel.listener.sx;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.kkhome.excel.utils.ExcelUtils.writeExcel;


/**
 * 处理乐天 vitastudio 后台商品上传问题
 */
public class ItemUpLoad {

    private final static String URL_PREX = "https://image.rakuten.co.jp/vitastudio/cabinet/";

    private static final Map<String, String> map = new HashMap<>();

    static {
        // https://image.rakuten.co.jp/vitastudio/cabinet/bb/bbsk10_08.jpg
        map.put("母婴", "bb");
        // https://image.rakuten.co.jp/vitastudio/cabinet/pet/pet07_07.jpg
        map.put("宠物", "pet");
        // https://image.rakuten.co.jp/vitastudio/cabinet/tb/tbsize1.jpg
        map.put("桌布", "tb");
        // https://image.rakuten.co.jp/vitastudio/cabinet/bk/bkhfg09_07.jpg
        map.put("盖毯", "bk");
        // https://image.rakuten.co.jp/vitastudio/cabinet/ml/mlgjm02_07.jpg
        map.put("门帘", "ml");
        https:
//image.rakuten.co.jp/vitastudio/cabinet/sf/sfbs31_06.jpg
        map.put("沙发垫", "sf");
        https:
//image.rakuten.co.jp/vitastudio/cabinet/cl1/cl2p43s_12.jpg
        map.put("窗帘", "cl1");
// 特殊匹配
        // map.put("窗帘2", "cl2");
        map.put("zk", "zk");
        map.put("bed", "bed");
    }


    public static void main(String[] args) throws IOException {
        itemUpload();
    }

    /**
     * 读取文件系统
     * 遍历文件系统
     * 高度超过2000px的忽略
     * 4列，分别是:货号，手机版，pc版，详情图
     * CsvUtils.writeDataListToCsv();
     * 输出到excel
     */
    public static void itemUpload() {
        try {
            Collection<String> values = map.values();
            // 一列
            List<List<String>> l1 = new ArrayList<>();

            File f = new File("D:\\发货\\美图批处理照片");

            File[] files = f.listFiles();
            Map<String, List<String>> huoHao = new HashMap<>();
            Map<String, List<ItemSku>> itemSkus = new HashMap<>();
            for (File imageFile : files) {
                // 图片对象
                BufferedImage bufferedImage = ImageIO.read(new FileInputStream(imageFile));

                // 宽度
                int width = bufferedImage.getWidth();
                // 高度
                int height = bufferedImage.getHeight();
                if (height >= 2000) {
                    System.out.println("skip：" + imageFile.getName());
                    continue;
                }
                // 货号
                String[] codes = imageFile.getName().split("_");
                String goodsNo = codes[0];
                // 货号处理
                extracted(values, huoHao, imageFile, goodsNo, itemSkus);

            }

            huoHao.forEach((k, v) -> {
                List<String> stringList = new ArrayList<>();
                stringList.add(k);

                // 手机版,<img width="100%" src="https://image.rakuten.co.jp/vitastudio/cabinet/pet/pet01_07.jpg">
                StringBuilder mobile = new StringBuilder();
                for (String image : v) {
                    if (mobile.length() != 0) {
                        mobile.append("\n");
                    }
                    mobile.append("<img width=\"100%\" src=\"").append(image).append("\">");
                }
                stringList.add(mobile.toString());
                // pc版,<img width="750px" src="https://image.rakuten.co.jp/vitastudio/cabinet/cl1/cl2p43s_13.jpg">
                StringBuilder pc = new StringBuilder();
                for (String image : v) {
                    if (pc.length() != 0) {
                        pc.append("\n");
                    }
                    pc.append("<img width=\"750px\" src=\"").append(image).append("\">");
                }
                stringList.add(pc.toString());
                // sku 图
                List<ItemSku> itemSkuList = itemSkus.get(k);
                for (ItemSku itemSku : itemSkuList) {
                    stringList.add(itemSku.getCol1());
                    stringList.add(itemSku.getCol2());
                    stringList.add(itemSku.getCol3());
                }
                l1.add(stringList);
            });
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
            writeExcel(l1, "D:\\发货\\上新-" + simpleDateFormat.format(new Date()) + ".xlsx");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "文件被占用", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private static void extracted(Collection<String> values, Map<String, List<String>> huoHao, File imageFile,
                                  String goodsNo, Map<String, List<ItemSku>> itemSkus) {
        // k=huoHao,value=image;
        List<String> images = huoHao.get(goodsNo);
        // sku处理
        List<ItemSku> itemSkuList = itemSkus.get(goodsNo);
        if (images == null) {
            images = new ArrayList<>();
            itemSkuList = new ArrayList<>();
        }
        String fileName = "";
        if (imageFile.getName().contains("cl2")) {
            fileName = "cl1";
        } else {
            for (String value : values) {
                if (imageFile.getName().contains(value)) {
                    fileName = value;
                }
            }
        }
        images.add(URL_PREX + fileName + "/" + imageFile.getName());
        huoHao.put(goodsNo, images);
        ItemSku itemSku = new ItemSku();
        itemSku.setCol2("/" + fileName + "/" + imageFile.getName());
        itemSkuList.add(itemSku);
        itemSkus.put(goodsNo, itemSkuList);
    }

    public static boolean checkcountname(String countname) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(countname);
        return m.find();
    }
}
