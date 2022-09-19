package com.dongtech.mywxwork_bot.wx;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class WxListener {
    @Resource
    private WxProducerService wxProducerService;

    @EventListener
    public void OnWxEvent(WxEvent event){
        WxRequest request = event.getSource();
        wxProducerService.Receive(request);
    }
}
