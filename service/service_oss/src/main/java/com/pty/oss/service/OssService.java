package com.pty.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 彭天怡 2022/4/21.
 */
public interface OssService {
    /**
     * 上传文件到oss中
     */
    String uploadFileAvatar(MultipartFile file);
}
