package com.nocountry.c930.service;

import com.nocountry.c930.dto.CommentDto;
import com.nocountry.c930.dto.PostCommentDto;

import java.util.Set;

public interface ICommentService {

    CommentDto createComment(Long id, PostCommentDto dto);

    CommentDto getComment(Long id);

    CommentDto updateComment(Long id, CommentDto dto);

    boolean deleteComment(Long idCampaign, Long idComment);

    Set<CommentDto> getCampaignComments (Long idCampaign);
    
}
