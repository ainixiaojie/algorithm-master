package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.R;
import com.example.dto.DeviceDto;
import com.example.dto.DeviceTypeDto;
import com.example.entity.Device;
import com.example.entity.DeviceDetail;
import com.example.entity.DeviceType;
import com.example.service.DeviceService;
import com.example.service.DeviceTypeService;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deviceType")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;

    @Autowired
    private DeviceService deviceService;


    @PostMapping
    public R<String> save(@RequestBody DeviceType deviceType){
        deviceTypeService.save(deviceType);
        return R.success("新增成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize ){




        Page<DeviceType> pageInfo = new Page<>(page, pageSize);
        deviceTypeService.page(pageInfo);

        Page<DeviceTypeDto> deviceTypeDtoPage = new Page<>();
        BeanUtils.copyProperties(pageInfo, deviceTypeDtoPage, "records");


        List<DeviceTypeDto> collect = pageInfo.getRecords().stream().map(item -> {
            //复制device到deviceDto
            DeviceTypeDto deviceTypeDto = new DeviceTypeDto();
            BeanUtils.copyProperties(item, deviceTypeDto);


            Long type_id = item.getId();
            //赋值type

            LambdaQueryWrapper<Device> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(Device::getTypeId,type_id);
            List<Device> list=deviceService.list(queryWrapper);
            if(list!=null){
                deviceTypeDto.setList(list);
                deviceTypeDto.setCount(list.size());
            }else{
                deviceTypeDto.setCount(0);
            }



            return deviceTypeDto;

        }).collect(Collectors.toList());
        deviceTypeDtoPage.setRecords(collect);

        return R.success(deviceTypeDtoPage);

    }


    @DeleteMapping
    public R<String> delete(Long id){
        deviceTypeService.remove(id);

        return R.success("删除成功");
    }




    @PutMapping
    public R<String > update(@RequestBody DeviceType deviceType){

        deviceTypeService.updateById(deviceType);
        return R.success("修改成功!");
    }


    @GetMapping("/list")
    public R<List<DeviceType>> list(){
        List<DeviceType> list = deviceTypeService.list();
        return  R.success(list);

    }







}
