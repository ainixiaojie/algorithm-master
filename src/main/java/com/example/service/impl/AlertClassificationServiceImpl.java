package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.CustomException;
import com.example.entity.AlertClassification;
import com.example.entity.UserAlert;
import com.example.mapper.AlertClassificationMapper;
import com.example.service.AlertClassificationService;
import com.example.service.UserAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlertClassificationServiceImpl  extends ServiceImpl<AlertClassificationMapper, AlertClassification> implements AlertClassificationService {
    @Autowired
    private UserAlertService userAlertService;

    @Override
    public void removeById(Long id) {

        LambdaQueryWrapper<UserAlert> wrapper=new LambdaQueryWrapper();
        wrapper.eq(UserAlert::getClassificationId,id);
        int count=userAlertService.count(wrapper);
        if(count>0){
            throw new CustomException("该分类下有警情,无法删除");
        }
        super.removeById(id);

    }
}
