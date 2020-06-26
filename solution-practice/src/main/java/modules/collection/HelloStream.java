package modules.collection;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/24
 */
public class HelloStream {

    public static void main(String[] args) {
        HelloStream helloStream = new HelloStream();
        System.out.println(helloStream.test());
    }

    public List test() {
        List<T> list = new ArrayList<T>();

        T t1 = T.saveT(1, "cccccccc");
        T t2 = T.saveT(2, "bbbbbbbb");
        T t3 = T.saveT(3, "ffffff");
        T t4 = T.saveT(1, "aaaaaa");
        T t5 = T.saveT(1, "aaabbb");

        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);

        return (List) list.stream().filter(a -> a.getId() == 1)
                .sorted(Comparator.comparing(T::getName)).collect(Collectors.toList());
    }

    @Data
    static class T {
        int id;
        String name;

        public static T saveT(int id, String name) {
            T t = new T();
            t.setId(id);
            t.setName(name);
            return t;
        }
    }
}
