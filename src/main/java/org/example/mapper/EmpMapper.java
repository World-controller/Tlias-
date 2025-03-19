package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

        /*
        * 底层实现
        * @Select("select count(*) from emp left join dept on dept.id = emp.dept_id ")
        public Long total();

        @Select("select emp.*,dept.name deptName from emp left join dept on dept.id = emp.dept_id order by emp.update_time desc limit #{page},#{pageSize} ")
        public List<Emp> rows(Integer page,Integer pageSize);
        * */
        /*基于PageHelper实现*/
//        @Select("select emp.*,dept.name deptName from emp left join dept on dept.id = emp.dept_id order by emp.update_time desc")

        List<Emp> list(EmpQueryParam empQueryParam);

        void insert(Emp emp);

    void delById(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    List<Map<Object,Object>> countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();

    void getEmps();

    String getNameById(Integer masterId);

    List<Emp> findAll();

    Emp selectByUsernameAndPassword(Emp emp);
}
