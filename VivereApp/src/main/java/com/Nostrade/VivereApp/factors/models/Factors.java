package com.Nostrade.VivereApp.factors.models;

import com.Nostrade.VivereApp.profile.models.Profile;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "factors")
@Data
public class Factors {
    @Id
    @GeneratedValue
    private Long id;
    private boolean smokerCig;
    private boolean smokerWeed;
    private boolean meth;
    private boolean heroin;
    private boolean alcoholic;
    private boolean active;
    private boolean heart;
    private boolean cancer;
    private boolean stroke;
    private boolean respiratory;
    private boolean kidney;
    private boolean obese;
    private boolean hiv;
    private boolean trainDiet;
    private boolean highDiet;
    private boolean medDiet;
    private boolean lowDiet;


}