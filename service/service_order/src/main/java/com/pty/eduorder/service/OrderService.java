package com.pty.eduorder.service;

import com.pty.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author pty
 * @since 2022-05-03
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String userId);
}
