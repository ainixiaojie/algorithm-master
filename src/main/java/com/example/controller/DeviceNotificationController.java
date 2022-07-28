package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.R;
import com.example.dto.DeviceNotificationDto;
import com.example.entity.DeviceDetail;
import com.example.entity.DeviceNotification;
import com.example.service.DeviceDetailService;
import com.example.service.DeviceNotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/DeviceNotification")
public class DeviceNotificationController {
    @Autowired
    private DeviceNotificationService notificationService;


    @Autowired
    private DeviceDetailService deviceDetailService;


    /**
     * 新增
     * @param notification
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DeviceNotification notification){
        notificationService.save(notification);
        return R.success("新增成功");

    }


    /**
     * 分页
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<DeviceNotification> pageInfo=new Page<>(page,pageSize);
        LambdaQueryWrapper<DeviceNotification> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(name!=null, DeviceNotification::getName,name);

        notificationService.page(pageInfo,wrapper);

        Page<DeviceNotificationDto> page1=new Page<>();

        BeanUtils.copyProperties(pageInfo, page1, "records");


        List<DeviceNotificationDto> collect = pageInfo.getRecords().stream().map(item -> {
            DeviceNotificationDto notificationDto = new DeviceNotificationDto();
            BeanUtils.copyProperties(item, notificationDto);


            Long notification_id = item.getId();
            //赋值count
            LambdaQueryWrapper<DeviceDetail> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(DeviceDetail::getNotificationId, notification_id);
            List<DeviceDetail> deviceDetails = deviceDetailService.list(wrapper1);
            if (deviceDetails != null) {
                notificationDto.setCount(deviceDetails.size());
            } else {
                notificationDto.setCount((0));
            }
            return notificationDto;


        }).collect(Collectors.toList());
        page1.setRecords(collect);








        return R.success(page1);
    }


    @GetMapping("/list")
    public R<List<DeviceNotification>> list(){
     List<DeviceNotification> list = notificationService.list();
     return R.success(list);
    }





    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping
    public  R<String> delete(Long id){
        notificationService.removeById(id);
        return R.success("删除成功");

    }


    /**
     * 新增
     * @param notification
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody DeviceNotification notification){
        notificationService.updateById(notification);
        return R.success("新增成功!");
    }




}
