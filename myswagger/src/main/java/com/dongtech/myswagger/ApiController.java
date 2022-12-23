package com.dongtech.myswagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "API")
@Controller
public class ApiController {
    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @ApiOperation(value = "测试运行状态")
    @RequestMapping(value="/test",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String test(){
        logger.info("test");
        return "test";
    }

    //curl -X GET "http://localhost:9090/query" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"page\": 1,  \"pageSize\": 10,  \"param1\": \"string\"}"
    @ApiOperation(value = "查询API")
    @RequestMapping(value="/query",method = {RequestMethod.GET})
    @ResponseBody
    public void Query(@RequestBody QueryRequest request){
        if (request == null){
            request = new QueryRequest();
            request.page = 1;
            request.pageSize = 10;
        }

        logger.info(String.format("query %d items from PageNo.%d",request.pageSize,request.page));
    }
}
