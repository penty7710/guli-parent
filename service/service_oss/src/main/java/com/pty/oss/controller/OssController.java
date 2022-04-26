package com.pty.oss.controller;

import com.pty.commonutils.R;
import com.pty.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 彭天怡 2022/4/21.
 */
@Api(tags = "头像上传")
@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;


    /**
     * 上传文件使用 MultipartFile 类型接收
     */
    @ApiOperation("上传头像")
    @PostMapping()
    public R uploadOssFile(MultipartFile file){
        //获取上传文件  MultipartFile
        //数据库中保存的是头像路径，返回上传到oss的路径

        String url = ossService.uploadFileAvatar(file);
        if(url == null){
            return R.error().message("上传失败");
        }else{
            return R.ok().data("url",url);
        }

    }
}
