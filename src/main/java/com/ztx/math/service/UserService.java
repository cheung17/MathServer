package com.ztx.math.service;

import com.ztx.math.dao.UserDao;
import com.ztx.math.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Repository用于标注数据访问组件，即DAO组件
 * Created by kun on 2016/5/5.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserEntity findUserById(int id) {
        return userDao.getUserById(id);
    }
}
