package com.kkhome.excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ShangHaiDelivery {
    @ExcelProperty("客户单号")
    private String customNo;
    @ExcelProperty("转单号")
    private String transferNo;
    @ExcelProperty("重量")
    private String wright;
    @ExcelProperty("收件人姓名")
    private String name;
    @ExcelProperty("联系地址")
    private String address;
    @ExcelProperty("城市")
    private String city;
    @ExcelProperty("州,省")
    private String pro;
    @ExcelProperty("收件人电话")
    private String phone;
    @ExcelProperty("收件人邮编")
    private String post;
    @ExcelProperty("英文品名1")
    private String enName;
    @ExcelProperty("中文品名1")
    private String chName;
    @ExcelProperty("配货信息1（SKU）")
    private String sku1;
    @ExcelProperty("材质1")
    private String material1;
    @ExcelProperty("申报价值1（美元）")
    private String applyValue1;
    @ExcelProperty("申报品数量1")
    private String applyNum1;
    @ExcelProperty("英文品名2")
    private String enName2;
    @ExcelProperty("中文品名2")
    private String chName2;
    @ExcelProperty("配货信息2（SKU）")
    private String sku2;
    @ExcelProperty("材质2")
    private String material2;
    @ExcelProperty("申报价值2")
    private String applyVal2;
    @ExcelProperty("申报品数量2")
    private String applyNum2;
    @ExcelProperty("英文品名3")
    private String enName3;
    @ExcelProperty("配货信息3（SKU）")
    private String sku3;
    @ExcelProperty("海关报关品名3")
    private String custom3;
    @ExcelProperty("材质3")
    private String material3;
    @ExcelProperty("申报价值3")
    private String applyVal3;
    @ExcelProperty("申报品数量3")
    private String applyNum3;
}
