package com.nocountry.c930.mapper;

import com.nocountry.c930.dto.CommentDto;
import com.nocountry.c930.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CommentMap {

    @Autowired
    UserMap userMap;
    @Autowired
    CampaignMap campaignMap;

    public CommentDto commentEntity2Dto(CommentEntity original) {

        CommentDto copy = new CommentDto();

        copy.setCommentId(original.getCommentId());
        copy.setDescription(original.getDescription());
        copy.setCreationDate(original.getCreationDate());
        copy.setUser(userMap.userEntity2Dto(original.getUser()));

        return copy;
    }

    public Set<CommentDto> commentEntityList2DtoList(Set<CommentEntity> entities) {

        Set<CommentDto> dtos = new HashSet<>();

        for (CommentEntity entity : entities) {
            dtos.add(commentEntity2Dto(entity));
        }

        return dtos;

    }

    public CommentEntity commentDto2Entity(CommentDto original) {

        CommentEntity copy = new CommentEntity();

        copy.setCommentId(original.getCommentId());
        copy.setDescription(original.getDescription());
        copy.setCreationDate(original.getCreationDate());
        copy.setUser(userMap.userDto2Entity(original.getUser()));

        return copy;
    }

}
