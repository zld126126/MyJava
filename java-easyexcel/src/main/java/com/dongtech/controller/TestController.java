package com.dongtech.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.dongtech.entity.StudentExcelModel;
import com.dongtech.listener.StudentExcelModelListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.RequestWrapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    //localhost:9999/writeExcel
    @ResponseBody
    @RequestMapping("/writeExcel")
    public String writeExcel(){
        String result = null;
        OutputStream out = null;
        try {
            out = new FileOutputStream("withHead.xls");
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS);
            //out = new FileOutputStream("withHead.xls");
            //ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0, StudentExcelModel.class);
            sheet1.setSheetName("sheet1");
            List<StudentExcelModel> data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                StudentExcelModel item = new StudentExcelModel();
                item.setName("name" + i);
                item.setAge("age" + i);
                item.setAddress("address" + i);
                item.setEmail("email" + i);
                item.setHeigh("heigh" + i);
                item.setLast("last" + i);
                item.setSax("sax" + i);
                data.add(item);
            }
            writer.write(data, sheet1);
            writer.finish();
            result = "success";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result = "failed";
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    //localhost:9999/readExcel
    @ResponseBody
    @RequestMapping("/readExcel")
    public String readExcel(@RequestParam("file") MultipartFile multipartFile){
        String result = null;
        String filename = multipartFile.getOriginalFilename();
        InputStream inputStream = null;
        //InputStream inputStream = new FileInputStream("d:/excelTest/test.xlsx");
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StudentExcelModelListener listener = new StudentExcelModelListener();
        ExcelReader excelReader = null;
        if(filename.contains(".xlsx")){
            excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
        }else{
            excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
        }
        try {
            excelReader.read(new Sheet(1,1,StudentExcelModel.class));
            result = "success";
        }catch (Exception e){
            e.printStackTrace();
            result = "failed";
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

