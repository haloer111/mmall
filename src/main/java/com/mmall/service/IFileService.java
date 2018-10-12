package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author gexiao
 * @date 2018/9/28 10:05
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
