package com.nocountry.c930.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    private String paymentMethod;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PROFILE_IMG_URL")
    private String imageUrl;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "CREATION_DATE")
    @CreationTimestamp
    private Date creationDate;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    private Date updateDate;


    @OneToMany(mappedBy = "creator",
            fetch = FetchType.LAZY,
            cascade
                    = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private Set<CampaignEntity> ownedCampaigns;


    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private RoleEntity role;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade
                    = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private Set<CommentEntity> commentsMade;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade
                    = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private Set<DonationEntity> donationsMade;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade
                    = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private Set<ComplaintEntity> complaintsMade;

}
