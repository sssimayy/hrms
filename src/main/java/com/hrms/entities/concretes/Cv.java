package com.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cvs")
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String universityName;

    private String department;

    private Date gradDate;

    private String firmName;

    private String position;

    private Date experienceDate;

    private int languageLevel;

    private String githubAddress;

    private String linkedinAddress;

    private String programmingLanguages;

    private String coverLetter;

    @OneToOne(mappedBy = "cv")
    private Candidate candidate;


}
