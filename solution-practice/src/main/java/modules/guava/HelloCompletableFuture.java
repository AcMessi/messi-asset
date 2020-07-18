package modules.guava;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @Description: CompletableFuture方法测试
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/3
 */
@Slf4j
public class HelloCompletableFuture {

    public static void main(String[] args) {

        // 进行变换
        String result1 = CompletableFuture.supplyAsync(() -> "hello").thenApply(a -> a + "world").join();
        log.info("result1 {}", result1);

        // 进行消耗
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(a -> log.info(a + "world"));

        // 对上一步的计算结果不关心，执行下一个操作
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenRun(() -> log.info("hello world"));

        // 集合两个completionStage结果，进行转化后返回
        String result2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "world";
                }),
                (s1, s2) -> s1 + " " + s2
        ).join();
        log.info("result2 {}", result2);

        // 集合两个completionStage结果，进行消耗
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "world";
                }),
                (s1, s2) -> log.info(s1 + " " + s2)
        );

        // 两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的转化操作
        String result3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).applyToEither(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello world";
                }), s -> s
        ).join();
        log.info("result3 {}", result3);

        // 两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的消耗操作
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).acceptEither(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello world";
                }), log::info
        );

        // 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).runAfterEither(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello";
                }), System.out::println
        );

        // 当运行时出现了异常，可以通过exceptionally进行补偿。
        String result4 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (true)
                throw new RuntimeException("测试异常情况");

            return "hello";
        }).exceptionally(
                e -> {
                    log.info(e.getMessage());
                    return "hello world exception";
                }
        ).join();
        log.info("result4 {}", result4);

        // 当运行完成时，对结果的记录
        String result5 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (true)
                throw new RuntimeException("测试异常情况");

            return "hello";
        }).whenComplete(
                (s, t) -> {
                    log.info(s);
                    log.info(t.getMessage());
                }
        ).exceptionally(
                e -> {
                    log.info(e.getMessage());
                    return "hello world";
                }
        ).join();
        log.info("result5 {}", result5);

        // 当运行完成时，对结果的处理
        String result6 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (true)
                throw new RuntimeException("测试异常情况");

            return "hello";
        }).handle(
                (s, t) -> {
                    if (t != null) {
                        return "hello world";
                    }
                    return "hello exception";
                }
        ).join();
        log.info("result6 {}", result6);
    }

}
