package com.nocountry.c930.service;

import com.nocountry.c930.dto.CampaignBasicDto;
import com.nocountry.c930.dto.DonationDto;
import com.nocountry.c930.dto.PageDto;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

public interface IDonationService {

    DonationDto createDonation(Long idCampaign, Long idDonationTier);

    DonationDto getDonation(Long id);

    DonationDto updateDonation(Long id, DonationDto dto);


    void deleteDonation(Long id);
    
}
