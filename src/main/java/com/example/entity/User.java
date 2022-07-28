package com.example.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.models.auth.In;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {


    private static final long serialVersionUID = 1L;

    private  Long id;
    //用户头像
    private  String avatar;
    //用户账号(唯一)
    private  String name;
    //用户email
    private  String email;
    //用户手机号
    private  String phone;





    @TableField(fill = FieldFill.INSERT) //插入时填充字段
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //插入和更新时填充字段
    private LocalDateTime updateTime;

    //1在线,0不在线
    private Integer status;











}
