package com.hrms.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobAdvert"})
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 50, message = "Company name length should be between 3 and 50.")
    @NotBlank(message = "Company name cannot be empty.")
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "position")
    private String position;

    @Column(name = "web_site")
    @NotBlank
    private String website;

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "phone")
    @NotBlank
    private String phone;

    @Column(name = "password")
    @NotBlank
    private String password;

    @Column(name = "waiting_update")
    private boolean waitingUpdate;

    @OneToMany(mappedBy = "employer")
    private List<JobAdvert> jobAdvert;
}