package com.sinyard.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sinyard
 * @date 2020-08-06 16:51
 * @desc
 */
@Api(tags = "HelloWorldController", description = "Swagger2测试")
@EnableAutoConfiguration
@Controller
@RequestMapping("/test")
public class HelloWorldController {
    @ApiOperation("对世界说hello world")
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam int a) {
        return "hello world";
    }

}
