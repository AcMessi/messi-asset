import org.openjdk.jol.info.ClassLayout;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/7
 */
public class HelloJOL {

    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
