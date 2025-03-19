package org.example.service;

import org.example.pojo.Dept;

import java.util.List;

public interface DeptService {

    public List<Dept> findAll();

    public void delete(Integer id);

    public void add(Dept dept);

    public Dept findById(Integer id);

    public void update(Dept dept);

}
