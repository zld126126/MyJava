package com.dongtech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UnitTestController {
    private static final Logger logger = LoggerFactory.getLogger(UnitTestController.class);

    @RequestMapping(value="/unittest")
    @ResponseBody
    public String UnitTest(){
        logger.info("UnitTestController-UnitTest");
        return "unittest";
    }
}
