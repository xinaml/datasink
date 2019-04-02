package com.xinaml.datasink.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 1. String	indexName	无	索引库的名称，建议以项目的名称命名
 *    String	type	“”	类型，建议以实体的名称命名
 *    short	shards	5	默认分区数
 *    short	replica	1	每个分区默认的备份数
 *    String	refreshInterval	“1s”	刷新间隔
 *    String	indexStoreType	“fs”	索引文件存储类型
 *
 * 2.主键注解：@Id (相当于Hibernate实体的主键@Id注解)(必写)
 * 只是一个标识，并没有属性。
 *
 * 3.属性注解 @Field (相当于Hibernate实体的@Column注解)
 * @Field默认是可以不加的，默认所有属性都会添加到ES中。加上@Field之后，
 * @document默认把所有字段加上索引失效，只有加@Field 才会被索引(同时也看设置索引的属性是否为no)
 *
 * 4.
 * FieldType	type	FieldType.Auto	自动检测属性的类型
 * FieldIndex	index	FieldIndex.analyzed	默认情况下分词
 * boolean	store	false	默认情况下不存储原文
 * String	searchAnalyzer	“”	指定字段搜索时使用的分词器
 * String	indexAnalyzer	“”	指定字段建立索引时指定的分词器
 * String[]	ignoreFields	{}	如果某个字段需要被忽略
 *
 */
@Document(indexName = "datasink",type = "employee", shards = 1,replicas = 0, refreshInterval = "-1")
public class Employee implements Serializable {
    @Id
    private String id;
    @Field( searchAnalyzer = "ik_smart", analyzer = "ik_smart")
//    @Field(searchAnalyzer = "ik_max_word", analyzer = "ik_smart")
    private String name;
    @Field
    private Integer age = 0;
    @Field
    private String about;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
