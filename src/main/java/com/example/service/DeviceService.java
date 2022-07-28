package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dto.DeviceDto;
import com.example.entity.Device;
import com.example.entity.DeviceDetail;

import java.util.List;

public interface DeviceService  extends IService<Device> {
    void removeWithDetailAndGPS(List<Long> ids);

    void saveWithDetail(DeviceDto  deviceDto);

    void updateWithDetail(DeviceDto deviceDto);


}
