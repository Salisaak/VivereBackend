package com.Nostrade.VivereApp.profile.models;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "profile")
@Data

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String gender;
    private String race;
    private boolean hispanic;
    private String height;
    private double weight;
    private String birthday;

}