package com.madisoon.cloud.module.information.web;

import com.madisoon.cloud.module.information.service.InformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * 信息相关的服务层
 *
 * @author Msater Zg
 * @version 1.0
 * @date 2019/3/5 10:03 AM
 */

@RestController
@RequestMapping("information")
@Api(value = "information", description = "信息相关")
public class InformationController {

    private final InformationService informationService;

    @Autowired
    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("information/show")
    @ApiOperation(value = "information/show", notes = "分页获取展示的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "第几页", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageSize", value = "每一页的数量", required = true, dataType = "STRING")
    })
    public void listInformationShow(@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize) {

    }

    @PutMapping("information/show")
    public void updateInformation(@RequestBody JSONObject jsonObject) {

    }

    @PostMapping("information/show")
    public void saveInformation(@RequestBody JSONObject jsonObject) {

    }

    @DeleteMapping("information/show")
    public void deleteInformation(@RequestBody List ids) {

    }
}
