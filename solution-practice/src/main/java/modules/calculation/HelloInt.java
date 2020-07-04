package modules.calculation;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/27
 */
public class HelloInt {

    public static void main(String[] args) {
        int i = 1, j = 1;
        int k = i++;
        int m = ++j;
        System.out.println("k: " + k);
        System.out.println("m: " + m);

        int a = 1;
        a = a++;
        int b = a++;
        int c = a + ++a * a++;
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);

    }

}
