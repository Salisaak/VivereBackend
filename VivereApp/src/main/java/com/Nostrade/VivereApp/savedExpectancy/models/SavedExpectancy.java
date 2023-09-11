package com.Nostrade.VivereApp.savedExpectancy.models;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "savedExpectancy")
@Data
public class SavedExpectancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String gender;
    private String race;
    private boolean hispanic;
    private String birthday;
    private double value;
}