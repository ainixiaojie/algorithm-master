package com.example.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceGps {

    private static final long serialVersionUID = 1L;
    private Long id;
    private  Long deviceId;
    private  Double latitude;
    private Double longitude;










    @TableField(fill = FieldFill.INSERT) //插入时填充字段
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
    private LocalDateTime updateTime;



    private  Integer isDeleted;







}
