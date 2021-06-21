package com.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_advert")
public class JobAdvert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private int jobId;

    @Size(min = 3, max = 50, message = "Description length should be between 3 and 50.")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Min salary cannot be empty.")
    @Column(name = "salary_min")
    private int salaryMin;

    @Column(name = "salary_max")
    @NotBlank(message = "Max salary cannot be empty.")
    private int salaryMax;

    @Size(min = 3, max = 50, message = "Position length should be between 3 and 50.")
    @Column(name = "open_position_count")
    private int openPositionCount;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "published_at")
    private LocalDate publishedAt;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "working_place_id")
    private WorkingPlace workingPlace;

    @ManyToOne
    @JoinColumn(name = "working_time_id")
    private WorkingTime workingTime;

    @ManyToOne
    private Employer employer;

}
