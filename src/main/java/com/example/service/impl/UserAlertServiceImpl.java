package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.UserAlert;
import com.example.mapper.UserAlertMapper;
import com.example.service.UserAlertService;
import org.springframework.stereotype.Service;

@Service
public class UserAlertServiceImpl extends ServiceImpl<UserAlertMapper, UserAlert> implements UserAlertService {
}
