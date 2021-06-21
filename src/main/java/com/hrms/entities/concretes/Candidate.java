package com.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Entity
@Table(name = "candidates")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cv"})
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be empty.")
    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50.")
    private String name;

    @NotBlank(message = "Surname cannot be empty.")
    @Column(name = "surname", nullable = false)
    @Size(min = 3, max = 50, message = "Surname length should be between 3 and 50.")
    private String surname;

    @Column(name = "email", unique = true)
    @Email(message = "Wrong email format!")
    @NotEmpty
    @NotBlank(message = "Email can not be empty!")
    private String email;

    @Column(name = "password")
    @NotEmpty
    @NotBlank(message = "Password can not be empty!")
    @Size(min = 6, max = 10, message = "Password length should be between 6-10.")
    private String password;

    @NotBlank(message = "Password check cannot be empty.")
    @Column(name = "passwordCheck")
    @Size(min = 6, max = 10, message = "Password length should be between 6-10.")
    private String passwordCheck;

    @NotBlank(message = "National identity cannot be empty.")
    @Column(name = "national_identity", nullable = false, length = 11, unique = true)
    @Size(min = 11, max = 11, message = "National identity length must be 11.")
    @Pattern(regexp = "(^[1-9][0-9]*$)|(^\\d{10}$)", message = "National identity can't start with 0 or contain text.")
    private String nationalIdentity;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "verification_code", updatable = false)
    private String verificationCode;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private Cv cv;
}
