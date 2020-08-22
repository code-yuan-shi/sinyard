package com.sinyard.demo.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author sinyard
 * @date 2020-08-21 22:46
 * @desc 搜索学生信息测试
 */
//        //索引库名次，mysql中数据库的概念
//	      String indexName();
//        //文档类型，mysql中表的概念
//        String type() default "";
//        //默认分片数
//        short shards() default 5;
//        //默认副本数量
//        short replicas() default 1;
@Document(indexName = "sms", type = "t_student", shards = 1, replicas = 0)
@Data
public class EsStudent implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private Integer studentid;

    @Field(type = FieldType.Keyword)
    private String stunum;

    private String password;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;

    private Integer dormbuildid;

    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String dormname;

    private String sex;

    private String tel;
}
