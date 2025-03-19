package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> list(ClazzQueryParam clazzQueryParam);

    List<Clazz> AllClazzInfo();

    void save(Clazz clazz);

    void delete(Integer id);

    Clazz info(Integer id);

    void update(Clazz clazz);

    Integer totalPeople(Integer id);
}
