package modules.thread;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/29
 */
public class EndThread3 {

    public static void main(String[] args) {
        EndThread3 endThread3 = new EndThread3();
        endThread3.test();
    }

    private void test() {
        String tem = "test";

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("tem" + tem);
        }).start();

        System.out.println("end");
        return;
    }
}
