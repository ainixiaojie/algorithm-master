package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.CustomException;
import com.example.entity.DeviceDetail;
import com.example.entity.DeviceNotification;
import com.example.mapper.DeviceNotificationMapper;
import com.example.service.DeviceDetailService;
import com.example.service.DeviceNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeviceNotificationServiceImpl extends ServiceImpl<DeviceNotificationMapper, DeviceNotification> implements DeviceNotificationService {

    @Autowired
    private DeviceDetailService deviceDetailService;

    @Override
    public void removeById(Long id) {
        LambdaQueryWrapper<DeviceDetail> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(DeviceDetail::getNotificationId,id);
        int count=deviceDetailService.count(wrapper);
        if(count>0){
            throw  new CustomException("该分类下有设备,无法删除!");
        }
        super.removeById(id);
    }
}



