package com.kkhome.excel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SkuVO {

    @ExcelProperty("商品管理番号（商品URL）")
    private String code;
    @ExcelProperty("バリエーション1選択肢定義")
    private String key1;
    @ExcelProperty("バリエーション2選択肢定義")
    private String key2;
}
