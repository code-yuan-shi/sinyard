package com.sinyard.demo.dao;

import com.sinyard.demo.document.EsStudent;
import com.sinyard.demo.entity.TStudent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TStudentMapper {
    int deleteByPrimaryKey(Integer studentid);

    int insert(TStudent record);

    TStudent selectByPrimaryKey(Integer studentid);

    List<EsStudent> selectAll(EsStudent student);
    List<EsStudent> selectAll(Integer studentid);

    int updateByPrimaryKey(TStudent record);
}