package com.gy.kafkalib;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    //http://localhost:11226/test
    @RequestMapping(value="/test")
    @ResponseBody
    public String test() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("GY-KAFKA","SUCCESS!!!");

        logger.info(String.valueOf(json));
        return json.toString();
    }
}
