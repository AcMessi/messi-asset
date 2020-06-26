package modules.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @Description: download big file from network
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/18
 */
public class DownLoadFile_FileUtils {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\messi.chaoqun.wang\\Pictures\\temp\\test.jpg");
        URL url = new URL("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4254150574,1030475924&fm=26&gp=0.jpg");

        FileUtils.copyURLToFile(url, file);
    }
}
