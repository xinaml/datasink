package com.xinaml.datasink.service;

import com.xinaml.datasink.dao.EmployeeRep;
import com.xinaml.datasink.entity.Employee;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSer {
    @Autowired
    private EmployeeRep employeeRep;

    public List<Employee> search(String name, String content) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(structureQuery(name,content))
                .build();
        List<Employee> list = employeeRep.search(searchQuery).getContent();
        return list;
    }

    public DisMaxQueryBuilder structureQuery(String name,String content) {
        //使用dis_max直接取多个query中，分数最高的那一个query的分数即可
        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
        //boost 设置权重,只搜索匹配name字段
        QueryBuilder ikNameQuery = QueryBuilders.matchQuery(name, content).boost(2f);
        disMaxQueryBuilder.add(ikNameQuery);
        return disMaxQueryBuilder;
    }
}
