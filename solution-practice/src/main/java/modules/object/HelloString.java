package modules.object;

import lombok.Data;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/27
 */
@Data
public class HelloString implements Cloneable {

    private String name;
    private String number;

    public static void main(String[] args) throws CloneNotSupportedException {
        String a = "hello";
        String b = a + "world";
        System.out.println(b);

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

        System.out.println(test1.getName() + ", " + test1.getNumber());
        System.out.println(test2.getName() + ", " + test2.getNumber());
        System.out.println();
    }

}
