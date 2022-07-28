package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.DeviceGps;
import com.example.mapper.DeviceGpsMapper;
import com.example.service.DeviceGpsService;

import org.springframework.stereotype.Service;

@Service
public class DeviceGpsServiceImpl extends ServiceImpl<DeviceGpsMapper, DeviceGps> implements DeviceGpsService {
}
