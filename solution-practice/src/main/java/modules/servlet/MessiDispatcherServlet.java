package modules.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/6/27
 */
@Slf4j
public class MessiDispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = -1806124040763216526L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {

        // 1. 加载配置文件
        doLoadConfig();

        // 2.扫描相关的类
        doScanner();

        // 3.实例化相关的类
        doInstance();
        // 4. 完成依赖注入
        doAutowired();

        // 5. 初始化HandlerMapping
        doInitHandlerMapping();

        log.info("Spring framework is initialized");
    }

    private void doInitHandlerMapping() {
    }

    private void doAutowired() {
    }

    private void doInstance() {
    }

    private void doScanner() {
    }

    private void doLoadConfig() {
    }
}
