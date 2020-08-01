package modules.thread;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/23
 */
public class EndThread1 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("thread1 start");
                Thread.sleep(100000);
                System.out.println("thread1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.stop();
        System.out.println("thread1 stop");
    }
}
