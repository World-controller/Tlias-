package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;
import org.example.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<OperateLog> logList =  operateLogMapper.selectAll();
        Page<OperateLog> p = (Page<OperateLog>) logList;
        return new PageResult<OperateLog>(p.getTotal(),p.getResult());
    }
}
