package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.LoginInfo;
import org.example.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

    void getEmps();

    List<Emp> findAll();

    LoginInfo login(Emp emp);
}
