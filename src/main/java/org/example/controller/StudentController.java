package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.StuQueryParam;
import org.example.pojo.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    /*
    * 学生列表查询
    * */
    @GetMapping
    public Result page(StuQueryParam stuQueryParam){
        log.info("分页查询：{}",stuQueryParam);
        PageResult<Student> studentPageResult =  studentService.page(stuQueryParam);
        return Result.success(studentPageResult);
    }
    /*
    * 根据学员ID查询学生信息(查询回显)
    * */
    @GetMapping("{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据学员ID:{}查询学生信息(查询回显)",id);
        Student student =  studentService.findById(id);
        return Result.success(student);
    }

    /*
    * 学生新增
    * */
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("学生新增:{}",student);
        studentService.save(student);
        return Result.success();
    }

    /*
    * 删除学生信息
    * */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除学生，id分别为:{}",ids);
        studentService.delete(ids);
        return Result.success();
    }
    /*
    * 更改学生信息
    * */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新员工信息:{}",student);
        studentService.update(student);
        return Result.success();
    }

    /*
    * 学生违纪处理
    * */
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id,@PathVariable Integer score ){
        log.info("学生:{}违纪处理,扣除分数:{}",id,score);
        studentService.vioHandle(id,score);
        return Result.success();
    }


}
