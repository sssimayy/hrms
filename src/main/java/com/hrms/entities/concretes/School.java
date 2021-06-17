package com.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "school")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","cv"})
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String universityName;

    private String department;

    private Date gradDate;

    @ManyToOne()
    @JoinColumn(name = "cv_id")
    private Cv cv;
}
