package com.dongtech.mywxwork_bot.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class WxTimer {
    private static final Logger logger = LoggerFactory.getLogger(WxTimer.class);

    @Resource
    WxProducerService wxProducerService;

    @Scheduled(fixedRate=1000)
    public void DoSend() throws IOException {
        wxProducerService.DoSend();
    }
}
