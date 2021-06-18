package com.hrms.entities.concretes.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class JobPostingSubmitDto {

    @NotNull
    @NotBlank
    private String description;

    private String companyName;

    private int salaryMin;

    private int salaryMax;

    private String website;

    @Email
    private String email;

    private String phone;

    private int openPositionCount;

    private LocalDate deadline;

    private LocalDate publishedAt;

    private LocalDate createdAt;

    private boolean isActive;

    private boolean isDeleted;

    @NotNull
    private int jobPositionId;

    @NotNull
    private int candidateId;

    @NotNull
    private int workingPlaceId;

    @NotNull
    private int workingTimeId;

    @NotNull
    private int cityId;
}
