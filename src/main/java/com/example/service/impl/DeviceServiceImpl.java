package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.DeviceDto;
import com.example.entity.Device;
import com.example.entity.DeviceDetail;
import com.example.entity.DeviceGps;
import com.example.mapper.DeviceMapper;
import com.example.service.DeviceDetailService;
import com.example.service.DeviceGpsService;
import com.example.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {
    @Autowired
    private DeviceDetailService deviceDetailService;

    @Autowired
    private DeviceGpsService deviceGpsService;


    /**
     * 根据设备id删除设备表,设备位置表,设备详情表
     *
     * @param ids
     */

    @Override
    public void removeWithDetailAndGPS(List<Long> ids) {
        //根据设备id删除设备表
        this.removeByIds(ids);


        //根据设备id删除设备位置表
        LambdaQueryWrapper<DeviceGps> deviceGpsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        deviceGpsLambdaQueryWrapper.in(DeviceGps::getDeviceId, ids);
        deviceGpsService.remove(deviceGpsLambdaQueryWrapper);


        //根据设备id删除设备详情表
        LambdaQueryWrapper<DeviceDetail> detailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        detailLambdaQueryWrapper.in(DeviceDetail::getDeviceId, ids);
        deviceDetailService.remove(detailLambdaQueryWrapper);


    }

    @Override
    public void saveWithDetail(DeviceDto deviceDto) {
        this.save(deviceDto);

        Long notificationId=deviceDto.getNotificationId();
        if(notificationId!=null){





            Long deviceId=deviceDto.getId();
            DeviceDetail deviceDetail=new DeviceDetail();
            //先删除
            deviceDetailService.removeById(deviceId);

            deviceDetail.setDeviceId(deviceId);

            deviceDetail.setNotificationId(notificationId);
            deviceDetailService.save(deviceDetail);
        }


    }


    @Transactional

    @Override
    public void updateWithDetail(DeviceDto deviceDto) {

        this.updateById(deviceDto);
        if(deviceDto.getNotificationId()!=null){

            DeviceDetail deviceDetail=new DeviceDetail();
            deviceDetail.setNotificationId(deviceDto.getNotificationId());
            deviceDetail.setDeviceId(deviceDto.getId());
            deviceDetailService.updateById(deviceDetail);
        }

    }


}
