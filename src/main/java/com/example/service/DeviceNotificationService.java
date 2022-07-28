package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.DeviceNotification;


public interface DeviceNotificationService extends IService<DeviceNotification> {

    void removeById(Long id);
}
