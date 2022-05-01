package com.ly.forum.controller;


import com.ly.forum.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Value("${file.upload.path}")
    private String imagePath;   // imagePath = D:\\images\\image-recognition\\"

    // 图片相对路径 => path = "/images"
    @Value("${file.upload.relative-path}")
    private String relativePath;    // relativePath = "/images/**"

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @PostMapping("/upload")
    public Map<String, String> uploadAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            // 获取图片路径 D:\\images\\image-recognition\\image.png"
            String realFileName = imageService.generateImage(file, imagePath);
            // 拼接文件的网络位置 imageURL = "http://localhost:8080/images/image.png"
            String imageURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + relativePath + realFileName;
            // 返回数据到前端
            Map<String, String> data = new HashMap<>();
            data.put("image", imageURL);
            data.put("filename", realFileName);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
