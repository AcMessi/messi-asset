package modules.object;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/26
 */
public class HelloArray {

    public static void main(String[] args) {
        int[] a = new int[10];

        for (int i = 0; i < 10; i++) {
            a[i] = i;
        }

        List<Integer> list = Arrays.stream(a).boxed().collect(Collectors.toList());
        list.sort(Comparator.comparing(Integer::intValue).reversed());

        System.out.println("stream========================");
        list.stream().forEach(System.out::println);

        System.out.println("parallelStream========================");
        list.parallelStream().forEach(System.out::println);

        System.out.println("parallelStream========================");
        list.parallelStream().forEachOrdered(System.out::println);

        System.out.println("binarySearch========================");
        System.out.println(Arrays.binarySearch(a, 1));
    }
}
