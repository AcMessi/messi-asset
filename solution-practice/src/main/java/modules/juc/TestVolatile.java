package modules.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/8/29
 */
public class TestVolatile {

    public static CountDownLatch latch;
    public static int a = 0;
//    public static volatile int a = 0;

    public static void multiThread(int threadCnt) {
        latch = new CountDownLatch(threadCnt);

        for (int i = 0; i < threadCnt; i++) {
            new Thread(() -> {
                //这里延迟1毫秒,增加线程切换的随机性,也可以不加
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }

                a++;
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int threadCnt = 1000;
        int times = 10;

        System.out.println(threadCnt + "个线程并发计算i++:");
        //运行多次观察不同
        for (int x = 0; x < times; x++) {
            //初始化变量
            a = 0;
            //同时启动 1000 个线程，并发计算i++
            multiThread(threadCnt);
            System.out.println("运行第 " + (x + 1 < 10 ? "0" : "") + (x + 1) + " 次的结果: a=" + a);
        }

    }
}
