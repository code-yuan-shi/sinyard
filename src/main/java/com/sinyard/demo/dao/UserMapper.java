package com.sinyard.demo.dao;

import com.sinyard.demo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author sinyard
 * @date 2020-08-07 14:08
 * @desc
 */
@Repository
@Mapper
public interface UserMapper {
    Admin selUser(int id);
}

