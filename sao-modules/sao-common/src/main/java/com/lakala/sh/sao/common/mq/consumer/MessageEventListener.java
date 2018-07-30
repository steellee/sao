//package com.lakala.sh.sao.common.mq.consumer;
//
//import com.lakala.sh.framework.core.LogUtils;
//import com.lakala.sh.framework.core.event.MessageEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author steellee
// * @date 2018/06/28
// * @version 1.0.0
// */
//@Component
//public class MessageEventListener implements ApplicationListener<MessageEvent> {
//    private final static Logger LOGGER = LoggerFactory.getLogger(MessageEventListener.class);
//
//    @Override
//    public void onApplicationEvent(MessageEvent event) {
//        // 消息的ID，用于消息排重
//        String key = event.getKey();
//        // 消息体，可能是JSON，也可能是XML，具体消息体格式由开发人员确定
//        String message = event.getBody();
//        LogUtils.info(LOGGER, "接收到消息, key={0}, message={1}", key, message);
//    }
//
//}
