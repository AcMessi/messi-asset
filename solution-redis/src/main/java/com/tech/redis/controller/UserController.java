package com.tech.redis.controller;

import com.tech.redis.model.User;
import com.tech.redis.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/20
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("api/user/")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @DeleteMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Long userId) {
        return userService.deleteByPrimaryKey(userId);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody User record) {
        return userService.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody User record) {
        return userService.insertSelective(record);
    }

    @GetMapping("/selectByPrimaryKey")
    @ResponseBody
    public User selectByPrimaryKey(Long userId) {
        return userService.selectByPrimaryKey(userId);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody User record) {
        return userService.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody User record) {
        return userService.updateByPrimaryKey(record);
    }
}
