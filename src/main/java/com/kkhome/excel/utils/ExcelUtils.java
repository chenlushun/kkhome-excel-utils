package com.kkhome.excel.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.getString;

public class ExcelUtils {
    /**
     * 读取Excel文件的内容
     *
     * @param inputStream excel文件，以InputStream的形式传入
     * @param sheetName   sheet名字
     * @return 以List返回excel中内容
     */
    public static List<Map<String, String>> readExcel(InputStream inputStream, String sheetName) {

        //定义工作簿
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(inputStream);
        } catch (Exception e) {
            System.out.println("Excel data file cannot be found!");
        }

        //定义工作表
        XSSFSheet xssfSheet;
        if (sheetName.equals("")) {
            // 默认取第一个子表
            xssfSheet = xssfWorkbook.getSheetAt(0);
        } else {
            xssfSheet = xssfWorkbook.getSheet(sheetName);
        }

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        //定义行
        //默认第一行为标题行，index = 0
        XSSFRow titleRow = xssfSheet.getRow(0);

        //循环取每行的数据
        for (int rowIndex = 1; rowIndex < xssfSheet.getPhysicalNumberOfRows(); rowIndex++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowIndex);
            if (xssfRow == null) {
                continue;
            }

            Map<String, String> map = new LinkedHashMap<String, String>();
            //循环取每个单元格(cell)的数据
            for (int cellIndex = 0; cellIndex < xssfRow.getPhysicalNumberOfCells(); cellIndex++) {
                XSSFCell titleCell = titleRow.getCell(cellIndex);
                XSSFCell xssfCell = xssfRow.getCell(cellIndex);
                map.put(getString(titleCell), getString(xssfCell));
            }
            list.add(map);
        }
        return list;
    }


    /**
     * 把内容写入Excel
     *
     * @param list 传入要写的内容，此处以一个List内容为例，先把要写的内容放到一个list中
     * @param path 把输出流怼到要写入的Excel上，准备往里面写数据
     */
    public static void writeExcel(List<List<String>> list, String path) {
        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

        //创建工作表
        XSSFSheet xssfSheet;
        xssfSheet = xssfWorkbook.createSheet();

        //创建行
        XSSFRow xssfRow;

        //创建列，即单元格Cell
        XSSFCell xssfCell;

        //把List里面的数据写到excel中
        for (int i = 0; i < list.size(); i++) {
            //从第一行开始写入
            xssfRow = xssfSheet.createRow(i);
            //创建每个单元格Cell，即列的数据
            List<String> sub_list = list.get(i);
            for (int j = 0; j < sub_list.size(); j++) {
                //创建单元格
                xssfCell = xssfRow.createCell(j);

                //设置单元格内容
                String context = sub_list.get(j);
                if (StringUtils.isNotEmpty(context)) {
                    xssfCell.setCellValue(context.replace("\"", ""));
                } else {
                    xssfCell.setCellValue(context);
                }
            }
        }

        //用输出流写到excel
        try {
            OutputStream outputStream = new FileOutputStream(path);
            xssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 把内容写入Excel
     *
     * @param list 传入要写的内容，此处以一个List内容为例，先把要写的内容放到一个list中
     * @param path 把输出流怼到要写入的Excel上，准备往里面写数据
     */
    public static void writeExcel2(List<List<String>> list, String path) {
        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();

        //创建工作表
        XSSFSheet xssfSheet;
        xssfSheet = xssfWorkbook.createSheet();

        //创建行
        XSSFRow xssfRow;

        //创建列，即单元格Cell
        XSSFCell xssfCell;

        //把List里面的数据写到excel中
        for (int i = 0; i < list.size(); i++) {
            //从第一行开始写入
            xssfRow = xssfSheet.createRow(i);
            //创建每个单元格Cell，即列的数据
            List<String> sub_list = list.get(i);
            for (int j = 0; j < sub_list.size(); j++) {
                //创建单元格
                xssfCell = xssfRow.createCell(j);

                //设置单元格内容
                String context = sub_list.get(j);
                if (StringUtils.isNotEmpty(context)) {
                    // xssfCell.setCellValue(context.replace("\"", ""));
                    xssfCell.setCellValue(context);
                } else {
                    xssfCell.setCellValue(context);
                }
            }
        }

        //用输出流写到excel
        try {
            OutputStream outputStream = new FileOutputStream(path);
            xssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
