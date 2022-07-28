package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Device {
    private static final long serialVersionUID = 1L;

    private  Long id;
    private String name;
    //设备类型id
    private  Long typeId;
    //设备图片
    private  String image;
    //设备ip
    private String ip;



    //设备状态
    private  Integer status;

    //设备描述
    private String detail;



    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
