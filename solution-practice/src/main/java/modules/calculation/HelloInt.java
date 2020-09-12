package modules.calculation;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/27
 */
public class HelloInt {

    public static void main(String[] args) {
        int z = 1;
        int z1 = z++;
    }

//    Code:
//       0: iconst_1 将int型(1)推送至栈顶
//       1: istore_1 将栈顶int型数值存入第二个本地变量
//       2: iload_1 将第二个int型本地变量推送至栈顶
//       3: iinc  1, 1 计算
//       6: istore_2 将栈顶int型数值存入第三个本地变量
//       7: return

}
