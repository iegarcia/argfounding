package com.nocountry.c930.service;

import com.nocountry.c930.auth.dto.UserRegistrationDto;
import com.nocountry.c930.dto.PageDto;
import com.nocountry.c930.dto.UserDto;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
    PageDto<UserDto> listAllUsers(Pageable page, HttpServletRequest request);

    UserDto getUser(Long id);

    boolean deleteUser(Long id);

    UserDto updateUser(Long id, UserRegistrationDto dto);
}
