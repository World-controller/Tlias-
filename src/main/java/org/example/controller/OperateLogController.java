package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;
    @GetMapping("/log/page")
    public Result page(@RequestParam(name = "page", defaultValue = "1") Integer  page, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        log.info("传入页码：{},单页大小:{}",page,pageSize);
        PageResult<OperateLog> logPageResult = operateLogService.page(page,pageSize);
        return Result.success(logPageResult);
    }

}
