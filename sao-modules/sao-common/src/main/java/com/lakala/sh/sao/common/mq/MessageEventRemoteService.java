//package com.lakala.sh.sao.common.mq;
//
//import com.lakala.sh.sao.common.mq.producer.MessageEventPublisher;
//import com.lakala.sh.framework.core.LogUtils;
//import com.lakala.sh.framework.remote.http.RequestParameter;
//import com.lakala.sh.framework.remote.http.annotation.HttpService;
//import com.lakala.sh.framework.remote.http.annotation.ServiceMapping;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * @author steellee
// * @date 2018/06/28
// * @version 1.0.0
// * http://localhost:8080/XXX/message/publish?key=123&message=abc
// */
////@HttpService(serviceName = "message")
//public class MessageEventRemoteService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(MessageEventRemoteService.class);
//
//    @Autowired
//    private MessageEventPublisher messageEventPublisher;
//
//    /**
//     *
//     * @param parameter
//     * @throws Exception
//     */
//    @ServiceMapping(method = RequestMethod.GET, path = "publish")
//    public void add(RequestParameter parameter) throws Exception {
//        String key = parameter.getString("key");
//        String message = parameter.getString("message");
//        messageEventPublisher.publish(key, message);
//        LogUtils.info(LOGGER, "发布通知消息完成, key={0}, message={1}", key, message);
//    }
//}
