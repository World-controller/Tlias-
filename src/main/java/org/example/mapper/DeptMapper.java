package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select id,name,create_time,update_time from dept order by update_time desc")
    public List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    public void delete(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    public void add(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id = #{id}")
    public Dept findById(Integer id);

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    public void update(Dept dept);
}
