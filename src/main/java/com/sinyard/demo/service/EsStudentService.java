package com.sinyard.demo.service;

import com.sinyard.demo.document.EsStudent;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author sinyard
 * @date 2020-08-22 0:08
 * @desc 学生搜索管理Service
 */
public interface EsStudentService {
    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除学生
     */
    String delete(Integer id);

    /**
     * 根据id创建商品
     */
    EsStudent create(Integer id);

    /**
     * 批量删除商品
     */
    void delete(List<Integer> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsStudent> search(String keyword, Integer pageNum, Integer pageSize);

}
