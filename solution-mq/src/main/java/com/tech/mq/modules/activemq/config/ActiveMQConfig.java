package com.tech.mq.modules.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/18
 */
@Configuration
public class ActiveMQConfig {

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("springboot.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("springboot.topic");
    }
    
}