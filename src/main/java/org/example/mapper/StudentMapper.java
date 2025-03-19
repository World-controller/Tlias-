package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.StuQueryParam;
import org.example.pojo.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> page(StuQueryParam stuQueryParam);

    void add(Student student);

    Student findById(Integer id);

    void delete(List<Integer> ids);

    void update(Student student);

    void vioHandle(Integer id, Integer score);

    List<Map<String, Object>> countStudentDegreeData();
 /*
 * 数据库查询结果示例：

| clazz     | data |
|-----------|------|
| 班级A     | 15   |
| 班级B     | 9    |
 * */
    /*
    * MyBatis转换后的Java结构：

List<Map<String, Object>> resultList = [
    { "clazz" : "班级A", "data" : 15 },
    { "clazz" : "班级B", "data" : 9 }
]*/
    List<Map<Object, Object>> countStudentCountData();
}
