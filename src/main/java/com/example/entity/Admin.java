package com.example.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {


    private static final long serialVersionUID = 1L;

    private Long id;
    private String nickName;
    private String avatar;

    //姓名
    private String name;

    private String password;

    //手机号
    private String phone;


    //0是男,1是女
    private Integer sex;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //状态 0:禁用，1:正常
    private Integer status;
}
