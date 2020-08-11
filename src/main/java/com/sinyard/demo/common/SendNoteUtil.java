package com.sinyard.demo.common;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sinyard.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author sinyard
 * @date 2020-08-10 13:58
 * @desc
 */
@Component
public class SendNoteUtil {
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GB6zwcKgdmoTjTaYYnw", "NNZiu6KsltM3GZbo1JaVpQB1K6utNP");
    IAcsClient client = new DefaultAcsClient(profile);

    //这一步的两个参数,一个是要发送验证码的手机号 一个是模板Code用来区分登录注册
    public String sendNoteMessage(String PhoneNumbers, String template) {
        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        request.putQueryParameter("SignName", "ABC商城");
        request.putQueryParameter("TemplateCode", template);
        request.putQueryParameter("TemplateParam", "{code:"+ stringBuilder.toString()+"}");   //要加转义字符，不然会报错
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + PhoneNumbers, stringBuilder.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + PhoneNumbers,AUTH_CODE_EXPIRE_SECONDS);
        try {
            CommonResponse response = client.getCommonResponse(request);
            String returnStr = response.getData();
            System.out.println(returnStr);
            JSONObject jsonObject = JSONObject.parseObject(returnStr);
            //返回的信息
            return jsonObject.getString("Message");
        } catch (ServerException e) {
            return e.getErrMsg();
        } catch (ClientException e) {
            return e.getErrMsg();
        }
    };
}



