package com.xinaml.datasink.act;

import com.xinaml.datasink.dao.EmployeeRep;
import com.xinaml.datasink.entity.Employee;
import com.xinaml.datasink.service.EmployeeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("es")
public class EmployeeAct {
    @Autowired
    private EmployeeRep employeeRep;
    @Autowired
    private EmployeeSer employeeSer;

    /**
     * 添加
     *
     * @return
     */
    @RequestMapping("add")
    public String add() {
        Employee employee = new Employee();
        employee.setId("2");
        employee.setAge(26);
        employee.setAbout("你是谁阿");
        employeeRep.save(employee);
        return "success";
    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping("delete")
    public String delete() {
        Employee employee = employeeRep.queryEmployeeById("1");
        employeeRep.delete(employee);
        return "success";
    }

    /**
     * 局部更新
     *
     * @return
     */
    @RequestMapping("update")
    public String update() {
        Employee employee = employeeRep.queryEmployeeById("1");
        employeeRep.save(employee);
        return "success";
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping("query")
    public Employee query() {
        Employee accountInfo = employeeRep.queryEmployeeById("1");
        return accountInfo;
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping("list")
    public List<Employee> list() {
        List<Employee> list = employeeRep.findAll(PageRequest.of(0, 100)).getContent();
        return list;
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping("query/ik")
    public List<Employee> queryIk() {
        List<Employee> employees = employeeSer.search("about", "你是谁");
        return employees;
    }


}
