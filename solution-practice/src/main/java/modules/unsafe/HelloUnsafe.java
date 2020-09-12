package modules.unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/19
 */
@Slf4j
public class HelloUnsafe {

    public static void main(String[] args) throws InstantiationException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        Integer i = (Integer) unsafe.allocateInstance(Class.forName("java.lang.Integer"));

        log.info("i {}", i);
    }
}
