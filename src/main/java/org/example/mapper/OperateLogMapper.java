package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time,operate_emp_Name) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime},#{operateEmpName});")
    void insert(OperateLog log);

    @Select("select *  from operate_log")
    List<OperateLog> selectAll();
}
