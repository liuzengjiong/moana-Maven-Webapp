package org.moana.services.impl;


import org.moana.bean.User;
import org.moana.dao.UserMapper;
import org.moana.services.LoginService;
import org.moana.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param account
     *         账号
     * @param password
     *         密码
     * @return 用户标识字符串
     */
    @Override
    public String login(String account, String password) {
        User user = userMapper.selectByPrimaryKey(account);
        if (null != user) {
            // 检验密码
            if (user.getPassword().equals(password)) {
                return MD5Util.getMD5(user);
            }
        }
        return "";
    }
}
