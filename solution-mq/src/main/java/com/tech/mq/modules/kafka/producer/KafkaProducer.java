package com.tech.mq.modules.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @Description: 生产者
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/18
 */
@RestController
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> template;

    /**
     * 同步发送
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/syncSendMessage")
    public String syncSendMessage() {
        for (int i = 0; i < 100; i++) {
            try {
                template.send("kafka-boot", "0", "messitest" + i).get();
            } catch (InterruptedException e) {
                log.error("sync send message fail [{}]", e.getMessage());
                e.printStackTrace();
            } catch (ExecutionException e) {
                log.error("sync send message fail [{}]", e.getMessage());
                e.printStackTrace();
            }
        }
        return "success";
    }

    /**
     * 异步发送
     *
     * @return
     */
    @GetMapping("/asyncSendMessage")
    public String sendMessageAsync() {
        for (int i = 0; i < 100; i++) {
            /**
             * <p>
             * SendResult:如果消息成功写入kafka就会返回一个RecordMetaData对象;result.
             * getRecordMetadata() 他包含主题信息和分区信息，以及集成在分区里的偏移量。
             * 查看RecordMetaData属性字段就知道了
             * </p>
             *
             */
            ListenableFuture<SendResult<String, String>> send = template.send("kafka-boot", "0", "test" + i);
            send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("async send message success partition [{}]", result.getRecordMetadata().partition());
                    log.info("async send message success offest[{}]", result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.error("async send message fail [{}]", ex.getMessage());

                }
            });
        }
        return "success";
    }
}
