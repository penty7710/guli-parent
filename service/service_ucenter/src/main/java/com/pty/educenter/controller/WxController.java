package com.pty.educenter.controller;

import com.alibaba.fastjson.JSON;
import com.pty.commonutils.JwtUtils;
import com.pty.educenter.entity.UcenterMember;
import com.pty.educenter.service.UcenterMemberService;
import com.pty.educenter.utils.HttpClientUtils;
import com.pty.servicebase.handler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author : pety
 * @date : 2022/5/1 17:46
 */
@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxController {

    @Value("${wx.oppen.app_id}")
    private String app_id;

    @Value("${wx.oppen.appsecret}")
    private String appsecret;

    @Value("${wx.oppen.redirecturl}")
    private String redirecturl;


    @Autowired
    private UcenterMemberService memberService;



    //2 获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code, String state) {
        try{
            //1 获取code值，临时票据，类似于验证码
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数 ：id  秘钥 和 code值
            String accessTokenUrl = String.format(baseAccessTokenUrl,
                    app_id,
                    appsecret,
                    code);
            //请求这个拼接好的地址，得到返回两个值 accsess_token 和 openid
            //使用httpclient发送请求，得到返回结果
            String  accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            //从accessTokenInfo字符串获取出来两个值 accsess_token 和 openid
            //由于accessTokenInfo 中是key-value的形式，所以将accessTokenInfo转换成map
            //根据map里面key获取对应值
            HashMap mapAccessToken = JSON.parseObject(accessTokenInfo, HashMap.class);
            String access_token = (String) mapAccessToken.get("access_token");
            String openid = (String) mapAccessToken.get("openid");

            //把扫描人信息添加数据库里面
            //判断数据表里面是否存在相同微信信息，根据openid判断
            UcenterMember member = memberService.getOpenIdMember(openid);

            //memeber是空，表没有相同微信数据，进行添加
            if(member == null) {
                //3 拿着得到accsess_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                String userInfo = HttpClientUtils.get(userInfoUrl);
                //获取返回userinfo字符串扫描人信息
                HashMap userInfoMap = JSON.parseObject(userInfo, HashMap.class);
                //昵称
                String nickname = (String) userInfoMap.get("nickname");
                //头像
                String headimgurl = (String) userInfoMap.get("headimgurl");

                //将用户数据保存到用户表中，完成注册
                member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);

            }
            //使用jwt根据member对象生成token字符串
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());

            //最后：返回首页面，通过路径传递token字符串
            return "redirect:http://localhost:3000?token="+jwtToken;

        }catch (Exception e){
            throw new GuliException(20001,"登录失败");
        }

    }



    //1 生成微信扫描二维码
    @GetMapping("login")
    public String getWxCode() {
        //固定地址，后面拼接参数
        // 微信开放平台授权baseUrl  %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirecturl进行URLEncoder编码
        try {
            URLEncoder.encode(redirecturl,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //设置%s里面值
        String url = String.format(
                baseUrl,
                app_id,
                redirecturl,
                "pty");

        //重定向到请求微信地址里面
        return "redirect:"+url;
    }
}
