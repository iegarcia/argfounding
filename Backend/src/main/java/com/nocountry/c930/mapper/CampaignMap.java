package com.nocountry.c930.mapper;


import com.nocountry.c930.dto.CampaignCreationDto;
import com.nocountry.c930.dto.CampaignBasicDto;
import com.nocountry.c930.dto.CampaignDto;
import com.nocountry.c930.entity.CampaignEntity;
import com.nocountry.c930.service.IUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CampaignMap {

    @Autowired
    private UserMap userMap;

    @Autowired
    private DonationTierMap tierMap;

    @Autowired
    private IUtilService util;


    public CampaignDto campaignEntity2Dto(CampaignEntity original) {

        CampaignDto copy = new CampaignDto();

        copy.setCampaignId(original.getCampaignId());
        copy.setName(original.getName());

        if(original.getLongDescription() == null){
            copy.setLongDescription(" ");
        }else {
            copy.setLongDescription(original.getLongDescription());
        }
        copy.setDescriptionImages(original.getDescriptionImages());
        copy.setShortDescription(original.getShortDescription());
        copy.setBannerUrl(original.getBannerUrl());
        copy.setLogoUrl(original.getLogoUrl());

        copy.setCreationDate(original.getCreationDate());
        copy.setClosingDate(original.getClosingDate());
        copy.setGoalMoney(original.getGoalMoney());
        copy.setCurrentMoney(original.getCurrentMoney());
        copy.setStatus(original.getStatus());
        copy.setDaysLeft(util.calculateDaysLeft(original.getClosingDate()));


        copy.setCreator(userMap.userEntity2Dto(original.getCreator()));
        copy.setDonationTiers(tierMap.tierEntitySet2Dto(original.getDonationTiers()));

        return copy;

    }

    public CampaignBasicDto campaignEntity2BasicDto(CampaignEntity original) {

        CampaignBasicDto copy = new CampaignBasicDto();

        copy.setCampaignId(original.getCampaignId());
        copy.setName(original.getName());
        copy.setCreationDate(original.getCreationDate());
        copy.setClosingDate(original.getClosingDate());
        copy.setGoalMoney(original.getGoalMoney());
        copy.setCurrentMoney(original.getCurrentMoney());
        copy.setBannerUrl(original.getBannerUrl());
        copy.setShortDescription(original.getShortDescription());
        copy.setLogoUrl(original.getLogoUrl());


        copy.setCreator(original.getCreator().getFirstName() + " " + original.getCreator().getLastName());

        return copy;

    }


    public List<CampaignBasicDto> campaignEntityList2BasicDto(List<CampaignEntity> entities) {

        List<CampaignBasicDto> dtos = new ArrayList<>();

        for (CampaignEntity entity : entities) {
            dtos.add(campaignEntity2BasicDto(entity));
        }

        return dtos;
    }

    public CampaignEntity campaignDto2Entity(CampaignDto original) {

        CampaignEntity copy = new CampaignEntity();
        copy.setCampaignId(original.getCampaignId());
        copy.setName(original.getName());
        copy.setLongDescription(original.getLongDescription());
        copy.setShortDescription(original.getLongDescription());

        copy.setCreationDate(original.getCreationDate());
        copy.setClosingDate(original.getClosingDate());
        copy.setGoalMoney(original.getGoalMoney());
        copy.setCurrentMoney(original.getCurrentMoney());
        copy.setStatus(original.getStatus());

        copy.setCreator(userMap.userDto2Entity(original.getCreator()));

        return copy;

    }

    public CampaignEntity campaignCreation2Entity(CampaignCreationDto dto) {

        CampaignEntity entity = new CampaignEntity();
        entity.setName(dto.getName());

        entity.setShortDescription(dto.getShortDescription());
        entity.setLongDescription(dto.getLongDescription());
        entity.setClosingDate(dto.getClosingDate());
        entity.setGoalMoney(dto.getGoalMoney());

        return entity;
    }
}
