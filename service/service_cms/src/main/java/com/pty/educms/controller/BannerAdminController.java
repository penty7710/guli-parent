package com.pty.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pty.commonutils.R;
import com.pty.educms.entity.CrmBanner;
import com.pty.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 管理员功能
 * </p>
 *
 * @author pty
 * @since 2022-04-28
 */
@CrossOrigin
@RestController
@RequestMapping("/educms/banneradmin")
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    //1 分页查询banner
    @ApiOperation(value = "分页查询banner")
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit){
        Page<CrmBanner> bannerPage = new Page<>(page, limit);
        crmBannerService.page(bannerPage,null);

        long total = bannerPage.getTotal();
        List<CrmBanner> records = bannerPage.getRecords();
        return R.ok().data("items",records).data("total",total);
    }

    //2 添加banner
    @ApiOperation(value = "添加Banner")
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return R.ok();
    }


    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return R.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return R.ok();
    }

}

