package com.nocountry.c930.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
public class TierCreationDto {

    private String tierName;

    private BigDecimal price;

    private String description;

    private MultipartFile image;

    private boolean isLimited;

    private int stockLimit;
}
