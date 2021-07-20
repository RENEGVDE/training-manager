package com.rusnac.clubrestapi.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

import static java.lang.Math.round;

@Entity
@Table(name="training")
public class Training {

    @Id
    @GeneratedValue
    @Column(name = "tr_id")
    private Long id;
    @NotNull
    @Column(name = "tr_duration")
    private double trDuration;
    @NotNull
    @Column(name = "tr_position")
    private String trPosition;
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ex_id")
    private Exercise exId;
    @NotNull
    @Column(name = "tr_calories")
    private double totCalories;

    public Training() {

    }

    public Training(double trDuration, String trPosition, Exercise exId) {
        this.trDuration = trDuration;
        this.trPosition = trPosition;
        this.exId = exId;
    }

    public Training(Long id, double trDuration, String trPosition, Exercise exId, double totCalories) {
        this.id = id;
        this.trDuration = trDuration;
        this.trPosition = trPosition;
        this.exId = exId;
        this.totCalories = totCalories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTrDuration() {
        return trDuration;
    }

    public void setTrDuration(double trDuration) {
        this.trDuration = trDuration;
    }

    public String getTrPosition() {
        return trPosition;
    }

    public void setTrPosition(String trPosition) {
        this.trPosition = trPosition;
    }

    public Exercise getExId() {
        return exId;
    }

    public void setExId(Exercise exId) {
        this.exId = exId;
    }

//    public void setTotCalories(double totCalories) {
////        double dCalories = this.getTrDuration() * this.exId.getExCalories();
////        this.totCalories = Math.round(dCalories * 100.0)/ 100.0;
//        this.totCalories = totCalories;
//    }

    public double getTotCalories() {
        double dCalories = this.trDuration * this.exId.getExCalories();
        totCalories = Math.round(dCalories * 100.0)/ 100.0;
        return totCalories;
    }

}
