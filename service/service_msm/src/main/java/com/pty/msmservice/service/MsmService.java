package com.pty.msmservice.service;

import java.util.Map;

/**
 * @author : pety
 * @date : 2022/4/29 10:16
 */
public interface MsmService {
    boolean send(String code, String phone);
}
