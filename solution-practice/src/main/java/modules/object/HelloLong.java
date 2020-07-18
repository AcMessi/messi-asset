package modules.object;

/**
 * @Description: LongCache
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/6
 */
public class HelloLong {

    public static void main(String[] args) {
        Long t1 = 1l;
        Long t2 = 1l;
        Long t3 = new Long(1);

        System.out.println(t1 == t2);
        System.out.println(t1 == t3);
        System.out.println(t1.equals(t3));

    }
}
