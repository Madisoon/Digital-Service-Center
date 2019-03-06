package com.madisoon.cloud.module.system.web;

import com.madisoon.cloud.module.system.service.SystemUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    @ApiOperation(value = "login", notes = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "账号", value = "userAccount", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "密码", value = "userPassword", required = true, dataType = "STRING")
    })
    public ResponseEntity userLogin(@RequestParam("userAccount") String userAccount,
                                    @RequestParam("userPassword") String userPassword, HttpServletRequest request) {
        Map map = systemUserService.userLogin(userAccount, userPassword, request.getSession().getId());
        try {
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("请求异常");
        }
    }
}
