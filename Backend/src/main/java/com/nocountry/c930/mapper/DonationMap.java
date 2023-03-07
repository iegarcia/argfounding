package com.nocountry.c930.mapper;



import com.nocountry.c930.dto.DonationDto;
import com.nocountry.c930.dto.DonationDto;
import com.nocountry.c930.entity.DonationEntity;
import com.nocountry.c930.entity.DonationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DonationMap {

    @Autowired
    UserMap userMap;

    public DonationDto donationEntity2Dto(DonationEntity original) {

        DonationDto copy = new DonationDto();

        copy.setDonationId(original.getDonationId());
        copy.setCreationDate(original.getCreationDate());
        copy.setAmount(original.getAmount());
        copy.setStatus(original.getStatus());
        copy.setUserDto(userMap.userEntity2Dto(original.getUser()));
        copy.setTierName(original.getTierName());

        return copy;
    }

    public Set<DonationDto> donationEntityList2DtoList(Set<DonationEntity> entities) {

        Set<DonationDto> dtos = new HashSet<>();

        for (DonationEntity entity : entities) {
            dtos.add(donationEntity2Dto(entity));
        }

        return dtos;

    }

    public DonationDto donationDto2Entity(DonationEntity original) {

        DonationDto copy = new DonationDto();

        copy.setDonationId(original.getDonationId());
        copy.setCreationDate(original.getCreationDate());
        copy.setAmount(original.getAmount());
        copy.setStatus(original.getStatus());
        copy.setStatus(original.getStatus());
        copy.setUserDto(userMap.userEntity2Dto(original.getUser()));
        copy.setTierName(original.getTierName());

        return copy;
    }

}
