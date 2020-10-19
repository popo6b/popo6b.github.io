package com.itws.dao;

import com.itws.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    User queryUserByName(String name);
    User queryUserByUserId(Long id);
}
