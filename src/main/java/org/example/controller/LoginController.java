package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.LoginInfo;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController//=@ResponseBody+@Controller
// @ResponseBody作用是将方法的返回值直接写入到response的body中，返回值必须是json格式，不进行数据跳转，直接进行数据响应
// @Controller注解是将类交给spring管理，使其成为一个控制器。
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result loginInfo(@RequestBody Emp emp){
        log.info("登录页面：用户{}",emp);
        LoginInfo loginInfo = empService.login(emp);
        if (loginInfo != null){
            return Result.success(loginInfo);
        }
        return Result.error("用户名或者密码不正确！");
    }
}
