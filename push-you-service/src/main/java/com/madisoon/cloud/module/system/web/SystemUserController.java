package com.madisoon.cloud.module.system.web;

import com.madisoon.cloud.module.system.service.SystemUserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * 系统用户控制层
 *
 * @author Msater Zg
 * @version 1.0
 * @date 2019/3/5 1:50 PM
 */

@RestController
public class SystemUserController {
    private final SystemUserService systemUserService;

    @Autowired
    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    /**
    * @author zg
    * Description: 验证用户，同时需要讲用户信息返回到前台，同时需要将数据放入到redis中
    * @date 1:53 PM 2019/3/5
    **/
    @PostMapping("login")
    public void userLogin(@RequestBody JSONObject jsonObject) {

    }
}
