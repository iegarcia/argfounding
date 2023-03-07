package com.nocountry.c930.mapper;

import com.nocountry.c930.dto.DonationTierDto;
import com.nocountry.c930.dto.TierCreationDto;
import com.nocountry.c930.entity.DonationTierEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DonationTierMap {

    public DonationTierDto tierEntity2Dto(DonationTierEntity entity) {
        DonationTierDto dto = new DonationTierDto();

        dto.setTierName(entity.getTierName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setTierId(entity.getDonationTierId());
        dto.setImageUrl(entity.getImageUrl());

        return dto;
    }

    public DonationTierEntity tierDto2Entity(TierCreationDto dto) {

        DonationTierEntity entity = new DonationTierEntity();

        entity.setTierName(dto.getTierName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setLimited(dto.isLimited());

        if (dto.isLimited()){
            entity.setStockLimit(dto.getStockLimit());
        }

        return entity;
    }

    public Set<DonationTierEntity> tierDtoSet2Entity(Set<TierCreationDto> dtos) {

        Set<DonationTierEntity> entities = new HashSet<>();

        for (TierCreationDto dto : dtos) {

            entities.add(tierDto2Entity(dto));
        }

        return entities;
    }

    public Set<DonationTierDto> tierEntitySet2Dto(Set<DonationTierEntity> entites) {

        Set<DonationTierDto> dtos = new HashSet<>();

        for (DonationTierEntity entity : entites) {

            dtos.add(tierEntity2Dto(entity));
        }

        return dtos;

    }

}
