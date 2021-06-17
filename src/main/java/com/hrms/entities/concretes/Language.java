package com.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "language")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","cv"})
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foreignLanguages;

    @ManyToOne()
    @JoinColumn(name = "cv_id")
    private Cv cv;
}
