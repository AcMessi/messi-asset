package com.tech.druid.controller;

import com.tech.druid.util.ApiResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/20
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("api/test/")
@Slf4j
public class TestController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/getDataSource")
    public ApiResult selectByPrimaryKey() {
        log.info("数据源：" + dataSource.getClass());
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement prepareStatement = connection
                    .prepareStatement("select * from user");
            ResultSet resultSet = prepareStatement.executeQuery();

            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("user_name", resultSet.getString("user_name"));
                item.put("user_status", resultSet.getString("user_status"));
                list.add(item);
            }

            if (list.isEmpty()) {
                return ApiResult.failure("no users found");
            }

            return ApiResult.success(list);
        } catch (SQLException e) {
            return ApiResult.failure(e.getMessage());
        }
    }

}
