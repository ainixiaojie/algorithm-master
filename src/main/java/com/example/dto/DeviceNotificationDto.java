package com.example.dto;

import com.example.entity.DeviceNotification;
import lombok.Data;

@Data
public class DeviceNotificationDto extends DeviceNotification {
    private int count;
}
