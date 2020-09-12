package modules.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/8/29
 */
public class TestVolatile2 {

    public static CountDownLatch totalLatch;
    public static CountDownLatch startLatch;
    public static int a = 0;
//    public static volatile int a = 0;

    public static void multiThread(int threadCnt) throws InterruptedException {
        totalLatch = new CountDownLatch(threadCnt);
        startLatch = new CountDownLatch(1);

        for (int i = 0; i < threadCnt; i++) {
            new Thread(() -> {
                //这里延迟1毫秒,增加线程切换的随机性,也可以不加
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }

                try {
                    startLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                a++;
                totalLatch.countDown();
            }).start();
        }

        startLatch.countDown();

        totalLatch.await();
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(1000 + "个线程并发计算i++:");
        //运行多次观察不同
        for (int x = 0; x < 10; x++) {
            //初始化变量
            a = 0;
            //同时启动 1000 个线程，并发计算i++
            multiThread(1000);
            System.out.println("运行第 " + (x + 1 < 10 ? "0" : "") + (x + 1) + " 次的结果: a=" + a);
        }

    }
}
