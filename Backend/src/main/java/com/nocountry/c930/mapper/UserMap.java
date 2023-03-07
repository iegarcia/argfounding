package com.nocountry.c930.mapper;


import com.nocountry.c930.auth.dto.ResponseUserDto;
import com.nocountry.c930.dto.UserDto;
import com.nocountry.c930.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMap {


  public UserDto userEntity2Dto(UserEntity entity) {

    UserDto dto = new UserDto();

    dto.setId(entity.getUserId());
    dto.setFirstName(entity.getFirstName());
    dto.setLastName(entity.getLastName());
    dto.setEmail(entity.getEmail());

    return dto;
  }

  public UserEntity userDto2Entity(UserDto dto) {

    UserEntity entity = new UserEntity();

    entity.setUserId(dto.getId());
    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    entity.setEmail(dto.getEmail());

    return entity;
  }

  public List<UserDto> userEntityList2DtoList(List<UserEntity> entities) {

    List<UserDto> dtos = new ArrayList<>();

    for (UserEntity entity : entities) {
      dtos.add(userEntity2Dto(entity));
    }

    return dtos;

  }

  public List<UserEntity> userDtoList2EntityList(List<UserDto> dtos) {

    List<UserEntity> entities = new ArrayList<>();

    for (UserDto dto : dtos) {
      entities.add(userDto2Entity(dto));
    }

    return entities;
  }

  public UserEntity userAuthDto2Entity(ResponseUserDto userDto) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    UserEntity userEntity = new UserEntity();
    userEntity.setFirstName(userDto.getFirstName());
    userEntity.setLastName(userDto.getLastName());
    userEntity.setEmail(userDto.getEmail());
    userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    userEntity.setUpdateDate(userDto.getUpdateDate());
    userEntity.setCreationDate(userDto.getCreationDate());

    return userEntity;
  }

  public ResponseUserDto userAuthEntity2Dto(UserEntity entitySaved) {
    ResponseUserDto dto = new ResponseUserDto();
    dto.setId(entitySaved.getUserId());
    dto.setFirstName(entitySaved.getFirstName());
    dto.setLastName(entitySaved.getLastName());
    dto.setEmail(entitySaved.getEmail());
    dto.setRole(entitySaved.getRole().getName());
    dto.setUpdateDate(entitySaved.getUpdateDate());
    dto.setCreationDate(entitySaved.getCreationDate());
    dto.setPassword("[PROTECTED]");
    dto.setImgUrl(entitySaved.getImageUrl());


    return dto;
  }

}
