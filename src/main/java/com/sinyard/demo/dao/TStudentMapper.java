package com.sinyard.demo.dao;

import com.sinyard.demo.entity.TStudent;
import java.util.List;

public interface TStudentMapper {
    int deleteByPrimaryKey(Integer studentid);

    int insert(TStudent record);

    TStudent selectByPrimaryKey(Integer studentid);

    List<TStudent> selectAll();

    int updateByPrimaryKey(TStudent record);
}