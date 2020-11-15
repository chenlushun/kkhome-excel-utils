package com.kkhome.excel.listener;

import com.kkhome.excel.utils.CsvUtils;
import com.kkhome.excel.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractExcelHandler {

    /**
     * 分割
     */
    public static final String csvSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    /**
     * @param from /Users/kinoko/Desktop/kkhome/RB_kkhome_376890-20200531211133原始文档的副本.csv
     * @param to   /Users/kinoko/Desktop/kkhome/tes.xlsx
     */
    public void readCsvToExcel(String from, String to) {
        List<List<String>> result = new ArrayList<>();
        List<String> stringList = CsvUtils.readCsv(from);
        handlerExcel(result, stringList);
        ExcelUtils.writeExcel(result, to);
    }

    /**
     * 数据处理
     *
     * @param result     结果数据
     * @param stringList 入参数据
     */
    public abstract void handlerExcel(List<List<String>> result, List<String> stringList);

}
