package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.R;
import com.example.dto.AlertClassificationDto;
import com.example.dto.DeviceDto;
import com.example.entity.AlertClassification;
import com.example.entity.DeviceDetail;
import com.example.entity.DeviceType;
import com.example.entity.UserAlert;
import com.example.service.AlertClassificationService;
import com.example.service.UserAlertService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertClassification")
public class AlertClassificationController {
    @Autowired
    private AlertClassificationService alertClassificationService;

    @Autowired
    private UserAlertService userAlertService;

    /**
     * 新增警情
     * @param alertClassification
     * @return
     */

    @PostMapping
    public R<String > save(@RequestBody AlertClassification alertClassification){
        alertClassificationService.save(alertClassification);
        return  R.success("新增警情分类成功");
    }






    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){
        Page<AlertClassification> pageInfo=new Page<>(page,pageSize);
        alertClassificationService.page(pageInfo);

        Page<AlertClassificationDto> page1=new Page<>();
        BeanUtils.copyProperties(pageInfo,page1,"records");


        List<AlertClassificationDto> collect = pageInfo.getRecords().stream().map(item -> {
          AlertClassificationDto dto=new AlertClassificationDto();
          BeanUtils.copyProperties(item,dto);

          Long classificationId=item.getId();
          LambdaQueryWrapper<UserAlert> wrapper=new LambdaQueryWrapper();
          wrapper.eq(UserAlert::getClassificationId,classificationId);
          int count=userAlertService.count(wrapper);
          dto.setCount(count);
          return dto;


        }).collect(Collectors.toList());
        page1.setRecords(collect);

        return R.success(page1);
    }



    @GetMapping("/list")
    public R<List<AlertClassification>> list(){
        List<AlertClassification> list=alertClassificationService.list();
        return R.success(list);
    }



    @PutMapping
    public R<String> update(@RequestBody AlertClassification classification){
       alertClassificationService.updateById(classification);
        return R.success("修改成功!");
    }

    @DeleteMapping
     public R<String >  delete(@RequestParam Long id){
        alertClassificationService.removeById(id);
        return  R.success("删除成功!");

     }
}
