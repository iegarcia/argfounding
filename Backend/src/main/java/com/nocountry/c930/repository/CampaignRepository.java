package com.nocountry.c930.repository;

import com.nocountry.c930.entity.CampaignEntity;
import com.nocountry.c930.entity.UserEntity;
import com.nocountry.c930.enumeration.CampaignCategory;
import com.nocountry.c930.enumeration.CampaignStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampaignRepository extends JpaRepository<CampaignEntity, Long> {

    Page<CampaignEntity> findAllByStatus(CampaignStatus status, Pageable page);

    Page<CampaignEntity> findAll(Pageable pageable);


    @Query("SELECT c FROM CampaignEntity c WHERE c.currentMoney >= c.goalMoney * 0.8 AND c.currentMoney < c.goalMoney")
    List<CampaignEntity> findCampaignsNearGoal();

    List<CampaignEntity> findAllByOrderByCreationDateAsc();

    List<CampaignEntity> findAllByOrderByCreationDateDesc();

    @Query("SELECT c FROM CampaignEntity c WHERE (:keyword IS NULL OR LOWER(c.name) " +
            "LIKE %:keyword% OR LOWER(c.longDescription) LIKE %:keyword%)")
    List<CampaignEntity> findAllByKeyword(@Param("keyword") String keyword);

    @Query("SELECT c FROM CampaignEntity c WHERE c.category = :category " +
            "AND (:keyword IS NULL OR LOWER(c.name) " +
            "LIKE %:keyword% OR LOWER(c.longDescription) LIKE %:keyword%)")
    List<CampaignEntity> findServicesByKeyword(@Param("keyword") String keyword, @Param("category") CampaignCategory category);

    @Query("SELECT c FROM CampaignEntity c WHERE (:keyword IS NULL OR LOWER(c.name)LIKE %:keyword% OR LOWER(c.longDescription) LIKE %:keyword%) ORDER BY SIZE(c.donationsReceived) DESC ")
    List<CampaignEntity> findALLByMostPopular(@Param("keyword") String keyword);
}
