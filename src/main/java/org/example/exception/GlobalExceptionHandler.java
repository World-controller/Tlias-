package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.ClazzTotalException;
import org.example.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
/*异常处理器配置xml文件（Spring）   下面的value属性配置了异常类型和对应的错误页面的映射关系
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <property name="exceptionMappings">
        <map>
            <entry key="org.springframework.dao.DuplicateKeyException" value="/error/duplicateKeyException"/>
            <entry key="java.lang.MyException" value="/error/exception"/>
        </map>
     </property>
</bean>

// 异常处理器配置类（Spring Boot）
* */

/*
*  全局异常处理器
* 1.- 定义全局异常处理器非常简单，就是定义一个类，在类上加上一个注解@RestControllerAdvice，
* 加上这个注解就代表我们定义了一个全局异常处理器。
- 2.在全局异常处理器当中，需要定义一个方法来捕获异常，在这个方法上需要加上注解@ExceptionHandler。
* 通过@ExceptionHandler注解当中的value属性来指定我们要捕获的是哪一类型的异常
* */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.info("出错啦",e);
        return  Result.error("出错啦，请联系管理员！");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.info("出现重复键错误");
        String string = e.toString();
        int i = string.lastIndexOf("Duplicate entry");
        String substring = string.substring(i);
        String[] split = substring.split(" ");
        return Result.error(split[2] + "已存在");
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("参数 '%s' 类型错误，需要 %s 类型",
                ex.getName(), ex.getRequiredType().getSimpleName());
        return ResponseEntity.badRequest().body(message);
    }
    @ExceptionHandler(ClazzTotalException.class)
    public Result handleClazzTotal() {
        String message = "对不起, 该班级下有学生, 不能直接删除";
        return Result.error(message);
    }

}
