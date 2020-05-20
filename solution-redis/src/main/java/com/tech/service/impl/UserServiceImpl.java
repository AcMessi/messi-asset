package com.tech.service.impl;

import com.tech.dao.UserMapper;
import com.tech.model.User;
import com.tech.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/20
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        long start = System.currentTimeMillis();

        StringBuilder sf = new StringBuilder("asset:user:");
        sf.append(userId);
        //从redis中获得key名为user的数据
        User user = (User) redisTemplate.opsForValue().get(sf.toString());
        //判断是否获得
        if (user == null) {
            //如果为空，数据库查询
            user = userMapper.selectByPrimaryKey(userId);
            //再次赋值给redis中，key名为user
            redisTemplate.opsForValue().set(sf.toString(), user, 10, TimeUnit.MINUTES);
        }

        log.info("it takes " + (System.currentTimeMillis() - start) + "ms");
        return user;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
