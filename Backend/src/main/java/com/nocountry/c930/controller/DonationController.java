package com.nocountry.c930.controller;

import com.nocountry.c930.dto.CampaignBasicDto;
import com.nocountry.c930.dto.DonationDto;
import com.nocountry.c930.dto.PageDto;
import com.nocountry.c930.service.IDonationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/donations")
public class DonationController {



}
