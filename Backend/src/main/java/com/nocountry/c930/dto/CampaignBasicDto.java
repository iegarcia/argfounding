package com.nocountry.c930.dto;

import com.nocountry.c930.enumeration.CampaignStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class CampaignBasicDto {

    private Long campaignId;

    private String name;

    private String shortDescription;

    private Date creationDate;

    private Date closingDate;

    private BigDecimal goalMoney;

    private BigDecimal currentMoney;

    private String creator;

    private String bannerUrl;

    private String logoUrl;

}
