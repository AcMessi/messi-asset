package com.tech.mq.modules.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消费者
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/18
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(id = "foo", topics = "kafka-boot")
    public void listen(String foo) {
        log.info("message content [{}]", foo);
    }

}
