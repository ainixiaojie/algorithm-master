package com.example.dto;

import com.example.entity.Device;
import com.example.entity.DeviceDetail;
import lombok.Data;

import java.util.List;


@Data
public class DeviceDto extends Device {

    public  String type;
    public List<DeviceDetail> deviceDetailList;
    //是否故障1代表故障,0 代表正常
    public  Integer breakdown;
    public Long notificationId;

    public String notification;




}
