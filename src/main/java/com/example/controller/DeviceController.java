package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.R;
import com.example.dto.DeviceDto;
import com.example.entity.Device;
import com.example.entity.DeviceDetail;
import com.example.entity.DeviceType;
import com.example.service.DeviceDetailService;
import com.example.service.DeviceService;
import com.example.service.DeviceTypeService;
import com.example.service.DeviceNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceDetailService deviceDetailService;

    @Autowired
    private DeviceTypeService deviceTypeService;

    @Autowired
    private DeviceNotificationService notificationService;

    /**
     * 添加设备
     *
     * @param deviceDto
     * @return
     */

    @PostMapping
    public R<String> save(@RequestBody DeviceDto deviceDto) {
        deviceService.saveWithDetail(deviceDto);
        return R.success("设备添加成功");
    }

    /**
     * 分页查询设备,
     * 返回设备详细信息(包括边缘检测结果)
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Device> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Device::getName, name);
        queryWrapper.orderByDesc(Device::getCreateTime);
        deviceService.page(pageInfo, queryWrapper);

        Page<DeviceDto> deviceDtoPage = new Page<>();
        BeanUtils.copyProperties(pageInfo, deviceDtoPage, "records");


        List<DeviceDto> collect = pageInfo.getRecords().stream().map(item -> {
            //复制device到deviceDto
            DeviceDto deviceDto = new DeviceDto();
            BeanUtils.copyProperties(item, deviceDto);


            Long device_id = item.getId();
            //赋值type


            DeviceType type = deviceTypeService.getById(item.getTypeId());
            if (type != null) {
                deviceDto.setType(type.name);


            }


            //赋值deviceDto list
            LambdaQueryWrapper<DeviceDetail> detailLambdaQueryWrapper = new LambdaQueryWrapper<>();
            detailLambdaQueryWrapper.eq(DeviceDetail::getDeviceId, device_id);
            List<DeviceDetail> list = deviceDetailService.list(detailLambdaQueryWrapper);

            if (list.size() != 0) {
                Long notificationId = list.get(0).getNotificationId();
                deviceDto.setBreakdown(1);
                deviceDto.setNotification(notificationService.getById(notificationId).getName());
            } else {
                deviceDto.setBreakdown(0);
            }
            deviceDto.setDeviceDetailList(list);


            return deviceDto;

        }).collect(Collectors.toList());
        deviceDtoPage.setRecords(collect);


        return R.success(deviceDtoPage);
    }


    @GetMapping("/{id}")
    public R<DeviceDto> getById(@PathVariable Long id) {
        Device device = deviceService.getById(id);
        DeviceDto deviceDto = new DeviceDto();
        BeanUtils.copyProperties(device, deviceDto);


        LambdaQueryWrapper<DeviceDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DeviceDetail::getDeviceId, id);
        DeviceDetail deviceDetail = deviceDetailService.getOne(wrapper);
        if (deviceDetail != null) {
            deviceDto.setNotificationId(deviceDetail.getNotificationId());

        }

        return R.success(deviceDto);


    }

    /**
     * 修改设备
     * 修改设备表,设备详情表
     *
     * @param deviceDto
     * @return
     */

    @PutMapping
    public R<String> update(@RequestBody DeviceDto deviceDto) {
        deviceService.updateWithDetail(deviceDto);

//        deviceService.updateById(deviceDto);
        return R.success("修改设备成功");

    }


    /**
     * 删除设备表及
     * 设备详情表,设备设备设备位置表
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        deviceService.removeWithDetailAndGPS(ids);
        return R.success("删除设备成功");
    }


}
