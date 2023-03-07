package com.nocountry.c930.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "COMPLAINTS")
public class ComplaintEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPLAINT_ID")
    private Long complaintId;

    @Column( name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "USER")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "CAMPAIGN")
    private CampaignEntity campaign;
}
