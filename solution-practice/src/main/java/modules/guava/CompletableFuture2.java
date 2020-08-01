package modules.guava;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/23
 */
@Slf4j
public class CompletableFuture2 {

    public static void main(String[] args) {
        // 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
        java.util.concurrent.CompletableFuture.supplyAsync(() -> {
            log.info("start1");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("end1");
            return "hello";
        }).runAfterEither(
                CompletableFuture.supplyAsync(() -> {
                    log.info("start2");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("end2");
                    return "hello";
                }), System.out::println
        );

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
