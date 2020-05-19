package com.tech.mq.modules.activemq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消费者
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/18
 */
@Component
@Slf4j
public class ActiveMqConsumer {

    //接收queue类型消息
    //destination对应配置类中ActiveMQQueue("springboot.queue")设置的名字
    @JmsListener(destination = "springboot.queue")
    public void listenQueue(String msg) {
        log.info("接收到queue消息：" + msg);
    }

    //接收topic类型消息
    //destination对应配置类中ActiveMQTopic("springboot.topic")设置的名字
    //containerFactory对应配置类中注册JmsListenerContainerFactory的bean名称
    @JmsListener(destination = "springboot.topic")
    public void listenTopic(String msg) {
        log.info("接收到topic消息：" + msg);
    }
}
