package com.hrms.entities.concretes.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CandidateDto {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surname;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String passwordCheck;

    @NotNull
    private String nationalIdentity;

    @NotNull
    private Date birthDate;

    @NotNull
    private String verificationCode;
}
