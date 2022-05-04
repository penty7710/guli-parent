package com.pty.educenter.service;

import com.pty.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pty.educenter.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author pty
 * @since 2022-04-29
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
