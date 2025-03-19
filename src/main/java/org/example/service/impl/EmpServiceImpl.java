package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult page(EmpQueryParam empQueryParam){
//        1.获取页码和每页大小(前端传来的参数)                 分页参数通过线程绑定的方式隐式传递，不需要显式传入mapper方法
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
//        2.获取到员工的信息列表
        List<Emp> empList = empMapper.list(empQueryParam);

//        System.out.println("empList的运行类型为："+empList.getClass());                    // class com.github.pagehelper.Page
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(),p.getResult());

    }

    @Transactional(rollbackFor = {Exception.class})//事务控制，为了保证数据库的一致性和安全性   适用于多次对数据库进行操作的方法，类，接口
    //默认出现运行时异常才会回滚
    @Override
    public void save(Emp emp) {//员工新增功能实现

//        1.插入员工基本信息表
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            List<EmpExpr> exprList = emp.getExprList();
//        2.插入员工工作经历表
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr-> empExpr.setEmpId(emp.getId()));
                empExprMapper.insert(exprList);
            }
        } finally {
            //        3.无论插入成功与否，都需要记录到日志中（使用事务的传播技术实现）
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工:"+emp.toString());
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteByIds(List<Integer> ids) {
//        1.删除员工基本信息
            empMapper.delById(ids);
//        2.删除员工工作经历
            empExprMapper.delByEmpId(ids);

    }

    @Override
    public Emp getById(Integer id) {
        Emp emp =  empMapper.getById(id);
        return emp;
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
//        1.根据员工id修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
//                2.修改员工的工作经历信息
//                2.1 先根据员工id删除原有的工作经历
        empExprMapper.delByEmpId(Arrays.asList(emp.getId()));
//                2.2再添加这个员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insert(exprList);
        }
    }

    @Override
    public void getEmps() {
        empMapper.getEmps();
    }

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        log.info("传入的员工：{}",emp);
        Emp e =  empMapper.selectByUsernameAndPassword(emp);

        if(e != null){
            log.info("登陆成功 员工信息：{}",e);
    //        完成需求：登录完成后，调用工具类生成JWT令牌，并返回
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());//必须要存储的
            claims.put("username",e.getUsername());//可存可不存
            claims.put("name",e.getName());//可存可不存
            String s = JwtUtils.generateJwt(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(), s);
        }
        return null;
    }
}
