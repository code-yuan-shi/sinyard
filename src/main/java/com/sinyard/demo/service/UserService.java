package com.sinyard.demo.service;

import com.sinyard.demo.entity.Admin;
import com.sinyard.demo.dao.UserMapper;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sinyard
 * @date 2020-08-07 11:47
 * @desc
 */
@Service("UserService")
public class UserService {
    @Autowired
    UserMapper userMapper;

    @ApiModelProperty(value = "查找用户")
    public Admin selUser(int id) {
        return userMapper.selUser(id);
    }
}
