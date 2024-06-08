package com.example.BatchProcessing.download;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ExcelDownload {
    private SXSSFWorkbook workbook;
    private SXSSFSheet worksheet;
    private int numberOfColumn;
    private int lastRowNum=-1;
    public ExcelDownload(SXSSFWorkbook workbook){
        this.workbook=workbook;
        if (workbook.getNumberOfSheets() > 0) {
            this.worksheet = workbook.getSheetAt(0);
            this.lastRowNum = worksheet.getLastRowNum();
        }
    }

    public static ExcelDownload builder(){
        return new ExcelDownload(new SXSSFWorkbook(20));
    }

    public static ExcelDownload builder(File file){
        try {
           return new ExcelDownload(new SXSSFWorkbook(new XSSFWorkbook(file)));
        }
        catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public ExcelDownload createWorkSheet(String sheetName,int numberOfColumn){
        log.info("Creating Worksheet {}",sheetName);
        this.worksheet=workbook.createSheet(sheetName);
        this.numberOfColumn=numberOfColumn;
        return this;
    }

    public ExcelDownload header(List<String> headers){
        log.info("Creating headers ");
        log.info("In header, last row {}",lastRowNum);
        Row header=worksheet.createRow(++lastRowNum);
        for(int i=0;i<headers.size();i++){
           Cell cell=header.createCell(i);
           cell.setCellValue(headers.get(i));
        }
        return this;
    }


    public ExcelDownload rows(List<List>dataList){
//        log.info("Worksheet {}",worksheet.getSheetName());
        log.info("areAllRowsFlushed = {}, lastFlushedRowNum = {}", worksheet.areAllRowsFlushed(), worksheet.getLastFlushedRowNum());
        log.info("Last row {}",lastRowNum);
        for(List<Object> data:dataList){
            Row row=workbook.getSheetAt(0).createRow(++lastRowNum);
            for(int i=0;i<data.size();i++) {
                Cell cell = row.createCell(i);
                Object obj=data.get(i);
                if(obj instanceof String){
                    cell.setCellValue((String)obj);
                }
                else if (obj instanceof Integer) {
                    cell.setCellValue((Integer)obj);
                }
                else if (obj instanceof Double){
                    cell.setCellValue((Double)obj);
                }
            }
        }
        return this;
    }

    public void save(File file){
        String filePath=file.getAbsolutePath();
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(filePath,true);
            log.info("writing data to {}",filePath);
            log.info("All Row Flushed {}",worksheet.areAllRowsFlushed());
            workbook.write(fileOutputStream);
        }
        catch (Exception e){
            log.error("Exception ",e);
        }
    }

    public void deleteFile(File file) {
        if(file.delete()){
            log.info("File deleted successfully");
        }
        else{
            log.error("Error deleting file");
        }
    }

}
