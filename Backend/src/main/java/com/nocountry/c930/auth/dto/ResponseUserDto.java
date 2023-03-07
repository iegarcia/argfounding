package com.nocountry.c930.auth.dto;

import com.nocountry.c930.enumeration.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto {

  private Long id;
  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  private String imgUrl;
  @NotEmpty
  @Email(message = "the email must be real email")
  private String email;
  @NotEmpty
  private String password;
  private RoleName role;
  private Date creationDate;
  private Date updateDate;

}
