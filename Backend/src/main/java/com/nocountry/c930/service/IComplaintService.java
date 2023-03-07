package com.nocountry.c930.service;

import com.nocountry.c930.dto.ComplaintDto;

public interface IComplaintService {

    ComplaintDto createComplaint(ComplaintDto dto);

    ComplaintDto getComplaint(Long id);

    ComplaintDto updateComplaint(Long id, ComplaintDto dto);

    void deleteComplaint(Long id);
}
