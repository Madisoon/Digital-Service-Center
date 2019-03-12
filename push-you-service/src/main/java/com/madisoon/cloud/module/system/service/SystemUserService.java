package com.madisoon.cloud.module.system.service;

import com.madisoon.cloud.entity.SysUser;
import com.madisoon.cloud.mapper.SysUserMapper;
import com.madisoon.starter.jwt.JwtService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(SystemUserService.class);

    private final JwtService jwtService;

    private final StringRedisTemplate stringRedisTemplate;

    private final SysUserMapper sysUserMapper;

    @Autowired
    public SystemUserService(JwtService jwtService, StringRedisTemplate stringRedisTemplate, SysUserMapper sysUserMapper) {
        this.jwtService = jwtService;
        this.stringRedisTemplate = stringRedisTemplate;
        this.sysUserMapper = sysUserMapper;
    }

    public Map userLogin(String account, String password, String sessionId) {

        SysUser sysUser = sysUserMapper.getUserByAccount(account);
        Map<String, Object> result = new HashMap<>(16);

        if (password.equals(sysUser.getUserPassword())) {
            LOGGER.info("{}登陆成功", account);
            // 登陆成功
            Map<String, Object> map = new HashMap<>(16);
            map.put("userInformation", sysUser);
            String audience = "Push You";
            long time = 14L;
/*            // 生成token
            String userToken = jwtService.createPersonToken(map, audience, time);
            // 将session和token放入到redis中
            stringRedisTemplate.opsForValue().set(sessionId, userToken, time, TimeUnit.DAYS);*/

            sysUser.setUserPassword("");
            result.put("status", 1);
            result.put("data", sysUser);
        } else {
            // 登陆失败
            LOGGER.info("{}登陆成功", account);
            result.put("status", 0);
        }
        return result;
    }
}
