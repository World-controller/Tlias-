package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.*;
import org.example.service.ClazzService;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

        @Autowired
        private ClazzService clazzService;
        @Autowired
        private EmpService empService;
    /*
    * 条件分页查询接口完成
    * */
    @GetMapping
    public Result list(ClazzQueryParam clazzQueryParam){
        log.info("Clazz的条件分页查询");
        PageResult<Clazz> clazzPageResult =clazzService.list(clazzQueryParam);
        return Result.success(clazzPageResult);
    }
    /*
    * 查询所有班级接口完成
    * */
    @GetMapping("/list")
    public  Result  AllClazzInfo(){
        log.info("clazz的查询所有班级");
        List<Clazz> clazzList = clazzService.AllClazzInfo();
        return Result.success(clazzList);
    }
    /*
    * 查询所有员工接口(已完成)参考EmpController
    * */

    /*
    * 新增班级信息接口
    * */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("新增班级信息接口");
        clazzService.save(clazz);
        return Result.success();
    }

    /*
    查询回显
    */
    @GetMapping("/{id}")
    public Result info(@PathVariable Integer id){
        log.info("根据id:{}显示班级信息",id);
        Clazz clazz =  clazzService.info(id);
        return Result.success(clazz);
    }

    /*
    * 更改班级信息
    * */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("更改班级信息:{}",clazz);
         clazzService.update(clazz);
         return Result.success();
    }


/*
* 删除班级信息接口
* */
    @Transactional(rollbackFor = {Exception.class})
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("删除班级信息:{}",id);
        Integer total = clazzService.totalPeople(id);
        if ( total != 0){
            throw new ClazzTotalException("对不起, 该班级下有学生, 不能直接删除");
        }
        clazzService.delete(id);
        return Result.success();
    }













}
