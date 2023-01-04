package com.dongtech.myexcel;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @ApiOperation(value = "测试运行状态")
    @RequestMapping(method= {RequestMethod.GET},value="/test")
    @ResponseBody
    public String TaskTest() {
        JSONObject json = new JSONObject();
        json.put("dongtech-myexcel","SUCCESS!!!");

        logger.info(String.valueOf(json));
        return json.toString();
    }
}
