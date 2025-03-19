package org.example.service;

import org.example.pojo.ClazzTotalOption;
import org.example.pojo.JobOption;

import java.util.List;
import java.util.Map;


public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzTotalOption getStudentCountData();

}
