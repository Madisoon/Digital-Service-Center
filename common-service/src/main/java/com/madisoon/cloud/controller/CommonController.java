package com.madisoon.cloud.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Description:
 * 公共服务的控制层
 *
 * @author Msater Zg
 * @date  2018/9/9 下午9:14
 */

@RestController
@RequestMapping(value = "/common-service")
public class CommonController {

    /**
     * 单个文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/SingleFileUpload")
    public String uploadImageFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // 新的时间类
            LocalDateTime localDateTime = LocalDateTime.now();
            //
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String str = localDateTime.format(timeFormatter);
            file.transferTo(new File(""));
            return str + file.getOriginalFilename();
        }
        return "";
    }
}
