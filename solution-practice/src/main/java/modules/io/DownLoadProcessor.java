package modules.io;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: dowanload big file using multiple threads
 * @Author: messi.chaoqun.wang
 * @Date: 2020/7/3
 */
@Slf4j
public class DownLoadProcessor {

    // url of source file
    private URL sourceUrl;

    // count of thread
    private int threadCount;

    // connection timeout
    private int timeout = 5000;

    // per size of the buffer
    private int perSize = 50 * 1024 * 1024;

    // total file size
    private int totalSize;

    // target path
    private String targetPath;

    // thread pool executor
    private ExecutorService taskExecutor;

    /**
     * constructor function
     *
     * @param sourceUrl
     * @param targetPath
     * @param timeout
     * @param perSize
     * @throws IOException
     */
    public DownLoadProcessor(String sourceUrl, String targetPath, int timeout, int perSize) throws IOException {
        this.sourceUrl = new URL(sourceUrl);
        this.targetPath = targetPath;

        if (timeout != 0) {
            this.timeout = timeout;
        }

        if (perSize != 0) {
            this.perSize = perSize;
        }

        init();
    }

    /**
     * save a file base on source url
     *
     * @throws IOException
     */
    public static String saveFile(String sourceUrl, String targetUrl, String fileName) throws IOException {
        if (StringUtils.isBlank(fileName)) {
            fileName = sourceUrl.substring(sourceUrl.lastIndexOf('/') + 1);
        }

        log.info("start to download file {}", fileName);

        String uuId = UUID.randomUUID().toString();

        if (!targetUrl.endsWith("/")) {
            targetUrl += "/";
        }

        String path = targetUrl + uuId + "/";

        // create temporary random folder
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filePath = path + fileName;

        DownLoadProcessor processor;
        try {
            processor = new DownLoadProcessor(sourceUrl, filePath, 0, 0);
            processor.download();
        } catch (Exception e) {
            File tempFile = FileUtils.getFile(path);
            FileUtils.deleteDirectory(tempFile);
        }

        log.info("file store path : {}", filePath);

        return filePath;
    }

    public static void main(String[] args) throws IOException {
        DownLoadProcessor.saveFile("http://localhost:8088/test/liferay-dxp-digital-enterprise-tomcat-7.0-sp3-20170503123037723.zip", "C:\\Users\\messi.chaoqun.wang\\Pictures\\temp", StringUtils.EMPTY);
    }

    /**
     * initialization
     *
     * @throws IOException
     */
    private void init() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) sourceUrl.openConnection();
        conn.setConnectTimeout(timeout);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("connection", "keep-alive");
        totalSize = conn.getContentLength();
        threadCount = (int) Math.ceil((float) totalSize / perSize);
        conn.disconnect();

        taskExecutor = Executors.newFixedThreadPool(threadCount);
    }

    public URL getFileUrl() {
        return sourceUrl;
    }

    public int getThreadCount() {
        return this.threadCount;
    }

    /**
     * download stream to file from url
     *
     * @throws InterruptedException
     */
    public void download() throws InterruptedException {
        log.info("file size is {} bytes ({} MB)", totalSize, totalSize / (1024 * 1024));
        log.info("download thread count is {}", threadCount);
        long startTime = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            taskExecutor.execute(() -> {
                int length = 0;
                int start = finalI * perSize;

                RandomAccessFile out = null;
                HttpURLConnection connection = null;
                InputStream in = null;

                try {
                    log.info("Thread {} start to download", Thread.currentThread());

                    out = new RandomAccessFile(targetPath, "rw");

                    // open connection
                    connection = (HttpURLConnection) sourceUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("connection", "keep-alive");
                    connection.setConnectTimeout(timeout);

                    // get input stream
                    in = connection.getInputStream();

                    // download from start point
                    in.skip(start);
                    out.seek(start);

                    byte[] buf = new byte[1024];
                    int hasRead = 0;
                    while (length < perSize && (hasRead = in.read(buf)) != -1) {
                        out.write(buf, 0, hasRead);
                        length += hasRead;
                    }

                    log.info("Thread {} has downloaded {}", Thread.currentThread(), length);
                } catch (IOException e) {
                    log.error(e.getMessage());
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                    }

                    if (connection != null) {
                        connection.disconnect();
                    }

                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                    }
                }

                latch.countDown();
                log.info("Thread {} end to download", Thread.currentThread());
            });
        }

        // wait until all threads end
        latch.await();

        long endTime = System.currentTimeMillis();
        log.info("file download process takes {} ms", (endTime - startTime));
    }

}
