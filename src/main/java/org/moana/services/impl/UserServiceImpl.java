package org.moana.services.impl;


import org.moana.bean.User;
import org.moana.dao.UserMapper;
import org.moana.services.UserService;
import org.moana.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户
     */
    @Override
    public User getUser(String account) {
        User user = userMapper.selectByPrimaryKey(account);
        return user;
    }
}
