package com.nocountry.c930.mapper;

import com.nocountry.c930.dto.ComplaintDto;
import com.nocountry.c930.entity.ComplaintEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComplaintMap {

    @Autowired
    UserMap userMap;
    @Autowired
    CampaignMap campaignMap;

    public ComplaintDto complaintEntity2Dto(ComplaintEntity original) {

        ComplaintDto copy= new ComplaintDto();

        copy.setComplaintId(original.getComplaintId());
        copy.setDescription(original.getDescription());
        copy.setCreationDate(original.getCreationDate());

        copy.setUser(userMap.userEntity2Dto(original.getUser()));
        copy.setCampaign(campaignMap.campaignEntity2Dto(original.getCampaign()));

        return copy;

    }

    public List<ComplaintDto> complaintEntityList2Dto(List<ComplaintEntity> entities) {

        List<ComplaintDto> dtos = new ArrayList<>();

        for (ComplaintEntity entity : entities) {
            dtos.add(complaintEntity2Dto(entity));
        }

        return dtos;
    }

    public ComplaintEntity complaintDto2Entity(ComplaintDto original) {

        ComplaintEntity copy = new ComplaintEntity();

        copy.setComplaintId(original.getComplaintId());
        copy.setDescription(original.getDescription());
        copy.setCreationDate(original.getCreationDate());

        copy.setUser(userMap.userDto2Entity(original.getUser()));
        copy.setCampaign(campaignMap.campaignDto2Entity(original.getCampaign()));

        return copy;

    }

}
