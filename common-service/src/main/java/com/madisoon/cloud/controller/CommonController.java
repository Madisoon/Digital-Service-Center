package com.madisoon.cloud.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * 公共服务的控制层
 *
 * @author Msater Zg
 * @date 2018/9/9 下午9:14
 */

@RestController
@RequestMapping(value = "/common-service")
public class CommonController {

    /**
     * 单个文件上传
     *
     * @param file 需要上传的文件
     * @return 文件的地址
     * @throws IOException
     */
    @PostMapping(value = "/singleFileUpload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // 新的时间类
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

            String str = localDateTime.format(timeFormatter);
            file.transferTo(new File(""));
            return str + file.getOriginalFilename();
        }
        return "file is empty";
    }

    /**
     * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
     *
     * @param request 请求
     * @return 多个文件地址，用逗号隔开
     */

    @PostMapping(value = "/multipleFileUpload")
    public String multipleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    return "You failed to upload " + i + " => "
                            + e.getMessage();

                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }

        }
        return "upload successful";

    }
}
