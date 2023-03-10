package com.nocountry.c930.entity;

import com.nocountry.c930.enumeration.RoleName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ROLES")
public class RoleEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private RoleName name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "role",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    private List<UserEntity> users = new ArrayList<>();
}
