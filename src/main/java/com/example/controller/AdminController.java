package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.R;
import com.example.entity.Admin;

import com.example.service.AdminService;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;




    @PostMapping("/login")
    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin){


        //1、将页面提交的密码password进行md5加密处理
        String password = admin.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName,admin.getName());
        Admin ad = adminService.getOne(queryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if(ad == null){
            return R.error("登录失败");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if(!ad.getPassword().equals(password)){
            return R.error("密码错误");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if(ad.getStatus() == 0){
            return R.error("账号已禁用");
        }

        //6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("adminId",ad.getId());

        return R.success(ad);
    }




    /**
     * 管理员退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("adminId");
        return R.success("退出成功");
    }








    /**
     * 新增管理员
     * @param admin
     * @return
     */

    @PostMapping
    public R<String> save(@RequestBody Admin admin){
        log.info("{}",admin);
        String password=admin.getPassword();
        admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        adminService.save(admin);
        return R.success("添加成功");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public  R<Page> page(int page,int pageSize,String name){
        Page<Admin> pageInfo=new Page<>(page,pageSize);
        LambdaQueryWrapper<Admin> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.like(name!=null,Admin::getName,name);

        queryWrapper.orderByDesc(Admin::getCreateTime);

        adminService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);

    }


    /**
     * 修改时回显
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Admin> getById(@PathVariable Long id){
        Admin admin = adminService.getById(id);

        return R.success(admin);
    }

    @PutMapping
    public R<String > update(@RequestBody Admin admin){
        adminService.updateById(admin);
        return R.success("修改成功");

    }










}
