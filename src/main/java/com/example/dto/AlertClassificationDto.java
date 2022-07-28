package com.example.dto;

import com.example.entity.AlertClassification;
import lombok.Data;

@Data
public class AlertClassificationDto extends AlertClassification {

    private int count;
}
