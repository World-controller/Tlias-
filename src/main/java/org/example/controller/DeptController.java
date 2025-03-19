package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@SuppressWarnings({"all"})
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result delete(Integer id) {
//            System.out.println("根据id删除部门"+id);
        log.info("根据id删除部门:{}", id);
        deptService.delete(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success();
    }

    //        查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    //        修改数据
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
//            System.out.println("修改部门，dept=" + dept);
        log.info("修改部门，dept={}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
