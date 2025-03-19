package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.ClazzTotalOption;
import org.example.pojo.JobOption;
import org.example.pojo.Result;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService  reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
            log.info("统计员工人数柱状图");
            JobOption jobOption =  reportService.getEmpJobData();
            return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别及其人数");
        List<Map<String,Object>> genderOption =  reportService.getEmpGenderData();
        return Result.success(genderOption);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData (){
        log.info("统计各班级中学员的人数");
        ClazzTotalOption clazzTotalOption  =  reportService.getStudentCountData();
        return  Result.success(clazzTotalOption);
    }
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员学历信息及其人数");
        List<Map<String,Object>> degreeOption =  reportService.getStudentDegreeData();
        return Result.success(degreeOption);
    }
}
