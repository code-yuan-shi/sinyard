package com.sinyard.demo.controller;

import com.aliyuncs.utils.StringUtils;
import com.sinyard.demo.common.SendNoteUtil;
import com.sinyard.demo.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sinyard
 * @date 2020-08-07 23:46
 * @desc 会员登录注册管理Controller
 */
@RestController
@Api(tags = "SendNoteController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class SendNoteController {

    @Autowired
    private SendNoteUtil sendNoteUtil;

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private  String REDIS_KEY_PREFIX_AUTH_CODE;

    @ApiOperation("发送验证码短信")
    @RequestMapping(value = "/sendNote", method = RequestMethod.GET)
    public void sendNote(String phone, HttpServletResponse response) {
        String template = "SMS_199196495";  //登录
        try {
            response.getWriter().write(sendNoteUtil.sendNoteMessage(phone, template));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ApiOperation("登录系统")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String phone, String authCode) {
        //验证验证码
        if (!verifyAuthCode(authCode, phone)) {
            return "验证码错误";
        }
        return "验证码成功";
    }

    //对输入的验证码进行校验
    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }

}
