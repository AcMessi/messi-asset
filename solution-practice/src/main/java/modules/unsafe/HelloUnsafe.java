package modules.unsafe;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/19
 */
@Slf4j
public class HelloUnsafe {

    public static void main(String[] args) throws InstantiationException {
//        AtomicInteger atomicInteger = (AtomicInteger) Unsafe.getUnsafe().allocateInstance(HelloUnsafe.class);
//        atomicInteger.set(1);
//        log.info("atomicInteger : {}", atomicInteger);
    }
}
