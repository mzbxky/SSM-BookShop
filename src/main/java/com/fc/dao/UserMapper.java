package com.fc.dao;

import com.fc.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int insert(User row);


    User selectByPrimaryKey(Integer uId);

    List<User> findAll();

    int delete(Integer uId);

    int update(User user);

    int updatePassword(@Param("uid") Integer uid, @Param("upwd") String upwd);
}