package com.dongtech.mywxwork_bot.wx;

import org.springframework.context.ApplicationEvent;

/**
 * wx事件
 */
public class WxEvent extends ApplicationEvent {
    private static final long serialVersionUID = -2681986913481417971L;

    /**
     * WxRequest
     * @param source
     */
    public WxEvent(WxRequest source) {
        super(source);
    }

    @Override
    public WxRequest getSource(){
        return (WxRequest) super.getSource();
    }
}
