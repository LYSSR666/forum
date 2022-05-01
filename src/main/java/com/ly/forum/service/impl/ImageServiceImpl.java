package com.ly.forum.service.impl;


import com.ly.forum.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    /**
     * 将文件保存到服务器
     * @param file           文件
     * @param realUploadPath 真实路径
     * @return 文件的具体路径
     * @throws IOException IO异常
     */
    @Override
    public String generateImage(MultipartFile file, String realUploadPath) throws IOException {
        //如果目录不存在，则创建目录
        File uploadFile = new File(realUploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        // 获取文件（图片）类型
        String originalFilename = file.getOriginalFilename();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf('.'));
        // 生成随机filename
        UUID uuid = UUID.randomUUID();
        String filename = uuid + fileType;
        // 创建输入流
        InputStream inputStream = file.getInputStream();
        // 生成输出地址
        String outputPath = realUploadPath + filename;
        // 创建输出流
        OutputStream outputStream = new FileOutputStream(outputPath);
        // 保存文件
        FileCopyUtils.copy(inputStream, outputStream);
        return filename;
    }

}
