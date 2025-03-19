package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.StudentMapper;
import org.example.pojo.PageResult;
import org.example.pojo.StuQueryParam;
import org.example.pojo.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult<Student> page(StuQueryParam stuQueryParam) {
        PageHelper.startPage(stuQueryParam.getPage(),stuQueryParam.getPageSize());
        List<Student> page = studentMapper.page(stuQueryParam);
        Page<Student>  p = (Page<Student>) page;
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

    @Override
    public Student findById(Integer id) {
        Student student =  studentMapper.findById(id);
        return student;
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void vioHandle(Integer id, Integer score) {
        studentMapper.vioHandle(id,score);
    }
}
