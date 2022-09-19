package com.dongtech.mywxwork_bot.wx;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxPushService {
    @Resource
    private ApplicationEventPublisher publisher;

    /**
     * 发布事件
     * @param wxRequest
     */
    public void publishEvent(WxRequest wxRequest){
        publisher.publishEvent(new WxEvent(wxRequest));
    }
}
