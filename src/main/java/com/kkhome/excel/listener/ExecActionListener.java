package com.kkhome.excel.listener;

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
        }
    }
}
