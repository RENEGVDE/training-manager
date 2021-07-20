package com.rusnac.clubrestapi.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ex_id")
    private Long exId;
    @Column(name = "ex_name", updatable = false)
    private String exName;
    @Column(name = "ex_calories", updatable = false)
    private double exCalories;
    @OneToMany(targetEntity = Training.class, mappedBy = "exId",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Training> training;

//    @OneToMany(targetEntity = Training.class, mappedBy = "calories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Training> trainings;

    public Exercise() {

    }

    public Exercise(String exId) {
        this.exId = Long.valueOf(exId);
    }

    public Exercise(Long exId, String exName, double exCalories) {
        this.exId = exId;
        this.exName = exName;
        this.exCalories = exCalories;
    }

    public Long getExId() {
        return exId;
    }

    public void setExId(Long exId) {
        this.exId = exId;
    }

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public double getExCalories() {
        return exCalories;
    }

    public void setExCalories(double exCalories) {
        this.exCalories = exCalories;
    }


}
