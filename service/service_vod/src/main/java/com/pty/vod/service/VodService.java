package com.pty.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 彭天怡 2022/4/26.
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file, String keyid, String keysecret);
}
