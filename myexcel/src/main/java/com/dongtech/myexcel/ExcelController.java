package com.dongtech.myexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "Excel接口")
@Controller
public class ExcelController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @ApiOperation(value = "运行状态")
    @RequestMapping(method= {RequestMethod.GET},value="/test")
    @ResponseBody
    public String TaskTest() {
        JSONObject json = new JSONObject();
        json.put("dongtech-myexcel","SUCCESS!!!");

        logger.info(String.valueOf(json));
        return json.toString();
    }

    @ApiOperation(value = "上传excel文件")
    @RequestMapping(method= {RequestMethod.GET,RequestMethod.POST},value="/uploadexcel")
    @ResponseBody
    public String UploadExcel(MultipartFile upload){
        JSONObject res = new JSONObject();
        //上传文件为空...
        if (upload == null){
            res.put("ErrorMessage","请选择一个上传文件!!!");
            return res.toString();
        }

        String fileName = upload.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".")+1);
        if(!ExcelUtils.CheckExtension(extension)){
            res.put("ErrorMessage","只支持xls和xlsx格式的文件!!!");
            return res.toString();
        }

        try{
            InputStream inputStream = upload.getInputStream();
            ExcelListener listener = new ExcelListener();
            //headRowNumber从第0行开始需要读表头
            EasyExcel.read(inputStream, listener).sheet().headRowNumber(0).doRead();
            res.put("TableTitle",listener.GetHead());
            res.put("TableContent",listener.GetBody());
        }catch (Exception e){
            logger.error(e.toString());
            res.put("ErrorMessage","上传失败!!!");
            return res.toString();
        }
        return res.toString();
    }
}
