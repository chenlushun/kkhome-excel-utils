package com.kkhome.excel.utils.dowmload.img;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SkuEntity {

    @ExcelProperty("商品管理番号（商品URL）")
    private String name;
    @ExcelProperty("バリエーション1選択肢定義")
    private String col;
    @ExcelProperty("バリエーション2選択肢定義")
    private String size;
}
