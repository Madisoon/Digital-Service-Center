package com.madisoon.cloud.module.system.web;

import com.madisoon.cloud.module.system.service.SystemTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Description:
 * 系统标签控制层
 *
 * @author Msater Zg
 * @version 1.0
 * @ClassName SystemTagController
 * @date 2019/3/6 2:54 PM
 */

@RestController
@RequestMapping("tag")
@Api(value = "SystemTagController", description = "标签管理api")
public class SystemTagController {

    private final SystemTagService systemTagService;

    @Autowired
    public SystemTagController(SystemTagService systemTagService) {
        this.systemTagService = systemTagService;
    }

    /**
     * @author zg
     * Description: 获取所有标签
     * @date 1:53 PM 2019/3/5
     **/
    @GetMapping("listAllTag")
    @ApiOperation(value = "listAllTag", notes = "获取所有标签")
    public ResponseEntity listAllTag() {
        try {
            return ResponseEntity.ok().body(systemTagService.listAllTag());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("请求异常");
        }
    }

    /**
     * @author zg
     * Description: 获取所有标签索引
     * @date 1:53 PM 2019/3/5
     **/
    @GetMapping("listTagIndex")
    @ApiOperation(value = "listTagIndex", notes = "获取所有标签索引")
    public ResponseEntity listTagIndex() {
        try {
            return ResponseEntity.ok().body(systemTagService.listTagIndex());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("请求异常");
        }
    }
}
