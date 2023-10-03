package com.kkhome.excel.listener;

import com.kkhome.excel.listener.sx.ItemSkuMerge;
import com.kkhome.excel.listener.sx.ItemUpLoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExecActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "vitastudio发货单处理":
                new VitastudioFHExcelHandler().doHandler();
                break;
            case "Excel合并(KKHOME)":
                new MergeAndDistinctExcelHandler().doHandler();
                break;
            case "Excel合并(星川)":
                new XingChuanMergeAndDistinctExcelHandler().doHandler();
                break;
            case "佐川地址处理":
                new ZuoChuanAddressHandler().doHandler();
                break;
            case "物流单号转换":
                new ZIFUTranslate().doHandler();
                break;
            case "上新表格生成":
                ItemUpLoad.itemUpload();
            case "sku合并":
                new ItemSkuMerge().doHandler();
            case "1688图片下载":
                new ItemSkuMerge().doHandler();
                break;
        }
    }
}
