package com.ly.forum.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public String generateImage(MultipartFile file, String realPath) throws IOException;
}
