package modules.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/23
 */
public class EndThread2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(() -> {
            try {
                System.out.println("thread start");
                Thread.sleep(10000);
                System.out.println("thread end");
            } catch (InterruptedException e) {
                System.out.println("cancel success");
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //取消
        System.out.println("cancel start");
        future.cancel(true);
        System.out.println("cancel end");
    }
}
