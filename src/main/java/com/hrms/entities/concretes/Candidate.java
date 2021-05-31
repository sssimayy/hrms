package com.hrms.entities.concretes;

import com.hrms.entities.abstracts.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "candidates")
@PrimaryKeyJoinColumn(name = "user_id")
@EqualsAndHashCode(callSuper = true)
public class Candidate extends User {

    @NotBlank(message = "Name cannot be empty.")
    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50.")
    private String name;

    @NotBlank(message = "Surname cannot be empty.")
    @Column(name = "surname", nullable = false)
    @Size(min = 3, max = 50, message = "Surname length should be between 3 and 50.")
    private String surname;

    @NotBlank(message = "National identity cannot be empty.")
    @Column(name = "national_identity", nullable = false, length = 11, unique = true)
    @Size(min = 11, max = 11, message = "National identity length must be 11.")
    @Pattern(regexp = "(^[1-9][0-9]*$)|(^\\d{10}$)", message = "National identity can't start with 0 or contain text.")
    private String nationalIdentity;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "enabled")
    private boolean enabled = false;

    @Column(name = "verification_code", updatable = false)
    private String verificationCode;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Cv cv;
}
