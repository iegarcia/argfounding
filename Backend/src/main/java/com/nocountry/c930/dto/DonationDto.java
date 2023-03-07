package com.nocountry.c930.dto;

import com.nocountry.c930.entity.CampaignEntity;
import com.nocountry.c930.entity.UserEntity;
import com.nocountry.c930.enumeration.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
public class DonationDto {

    private Long donationId;

    private Date creationDate;

    private BigDecimal amount;

    private PaymentStatus status;

    private UserDto userDto;

    private String tierName;


}
