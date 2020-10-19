package com.itws.service.imp;

import com.itws.dao.UserDao;
import com.itws.pojo.User;
import com.itws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User queryUserByName(String name) {
        return userDao.queryUserByName(name);
    }
}
