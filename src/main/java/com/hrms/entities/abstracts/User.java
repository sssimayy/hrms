package com.hrms.entities.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    @Transient
    @NotBlank(message = "Password check cannot be empty.")
    @Column(nullable = false)
    @Size(min = 6, max = 30, message = "Password length should be between 6-30.")
    private String passwordCheck;

}
