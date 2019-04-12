package com.xinaml.datasink.easticsearch.dao;

import com.xinaml.datasink.easticsearch.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeRep  extends ElasticsearchRepository<Employee,String> {
    /**
     * 查询雇员信息
     * @param id
     * @return
     */
    Employee queryEmployeeById(String id);

}
