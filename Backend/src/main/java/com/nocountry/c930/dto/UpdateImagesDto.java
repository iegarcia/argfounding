package com.nocountry.c930.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateImagesDto {

    private MultipartFile[] descriptionImages;

    private MultipartFile banner;

    private MultipartFile logo;
}
