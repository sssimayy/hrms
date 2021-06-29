package com.hrms.entities.concretes.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CandidateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordCheck;

    private String nationalIdentity;

    private Date birthDate;

    private String verificationCode;
}
