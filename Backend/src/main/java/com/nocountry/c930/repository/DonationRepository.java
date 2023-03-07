package com.nocountry.c930.repository;

import com.nocountry.c930.dto.DonationDto;
import com.nocountry.c930.entity.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<DonationEntity, Long> {



}
