package com.nocountry.c930.dto;

import com.nocountry.c930.entity.CampaignEntity;
import com.nocountry.c930.enumeration.CampaignStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CampaignDto {

    private Long campaignId;

    private String name;

    private String bannerUrl;

    private String logoUrl;

    private String shortDescription;

    private String longDescription;

    private List<String> descriptionImages;

    private Date creationDate;

    private Date closingDate;

    private BigDecimal goalMoney;

    private BigDecimal currentMoney;

    private CampaignStatus status;

    private Set<DonationTierDto> donationTiers;

    private int daysLeft;

    private UserDto creator;
}
