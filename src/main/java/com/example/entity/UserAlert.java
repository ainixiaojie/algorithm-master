package com.example.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAlert {

    private static final long serialVersionUID = 1L;

    private  Long id;

    //用户id
    private  Long userId;

    private String userName;
    //分类id
    private  Long classificationId;


    //经度
    private  Double Longitude;
    //纬度
    private Double latitude;
    //警报详情
    private  String detail;
    //现场图片
    private  String image;
    //管理员id
    private  Long adminId;
    //0未处理,1处理
    private  Integer isProcessed;



    @TableField(fill = FieldFill.INSERT) //插入时填充字段
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
    private LocalDateTime updateTime;










}
