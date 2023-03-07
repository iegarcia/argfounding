package com.nocountry.c930.dto;

import com.nocountry.c930.enumeration.CampaignStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateCampaignDto {

    private String name;

    private String longDescription;

    private String shortDescription;

    private MultipartFile image;

    private MultipartFile[] descriptionImages;

}
