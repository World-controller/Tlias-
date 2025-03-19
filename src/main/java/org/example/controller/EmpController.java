package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//员工类
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}",empQueryParam);
        PageResult pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}",ids);
        empService.deleteByIds(ids);
        return Result.success();
    }
    //        查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
            log.info("查询员工id :{}",id);
            Emp emp =  empService.getById(id);
            return Result.success(emp);
    }
    @PutMapping
    public  Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result findAll(){
        List<Emp> empList =  empService.findAll();
        return Result.success(empList);
    }





}
