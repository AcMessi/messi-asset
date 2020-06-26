package modules.collection;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/25
 */
public class HelloParallelStream {

    public static void main(String[] args) {
        List<HelloStream.T> list = new ArrayList<HelloStream.T>();

        HelloStream.T t1 = HelloStream.T.saveT(1, "cccccccc");
        HelloStream.T t2 = HelloStream.T.saveT(2, "bbbbbbbb");
        HelloStream.T t3 = HelloStream.T.saveT(3, "ffffff");
        HelloStream.T t4 = HelloStream.T.saveT(1, "aaaaaa");
        HelloStream.T t5 = HelloStream.T.saveT(1, "aaabbb");

        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);

        for (int i = 0; i < 100000; i++) {
            HelloStream.T t = HelloStream.T.saveT(1, "aaaaaa");
            list.add(t);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {

                    CountDownLatch latch = new CountDownLatch(1);
                    long startTime = System.currentTimeMillis();

                    list.parallelStream().filter(a -> a.getId() == 1)
                            .sorted(Comparator.comparing(HelloStream.T::getName)).collect(Collectors.toList());

                    latch.countDown();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    long endTime = System.currentTimeMillis();    //获取结束时间
                    System.out.println("parallelStream运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
                }
        );

        executorService.execute(() -> {

                    CountDownLatch latch = new CountDownLatch(1);
                    long startTime = System.currentTimeMillis();

                    list.stream().filter(a -> a.getId() == 1)
                            .sorted(Comparator.comparing(HelloStream.T::getName)).collect(Collectors.toList());

                    latch.countDown();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    long endTime = System.currentTimeMillis();    //获取结束时间
                    System.out.println("stream运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
                }
        );
    }

    @Data
    static class T {
        int id;
        String name;

        public static HelloStream.T saveT(int id, String name) {
            HelloStream.T t = new HelloStream.T();
            t.setId(id);
            t.setName(name);
            return t;
        }
    }
}
