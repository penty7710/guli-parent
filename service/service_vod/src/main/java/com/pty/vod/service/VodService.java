package com.pty.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 彭天怡 2022/4/26.
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file, String keyid, String keysecret);

    void removeAlyVideo(String keyid, String keysecret, String id);

    void removeMoreAlyVideo(List<String> videoIdList, String keyid, String keysecret);
}
