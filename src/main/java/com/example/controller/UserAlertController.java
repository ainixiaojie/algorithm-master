package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.BaseContext;
import com.example.common.R;
import com.example.dto.AlertDto;
import com.example.entity.AlertClassification;
import com.example.entity.UserAlert;
import com.example.service.AlertClassificationService;
import com.example.service.UserAlertService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/alert")
public class UserAlertController {

    @Autowired
    private UserAlertService alertService;

    @Autowired
    private AlertClassificationService alertClassificationService;


    /**
     *接收移动端的警情
     *@param alert
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody UserAlert alert) {

        //从BaseContent中获取user_id 添加进warning

        alertService.save(alert);
        return R.success("警情添加成功");

    }

    /**
     * 分页查询警情
     * @param page
     * @param pageSize
     * @return
     */

    @GetMapping("/page")
    public R<Page > page(int page,int pageSize, String classification){
       List<Long> ids=new ArrayList<>();

        if(classification !=null){
            LambdaQueryWrapper<AlertClassification> wrapper=new LambdaQueryWrapper<>();
            wrapper.like(AlertClassification::getName,classification);
            List<AlertClassification> list = alertClassificationService.list(wrapper);
            for (int i = 0; i < list.size(); i++) {
                ids.add(list.get(i).getId());
            }
        }



        Page<UserAlert> pageInfo=new Page<>(page,pageSize);
        LambdaQueryWrapper<UserAlert> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(UserAlert::getCreateTime);
        queryWrapper.in(!ids.isEmpty(), UserAlert::getClassificationId,ids);
        alertService.page(pageInfo,queryWrapper);

        Page<AlertDto> page1=new Page<>();
        BeanUtils.copyProperties(pageInfo,page1,"records");
        List<AlertDto> list=pageInfo.getRecords().stream().map(item->{
            AlertDto alertDto=new AlertDto();
            BeanUtils.copyProperties(item,alertDto);

            Long classification_Id=item.getClassificationId();
            AlertClassification class1=alertClassificationService.getById(classification_Id);

            if(class1!=null){
                alertDto.setClassification(class1.getName());

            }
            return alertDto;




        }).collect(Collectors.toList());
        page1.setRecords(list);



        return R.success(page1);
    }


    /**
     * 接警,处理完毕,添加详细信息
     * @param alert
     * @return
     */
    @PutMapping
    public  R<String>  update(@RequestBody UserAlert alert){
        Long adminId= BaseContext.getCurrentId();
        alert.setAdminId(adminId);
        alertService.updateById(alert);
        return  R.success("警情修改成功");

    }


    @GetMapping("/{id}")
    public R<UserAlert> getById(@PathVariable("id") Long id){
        UserAlert alert = alertService.getById(id);
        return R.success(alert);
    }


    /**
     * 删除警情
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String>  delete(@RequestParam List<Long> ids)
    {
        alertService.removeByIds(ids);
        return R.success("警情删除成功!");

    }




}
