package com.example.dto;

import com.example.entity.UserAlert;
import lombok.Data;


@Data
public class AlertDto extends UserAlert {

    private String classification;
}
