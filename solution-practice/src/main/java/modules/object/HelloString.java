package modules.object;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/27
 */
@Data
@Slf4j
public class HelloString implements Cloneable {

    private String name;
    private String number;

    public static void main(String[] args) throws CloneNotSupportedException {
        String a = "hello";
        String b = a + "world";
        log.info(b);

        HelloString test1 = new HelloString();
        test1.setName("123");
        test1.setNumber("456");

        HelloString test2 = test1;
        HelloString test3 = (HelloString) test1.clone();

        // 内存地址比较
        System.out.println(test1 == test2);
        System.out.println(test1.hashCode() == test2.hashCode());
        System.out.println(test1 == test3);
        System.out.println(test2 == test3);

        test2.setName("1233");
        test2.setNumber("4565");

        log.info(test1.getName() + ", " + test1.getNumber());
        log.info(test2.getName() + ", " + test2.getNumber());

        String temp1 = "hello test";
        String temp2 = "hello test";
        log.info("temp1 == temp2 {}", temp1 == temp2);
    }

}
