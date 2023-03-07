package com.nocountry.c930.service.impl;

import com.nocountry.c930.dto.ComplaintDto;
import com.nocountry.c930.entity.ComplaintEntity;
import com.nocountry.c930.entity.UserEntity;
import com.nocountry.c930.mapper.ComplaintMap;
import com.nocountry.c930.mapper.exception.ParamNotFound;
import com.nocountry.c930.repository.ComplaintRepository;
import com.nocountry.c930.repository.UserRepository;
import com.nocountry.c930.service.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImpl implements IComplaintService {

    @Override
    public ComplaintDto createComplaint(ComplaintDto dto) {

        return null;
    }

    @Override
    public ComplaintDto getComplaint(Long id) {
        

        return null;
    }

    @Override
    public ComplaintDto updateComplaint(Long id, ComplaintDto dto) {

        return dto;
    }

    @Override
    public void deleteComplaint(Long id) {
        
    }
}
