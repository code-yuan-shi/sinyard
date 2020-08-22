package com.sinyard.demo.repository;

import com.sinyard.demo.document.EsStudent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author sinyard
 * @date 2020-08-21 23:29
 * @desc 学生ES操作类
 */
public interface EsStudentRepository extends ElasticsearchRepository<EsStudent, Long> {
    /**
     * 搜索查询
     * @param stunum            学号
     * @param name              姓名
     * @param dormname          宿舍号
     * @return
     */
    Page<EsStudent> findByStunumOrNameOrDormname(String stunum, String name, String dormname, Pageable page);

}
