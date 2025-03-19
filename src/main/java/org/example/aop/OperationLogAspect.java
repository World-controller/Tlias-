package org.example.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.mapper.EmpMapper;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.Emp;
import org.example.pojo.OperateLog;
import org.example.utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private EmpMapper empMapper;
    @Around("@annotation(org.example.anno.Log)")
    public Object logOperation(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();

        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String methodParams = Arrays.toString(pjp.getArgs());

        Object result = pjp.proceed();

        String returnValue = (result != null? result.toString():"void");
        long end = System.currentTimeMillis();
        long costTime = end - begin;

        Integer currentId = CurrentHolder.getCurrentId();
        Emp e = empMapper.getById(currentId);
        String operateEmpName = e.getName();
        OperateLog operateLog = new OperateLog(null, currentId, LocalDateTime.now(),
                className, methodName, methodParams, returnValue, costTime,operateEmpName);
        log.info("增加操作日志:{}",operateLog);

        operateLogMapper.insert(operateLog);
        return result;
    }

}
