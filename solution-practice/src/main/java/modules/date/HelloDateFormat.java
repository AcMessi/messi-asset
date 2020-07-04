package modules.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 1. Y表示月年
 * 2. y表示正常年
 * 3. M表示分
 * 4. m表示月
 * 5. DD表示年日
 * 6. dd表示月日
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/28
 */
public class HelloDateFormat {

    private static String DATE_FORMAT_1 = "YYYY-MM-DD";

    private static String DATE_FORMAT_2 = "YYYY-MM-dd";

    private static String DATE_FORMAT_3 = "YYYY-mm-dd";

    private static String DATE_FORMAT_4 = "yyyy-MM-DD";

    private static String DATE_FORMAT_5 = "yyyy-MM-dd";

    private static String DATE_FORMAT_6 = "yyyy-mm-dd";

    public static void main(String[] args) {
        Date date = new Date();

        printDate(date, DATE_FORMAT_1);
        printDate(date, DATE_FORMAT_2);
        printDate(date, DATE_FORMAT_3);
        printDate(date, DATE_FORMAT_4);
        printDate(date, DATE_FORMAT_5);
        printDate(date, DATE_FORMAT_6);
    }

    private static void printDate(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        System.out.println(sdf.format(date));
    }
}
