package com.test.demo.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.DateUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Service
public class ImageService {

    @Value("${web.upload-path}")
    private String path;            //自定义路径

    /**
     * 保存图片
     * @param image   上传的图片
     * @return
     */
    public Object saveImage(MultipartFile image) {

        if(null == image) {
            return "上传图片不能为空";
        }

        //获取图片类型
        String exName = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        //使用工具类UUID给图片重命名
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + exName;
        //为了便于我们查找图片，将保存路径以 年 / 月 / 日 / 的格式命名
        StringBuilder realPath = new StringBuilder(path);
        realPath.append(fileName);

        File file = new File(realPath.toString());
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            image.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return "请重新上传图片";
        }

        //将图片的保存路径realPath保存到数据库中
        /* .............. */

        return realPath;
    }

}