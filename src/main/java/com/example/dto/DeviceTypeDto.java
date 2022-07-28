package com.example.dto;

import com.example.entity.Device;
import com.example.entity.DeviceType;
import lombok.Data;

import java.util.List;

@Data
public class DeviceTypeDto extends DeviceType {
    private  Integer count;
    private List<Device> list;
}
