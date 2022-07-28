package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.DeviceType;

public interface DeviceTypeService  extends IService<DeviceType> {





    public void remove(Long id);
}
