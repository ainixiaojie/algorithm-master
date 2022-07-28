package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.CustomException;
import com.example.entity.Device;
import com.example.entity.DeviceType;

import com.example.mapper.DeviceTypeMapper;

import com.example.service.DeviceService;
import com.example.service.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.LanguageCallback;

@Service
public class DeviceTypeServiceImpl extends ServiceImpl<DeviceTypeMapper, DeviceType>  implements DeviceTypeService {

    @Autowired
    DeviceService deviceService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Device> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Device::getTypeId,id);
        int count=deviceService.count(wrapper);
        if(count>0){
            throw  new CustomException("当前设备分类下有设备,无法删除");
        }
        super.removeById(id);


    }
}
