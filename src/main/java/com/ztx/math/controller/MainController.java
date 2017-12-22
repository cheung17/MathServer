package com.ztx.math.controller;

import com.ztx.math.model.UserEntity;
import com.ztx.math.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ztx
 * @Controller 表明Controller类
 * @RequestMapping 定义一个请求的映射
 */
//表明该类为处理请求的Controller的类
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    // 首页  //定义一个请求的映射
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public UserEntity test() {

        return userService.findUserById(1);
    }


}