package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.EmpExpr;

import java.util.List;

//员工经历
@Mapper
public interface EmpExprMapper {

    void insert(List<EmpExpr> exprList);

    void delByEmpId(List<Integer> ids);

}
