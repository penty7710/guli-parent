package com.pty.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pty.commonutils.JwtUtils;
import com.pty.commonutils.R;
import com.pty.eduorder.entity.Order;
import com.pty.eduorder.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author pty
 * @since 2022-05-03
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("根据课程id生成课程订单")
    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        //根据token，获取用户id
        String userId = JwtUtils.getMemberIdByJwtToken(request);

        //创建订单，返回订单号
        String orderNo = orderService.createOrders(courseId,userId);
        return R.ok().data("orderId",orderNo);
    }

    @ApiOperation("根据订单id查询订单信息")
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId) {
        //是根据订单号查询，不是根据订单的id查询
        Order order = orderService.getOne(new QueryWrapper<Order>().eq("order_no", orderId));
        return R.ok().data("item",order);
    }


}

