package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DeviceDetail {

    private static final long serialVersionUID = 1L;

    private  Long id;
    //对应的设备id
    private  Long deviceId;

    //设备故障id
    private  Long notificationId;


    //设备故障图片
    private  String  image;



    //经度
    private Double longitude;

    //纬度
    private Double latitude;


    //详细信息
    private String detail;

    @TableField(fill = FieldFill.INSERT) //插入时填充字段
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
    private LocalDateTime updateTime;

    @TableLogic
    private  Integer isDeleted;

}
