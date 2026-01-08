package com.bivolaris.KavalaMed.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "annualcards")
public class AnnualCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardid")
    private Integer cardId;

    @NotNull
    @Column(name = "cardname")
    private String cardName;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "benefits")
    private String benefits;

    @Column(name = "validitymonths")
    private Integer validityMonths = 12;

    @OneToMany(mappedBy = "card")
    private Set<PatientAnnualCard> patientAnnualCards;
}