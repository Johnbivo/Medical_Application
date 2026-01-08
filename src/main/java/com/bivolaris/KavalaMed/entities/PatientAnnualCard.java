package com.bivolaris.KavalaMed.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "patientannualcards")
public class PatientAnnualCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patientcardid")
    private Integer patientCardId;

    @ManyToOne
    @JoinColumn(name = "patientid")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "cardid")
    private AnnualCard card;

    @NotNull
    @Column(name = "purchasedate")
    private LocalDate purchaseDate;

    @NotNull
    @Column(name = "expirydate")
    private LocalDate expiryDate;

    @Column(name = "active")
    private Boolean active = true;
}