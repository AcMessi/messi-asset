package modules.guava;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/23
 */
@Slf4j
public class CompletableFuture1 {

    public static void main(String[] args) {
        // 两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的消耗操作
        java.util.concurrent.CompletableFuture.supplyAsync(() -> {
            log.info("start1");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("end1");
            return "hello";
        }).acceptEither(
                java.util.concurrent.CompletableFuture.supplyAsync(() -> {
                    log.info("start2");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("end2");
                    return "hello world";
                }), log::info
        );

        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}
