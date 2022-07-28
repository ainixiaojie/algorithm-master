package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.DeviceDetail;
import com.example.mapper.DeviceDetailMapper;
import com.example.service.DeviceDetailService;
import org.springframework.stereotype.Service;

@Service
public class DeviceDetailServiceImpl extends ServiceImpl<DeviceDetailMapper, DeviceDetail> implements DeviceDetailService {
}
