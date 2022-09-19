package com.dongtech.mywxwork_bot.wx;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class WxProducerService {
    private static final Logger logger = LoggerFactory.getLogger(WxProducerService.class);
    final String APIWXBOT = "/WXBOT";
    final String LOGTITLE = "[dongtech-server] ["+APIWXBOT+"]";

    @Autowired
    private WxPushService wxPushService;

    @Value("${wx.wxbot.token}")
    public String WXBOTTOKEN;

    private Queue<WxRequest> wxRequestQueue = new LinkedList<WxRequest>();

    public void sendMessage(WxRequest wxRequest) throws IOException {
        if (wxRequest.message == null || wxRequest.message == "") {
            return;
        }

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(WXBOTTOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        //构建一个json格式字符串textMsg，其内容是接收方需要的参数和消息内容
        String textMsg = "{\"msgtype\":\"text\",\"text\":{\"content\":\"SERVER:"+ wxRequest.message+"\"},\"isAtAll\":false}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String msg = LOGTITLE+" WXBOT Send Success : "+wxRequest.message;
            logger.info(msg);
        }else{
            wxPushService.publishEvent(wxRequest);
        }
    }

    public void Receive(WxRequest wxRequest){
        wxRequestQueue.offer(wxRequest);
    }

    public void DoSend() throws IOException {
       WxRequest req = wxRequestQueue.poll();
       if (req == null){
           return;
       }

       send(req);
    }

    /**
     * 发送
     * @param wxRequest
     */
    public void send(WxRequest wxRequest) throws IOException {
        sendMessage(wxRequest);
    }
}
