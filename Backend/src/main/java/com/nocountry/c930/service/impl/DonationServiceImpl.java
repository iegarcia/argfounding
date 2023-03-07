package com.nocountry.c930.service.impl;

import com.nocountry.c930.dto.CampaignBasicDto;
import com.nocountry.c930.dto.CampaignDto;
import com.nocountry.c930.dto.DonationDto;
import com.nocountry.c930.dto.PageDto;
import com.nocountry.c930.entity.CampaignEntity;
import com.nocountry.c930.entity.DonationEntity;
import com.nocountry.c930.entity.DonationTierEntity;
import com.nocountry.c930.entity.UserEntity;
import com.nocountry.c930.enumeration.CampaignStatus;
import com.nocountry.c930.enumeration.PaymentStatus;
import com.nocountry.c930.mapper.DonationMap;
import com.nocountry.c930.mapper.exception.ParamNotFound;
import com.nocountry.c930.repository.CampaignRepository;
import com.nocountry.c930.repository.DonationRepository;
import com.nocountry.c930.repository.DonationTierRepository;
import com.nocountry.c930.repository.UserRepository;
import com.nocountry.c930.service.ICampaignService;
import com.nocountry.c930.service.IDonationService;
import com.nocountry.c930.service.IUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class DonationServiceImpl implements IDonationService {

    @Autowired
    private DonationRepository donationRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CampaignRepository campaignRepo;

    @Autowired
    private DonationTierRepository donationTierRepo;

    @Autowired
    private DonationMap donationMap;

    @Autowired
    private IUtilService util;
    @Autowired
    private ICampaignService campaignService;

    @Override
    public DonationDto createDonation(Long idCampaign, Long idDonationTier) {

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByEmail(userEmail);

        CampaignEntity campaign = campaignRepo.findById(idCampaign).orElseThrow(
                () -> new ParamNotFound("Campaign doesn't exist.")
        );

        DonationTierEntity donationTier = donationTierRepo.findById(idDonationTier).orElseThrow(
                () -> new ParamNotFound("Donation tier doesn't exist.")
        );

        DonationEntity donation = new DonationEntity();

        donation.setStatus(PaymentStatus.SUCCESS);
        donation.setAmount(donationTier.getPrice());
        donation.setTierName(donationTier.getTierName());
        donation.setUser(user);
        donation.setCampaign(campaign);
        donationRepo.save(donation);

        donationTier.setAmountsBought(donationTier.getAmountsBought() + 1);
        donationTierRepo.save(donationTier);
        campaignService.updateCampaignMoney(idCampaign);

        return donationMap.donationEntity2Dto(donationRepo.save(donation));
    }

    @Override
    public DonationDto getDonation(Long id) {


        return null;
    }

    @Override
    public DonationDto updateDonation(Long id, DonationDto dto) {

        return dto;
    }

    @Override
    public void deleteDonation(Long id) {

    }

}
