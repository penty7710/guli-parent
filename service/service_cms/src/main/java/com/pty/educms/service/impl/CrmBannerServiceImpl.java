package com.pty.educms.service.impl;

import com.pty.educms.entity.CrmBanner;
import com.pty.educms.mapper.CrmBannerMapper;
import com.pty.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author pty
 * @since 2022-04-28
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    //查询所有的banner
    //使用这个注解，会将查询结果以 value::key的形式缓存在redis中
    @Cacheable(value = "banner",key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectAllBanner() {

        List<CrmBanner> crmBanners = baseMapper.selectList(null);
        return crmBanners;
    }
}
