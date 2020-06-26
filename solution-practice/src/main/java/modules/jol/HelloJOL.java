package modules.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/7
 */
public class HelloJOL {

    public static void main(String[] args) throws InterruptedException {
        //偏向锁有4s的延迟
        //Thread.sleep(5000);

        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj) {
            // 证明markword里记录了锁信息
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
