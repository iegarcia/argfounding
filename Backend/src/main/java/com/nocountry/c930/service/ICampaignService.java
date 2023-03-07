package com.nocountry.c930.service;


import com.nocountry.c930.dto.*;
import com.nocountry.c930.enumeration.CampaignCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ICampaignService {

    CampaignBasicDto createCampaign(CampaignCreationDto dto) throws IOException;

    CampaignDto getCampaign(Long id);

    PageDto<CampaignBasicDto> listAllCampaigns(Pageable page, HttpServletRequest request);

    List<CampaignBasicDto> listCampaignsNearGoal();

    List<CampaignBasicDto> listCampaignsByDate(String order);

    List<CampaignBasicDto> searchCampaignsByKeyword(String keyword);

    List<CampaignBasicDto> searchCampaignByCategoryAndKeyword(String keyword, CampaignCategory category);

    List<CampaignBasicDto> listCampaignsByMostPopular(String keyword);

    List<CampaignBasicDto> listCampaignByUser(Long idUser);

    void replaceDescriptionImages(Long idCampaign, UpdateImagesDto images) throws IOException;

    CampaignDto updateCampaign(Long id, CampaignCreationDto dto) throws IOException;

    Set<DonationDto> findAllDonations(Long id);

    void updateCampaignMoney(Long idCampaign);

    void deleteCampaign(Long id);

    void replaceTierImages(Long idCampaign, UpdateTierImagesDto images) throws IOException;
}
