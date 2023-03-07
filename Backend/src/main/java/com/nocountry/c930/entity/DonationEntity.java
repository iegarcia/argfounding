package com.nocountry.c930.entity;

import com.nocountry.c930.enumeration.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "DONATIONS")
public class DonationEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DONATION_ID")
    private Long donationId;

    @Column(name = "CREATION_DATE", nullable = false)
    @CreationTimestamp
    private Date creationDate;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "TIER_NAME")
    private String tierName;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "USER")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "CAMPAIGN")
    private CampaignEntity campaign;



}
