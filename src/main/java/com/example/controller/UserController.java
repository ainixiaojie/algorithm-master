package com.example.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.R;
import com.example.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {



    /**
     * 移动端用户登录
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody User user, HttpSession session){
        return null;

    }











}
