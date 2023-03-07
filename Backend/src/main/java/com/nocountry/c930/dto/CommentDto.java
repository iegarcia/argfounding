package com.nocountry.c930.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CommentDto {

    private Long commentId;

    private String description;

    private Date creationDate;

    private UserDto user;

}
