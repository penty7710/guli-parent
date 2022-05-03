package com.pty.eduorder.service.impl;

import com.pty.eduorder.client.EduClint;
import com.pty.eduorder.client.UcenterClient;
import com.pty.eduorder.entity.Order;
import com.pty.eduorder.mapper.OrderMapper;
import com.pty.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pty.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author pty
 * @since 2022-05-03
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Autowired
    private EduClint eduClint;


    @Autowired
    private UcenterClient ucenterClient;

    /**
     * 生成订单
     * @param courseId 课程id
     * @param userId  用户id
     */
    @Override
    public String createOrders(String courseId, String userId) {
        //通过远程调用根据用户id获取用户信息
        //进行类型转换
        Map<String, String> userInfo = (Map<String, String>) ucenterClient.getUserInfoOrder(userId).getData().get("ucenterMember");

        //通过远程调用根据课程id获取课程信息
        Map<String, String> courseInfo = (Map<String, String>) eduClint.getCourseInfoOrder(courseId).getData().get("courseInfo");

        //创建Order对象，向order对象里面设置数据
        Order order = new Order();
        //订单号
        order.setOrderNo(OrderNoUtil.getOrderNo());
        //课程号
        order.setCourseId(courseId);
        //课程名称
        order.setCourseTitle(courseInfo.get("title"));
        //课程封面
        order.setCourseCover( courseInfo.get("cover"));
        //讲师名称
        order.setTeacherName(courseInfo.get("teacherName"));
        //价格
        String price =String.valueOf(courseInfo.get("price")) ;
        order.setTotalFee( new BigDecimal(price));
        //用户id
        order.setMemberId(userId);
        //用户手机号
         order.setMobile(userInfo.get("mobile"));
        //昵称
        order.setNickname(userInfo.get("nickname"));
        //订单支付状态 0未支付，1已支付
        order.setStatus(0);
        //支付类型1.微信 2.支付宝
        order.setPayType(1);

        baseMapper.insert(order);


        //返回订单号
        return order.getOrderNo();
    }
}
