package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.StuQueryParam;
import org.example.pojo.Student;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StuQueryParam stuQueryParam);

    void save(Student student);

    Student findById(Integer id);

    void delete(List<Integer> ids);

    void update(Student student);

    void vioHandle(Integer id, Integer score);
}
