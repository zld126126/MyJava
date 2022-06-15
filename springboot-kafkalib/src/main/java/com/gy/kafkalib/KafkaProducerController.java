package com.gy.kafkalib;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;

@Controller
public class KafkaProducerController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    final String APISENDMESSAGE = "/send-message";
    final String APISENDMESSAGEKEYVALUE = "/send-message-key-value";
    final String LOGTITLE = "[[gy-kafka]] ";

    //http://localhost:11226/send-message?topic=pet_home&message=hengjie
    @RequestMapping(value=APISENDMESSAGE)
    @ResponseBody
    public boolean sendMessage(String topic,String message) {

        try {
            SendResult sendResult = kafkaTemplate.send(topic, message).get();
            RecordMetadata recordMetadata = sendResult.getRecordMetadata();
            logger.info(recordMetadata.topic());
        } catch (InterruptedException e) {
            logger.info(LOGTITLE+APISENDMESSAGE+" send message failed: topic:"+topic+",message:"+message);
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            logger.info(LOGTITLE+APISENDMESSAGE+" send message failed: topic:"+topic+",message:"+message);
            e.printStackTrace();
            return false;
        }
        logger.info(LOGTITLE+APISENDMESSAGE+" send message success: topic:"+topic+",message:"+message);
        return true;
    }

    //http://localhost:11226/send-message-key-value?topic=pet_home&key=333&key=123456&value=don
    @RequestMapping(APISENDMESSAGEKEYVALUE)
    @ResponseBody
    public boolean sendMessageKeyValue(String topic, String key,String value) {
        try {
            SendResult sendResult = kafkaTemplate.send(topic, key, value).get();
            RecordMetadata recordMetadata = sendResult.getRecordMetadata();
        } catch (InterruptedException e) {
            logger.info(LOGTITLE+APISENDMESSAGEKEYVALUE+" send key-value failed: topic:"+topic+",key:"+key+",value:"+value);
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            logger.info(LOGTITLE+APISENDMESSAGEKEYVALUE+" send key-value failed: topic:"+topic+",key:"+key+",value:"+value);
            e.printStackTrace();
            return false;
        }
        logger.info(LOGTITLE+APISENDMESSAGEKEYVALUE+" send key-value success: topic:"+topic+",key:"+key+",value:"+value);
        return true;
    }
}
