package com.dongtech.mywxwork_bot.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class WxProducerController {
    private static final Logger logger = LoggerFactory.getLogger(WxProducerController.class);
    final String APIWXBOT = "/WXBOT";
    final String LOGTITLE = "[dongtech-server] ["+APIWXBOT+"]";

    @Autowired
    private WxProducerService wxProducerService;

    @Autowired
    private WxPushService wxPushService;

    /**
     * 发送企业微信机器人消息
     * @param request
     * @throws IOException
     */
    @RequestMapping(value=APIWXBOT)
    @ResponseBody
    public void sendWXBOT(HttpServletRequest request) throws IOException {
        String message = request.getParameter("message");
        if (message == null || message == "") {
            return;
        }

        String msg = GetCurrentTime()+message;
        String logMsg = LOGTITLE+" WXBOT Receive Message: "+msg;
        logger.info(logMsg);

        WxRequest wxRequest = new WxRequest();
        wxRequest.message = msg;
        wxPushService.publishEvent(wxRequest);
    }

    private String GetCurrentTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss||");
        return format.format(new Date());
    }
}
