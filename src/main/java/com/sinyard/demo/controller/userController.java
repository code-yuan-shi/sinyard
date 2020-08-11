package com.sinyard.demo.controller;

import com.sinyard.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sinyard
 * @date 2020-08-07 14:07
 * @desc
 */
@Api(tags = "userController",description = "根据ID返回用户")
@Controller
@RequestMapping("/admin")
public class userController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户", notes = "获取用户")
    @RequestMapping("getUser/{id}")
    @ResponseBody
    public String GetUser(@PathVariable int id) {
        return userService.selUser(id).toString();
    }
}

