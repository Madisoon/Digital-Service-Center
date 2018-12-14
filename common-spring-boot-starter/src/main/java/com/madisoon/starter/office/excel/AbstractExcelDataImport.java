package com.madisoon.starter.office.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * excel数据导入的抽象类
 *
 * @author Msater Zg
 * @date 2018/11/14 10:00 AM
 */
public abstract class AbstractExcelDataImport {

    private final static String EXCEL_TYPE_XLS = "xls";
    private final static String EXCEL_TYPE_XLSX = "xlsx";

    /**
     * 文件上传
     *
     * @param filePath  文件路径
     * @param sheetName 需要解析的sheetName
     */
    public void excelDataImport(String filePath, String sheetName) {
        // 读取文件
        File file = new File(filePath);
        excelDataImport(file, sheetName);
    }

    /**
     * 文件上传
     *
     * @param file      文件
     * @param sheetName 需要解析的sheetName
     */
    public boolean excelDataImport(File file, String sheetName) {
        boolean flag = true;

        // 文件的类型
        String type = getFileFix(file.getName());

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Workbook workbook = new HSSFWorkbook();

        List<List> list = new ArrayList<>();
        try {
            if (EXCEL_TYPE_XLS.equals(type)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (EXCEL_TYPE_XLSX.equals(type)) {
                workbook = new XSSFWorkbook(inputStream);
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Sheet sheet = workbook.getSheet(sheetName);

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            // 读取每行数据
            Row row = sheet.getRow(i);
            if (row != null) {
                List<Object> rowList = new ArrayList<>();
                short lastCell = row.getLastCellNum();
                short firstCellNum = row.getFirstCellNum();
                for (int j = firstCellNum; j < lastCell; j++) {
                    rowList.add(row.getCell(j));
                }
                list.add(rowList);
            }
        }
        List listReturn = excelDataOperation(list);
        if (listReturn.size() == 0) {
            flag = false;
        }
        return flag;
    }

    /**
     * 获取文件的类型
     *
     * @param fileName 文件名称
     * @return type
     */
    private String getFileFix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 导入逻辑
     *
     * @param list excel表格的数据
     * @return 没有导入成功的数据
     */
    public abstract List excelDataOperation(List list);
}
