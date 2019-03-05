package com.madisoon.cloud.module.system.service;

import com.madisoon.starter.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * 系统用户控制层
 *
 * @author Msater Zg
 * @version 1.0
 * @date 2019/3/5 1:50 PM
 */

@Service
public class SystemUserService {

    private final JwtService jwtService;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public SystemUserService(JwtService jwtService, StringRedisTemplate stringRedisTemplate) {
        this.jwtService = jwtService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void userLogin() {
        Map map = new HashMap(16);
        String audience = "";
        long time = 50L;
        String userToken = jwtService.createPersonToken(map, audience, time);
        stringRedisTemplate.opsForValue().set("", "", 14L, TimeUnit.DAYS);
    }
}
