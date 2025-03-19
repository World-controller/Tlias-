package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.ClazzTotalOption;
import org.example.pojo.JobOption;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<Object,Object>> maps = empMapper.countEmpJobData();
        List<Object> pos = maps.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> num = maps.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(pos,num);

    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        List<Map<String,Object>> maps =   empMapper.countEmpGenderData();
        return maps;
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String,Object>> maps = studentMapper.countStudentDegreeData();
        return maps;
    }

    @Override
    public ClazzTotalOption getStudentCountData() {
        List<Map<Object ,Object>> maps = studentMapper.countStudentCountData();
        List<Object> name = maps.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> value = maps.stream().map(dataMap -> dataMap.get("data")).toList();
        return new ClazzTotalOption(name,value) ;
    }
}
