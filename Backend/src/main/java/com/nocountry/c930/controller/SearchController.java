package com.nocountry.c930.controller;

import com.nocountry.c930.dto.CampaignBasicDto;
import com.nocountry.c930.enumeration.CampaignCategory;
import com.nocountry.c930.service.ICampaignService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns/search")
@CrossOrigin
public class SearchController {

    @Autowired
    ICampaignService campaignService;


    @GetMapping()
    @ApiOperation(value = "Searches between all campaign by keyword")
    public ResponseEntity<List<CampaignBasicDto>> searchAllByKeyword(@RequestParam(required = false) String keyword) {


        List<CampaignBasicDto> result = campaignService.searchCampaignsByKeyword(keyword);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/services")
    @ApiOperation(value = "Search services campaigns by keyword")
    public ResponseEntity<List<CampaignBasicDto>> searchServicesCampaigns(@RequestParam(required = false) String keyword) {

        List<CampaignBasicDto> result = campaignService.searchCampaignByCategoryAndKeyword(keyword, CampaignCategory.SERVICE);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/products")
    @ApiOperation(value = "Search product campaigns by keyword")
    public ResponseEntity<List<CampaignBasicDto>> searchProductCampaigns(@RequestParam(required = false) String keyword) {

        List<CampaignBasicDto> result = campaignService.searchCampaignByCategoryAndKeyword(keyword, CampaignCategory.PRODUCT);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/nearGoal")
    @ApiOperation(value = "List all the campaigns with 80% or more completion")
    public ResponseEntity<List<CampaignBasicDto>> getByNearGoal() {

        List<CampaignBasicDto> result = campaignService.listCampaignsNearGoal();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/mostPopular")
    @ApiOperation(value = "Filters all the campaign by donations recieved")
    public ResponseEntity<List<CampaignBasicDto>> getByMostPopular(@RequestParam(required = false) String keyword) {

        List<CampaignBasicDto> result = campaignService.listCampaignsByMostPopular(keyword);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/newest")
    @ApiOperation(value = "Gets all the campaigns by Date")
    public ResponseEntity<List<CampaignBasicDto>> getByNewest() {

        List<CampaignBasicDto> result = campaignService.listCampaignsByDate("DESC");

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
