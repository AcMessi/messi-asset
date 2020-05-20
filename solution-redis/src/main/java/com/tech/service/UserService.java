package com.tech.service;

import com.tech.model.User;

/**
 * @Description:
 * @Author: messi.chaoqun.wang
 * @Date: 2020/5/20
 */
public interface UserService {

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
