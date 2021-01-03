package com.kkhome.excel.utils;

import com.kkhome.excel.listener.XingChuanMergeAndDistinctExcelHandler;
import org.apache.commons.lang.math.NumberUtils;

import static com.kkhome.excel.listener.XingChuanMergeAndDistinctExcelHandler.analysisNumber;

public class XingChuanMergeAndDistinctExcelHandlerTest {

    public static void main(String[] args) {
        final XingChuanMergeAndDistinctExcelHandler.Context context = new XingChuanMergeAndDistinctExcelHandler.Context();
        System.out.println(analysisNumber("1", context));
        System.out.println(analysisNumber("01", context));
//        System.out.println(analysisNumber("lkjkl34jLL99l"));
        System.out.println(analysisNumber("2", context));
        System.out.println(NumberUtils.isNumber("2"));
        System.out.println(BCConvert.qj2bj("２").equals("2"));
        System.out.println(NumberUtils.isNumber("２"));
//        System.out.println(analysisNumber("２"));
        System.out.println(analysisNumber(BCConvert.qj2bj("カラー:黒２枚"), context));
        System.out.println(BCConvert.qj2bj("カラー:黒２枚"));


    }
}
