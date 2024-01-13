package com.kkhome.excel.utils.dowmload.img;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SkuPriceEntity {

    @ExcelProperty("商品编号")
    private String code;
    @ExcelProperty("尺寸")
    private String size;
    @ExcelProperty("价格")
    private String price;
}
