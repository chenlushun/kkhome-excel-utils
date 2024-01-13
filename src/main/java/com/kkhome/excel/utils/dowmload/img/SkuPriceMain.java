package com.kkhome.excel.utils.dowmload.img;

import com.alibaba.excel.EasyExcel;
import com.kkhome.excel.utils.PathUtils;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SkuPriceMain {

    @SneakyThrows
    public static void main(String[] args) {
        handler();
    }

    @SneakyThrows
    public static void handler() {
        File file = new File(PathUtils.getPathPrex() + "1688");
        File[] files = file.listFiles();
        List<SkuPriceEntity> skuEntities = new ArrayList<>();
        for (File file1 : files) {
            List<SkuPriceEntity> sku = Find.test3(file1.getName(), file1.getAbsolutePath());

            skuEntities.addAll(sku);
        }

        String fileName = PathUtils.getPathPrex() + "simplePrice" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, SkuPriceEntity.class).sheet("模板").doWrite(() -> {
            // 分页查询数据
            return skuEntities;
        });
    }
}
