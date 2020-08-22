package com.sinyard.demo.service.impl;

import com.sinyard.demo.dao.TStudentMapper;
import com.sinyard.demo.document.EsStudent;
import com.sinyard.demo.repository.EsStudentRepository;
import com.sinyard.demo.service.EsStudentService;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author sinyard
 * @date 2020-08-22 12:10
 * @desc 学生搜索管理Service实现类
 */
@Service
public class EsStudentServiceImpl implements EsStudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsStudentServiceImpl .class);

    @Autowired
    private TStudentMapper studentMapper;
    @Autowired
    private EsStudentRepository studentRepository;

    @Override
    public int importAll() {
        List<EsStudent> esStudentList = studentMapper.selectAll((EsStudent) null);                       //查询数据出来
        Iterable<EsStudent> esStudentIterable = studentRepository.saveAll(esStudentList);   //保存数据
        Iterator<EsStudent> iterator = esStudentIterable.iterator();                        //提交数据（猜的）
        int result = 0;
        while (iterator.hasNext()) {   //统计数量
            result++;
            iterator.next();
        }
        return result;
    }


    @Override
    public String delete(Integer id) {
        if (studentRepository.existsById(id.longValue())){
            studentRepository.deleteById(id.longValue());
            return "删除成功";
        }
        return "不存在该学生";
    }

    @Override
    public EsStudent create(Integer id) {
        EsStudent result = null;
        List<EsStudent> esStudentList = studentMapper.selectAll(Math.toIntExact(id));
        if (esStudentList.size() > 0) {
            EsStudent esStudent = esStudentList.get(0);
            result = studentRepository.save(esStudent);
        }
        return result;
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsStudent> esStudentList = new ArrayList<>();
            for (Integer id : ids) {
                EsStudent esStudent = new EsStudent();
                esStudent.setStunum(id.toString());
                esStudentList.add(esStudent);
            }
           studentRepository.deleteAll(esStudentList);
        }
    }

    @Override
    public Page<EsStudent> search( String keyword, Integer pageNum,Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return studentRepository.findByStunumOrNameOrDormname(keyword,keyword,keyword,pageable);
    }




}
