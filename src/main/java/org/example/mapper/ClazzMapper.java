package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    List<Clazz> AllClazzInfo();

    void save(Clazz clazz);

    void delete(Integer id);

    Clazz info(Integer id);

    void update(Clazz clazz);

    Integer getTotal(Integer id);
}
