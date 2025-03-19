package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.ClazzMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageResult<Clazz> list(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        for (Clazz c :clazzList) {
            LocalDate endDate = c.getEndDate();
            LocalDate beginDate = c.getBeginDate();
            if(LocalDate.now().isBefore(beginDate)){
                c.setStatus("未开班");
            }else if (endDate.isBefore(LocalDate.now())){
                c.setStatus("已结课");
            }else {c.setStatus("在读中");}
        }
        Page<Clazz> p  = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public List<Clazz> AllClazzInfo() {
        List<Clazz> clazzList =  clazzMapper.AllClazzInfo();
        return clazzList;
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        LocalDate endDate = clazz.getEndDate();
        LocalDate beginDate = clazz.getBeginDate();
        if(LocalDate.now().isBefore(beginDate)){
            clazz.setStatus("未开班");
        }else if (endDate.isBefore(LocalDate.now())){
            clazz.setStatus("已结课");
        }else {clazz.setStatus("在读中");}
        clazz.setMasterName(empMapper.getNameById(clazz.getMasterId()));
        clazzMapper.save(clazz);
    }

    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }

    @Override
    public Clazz info(Integer id) {
        return clazzMapper.info(id);}

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public Integer totalPeople(Integer id) {

        return clazzMapper.getTotal(id);
    }

}
