package com.bookride.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bookride.exceptions.QualificationException;
import com.bookride.model.Enum.NiveauQ;
import com.bookride.model.Enum.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Entity
@Table(name = "drivers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @Enumerated(EnumType.STRING)
    @NotNull
    private NiveauQ niveauQ;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime availabilityStart;

    @NotNull
    private int dureePermis;

    @OneToOne(mappedBy = "driver")
    private Vehicle vehicle;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime availabilityEnd;

    public void setNiveauQBasedOnDureePermis() {
        if (this.dureePermis < 0 || this.dureePermis > 40) {
            throw new QualificationException("Durée du permis invalide. Elle doit être comprise entre 0 et 40 ans.");
        }
        if (this.dureePermis < 2) {
            this.niveauQ = NiveauQ.SENIOR;
        } else if (this.dureePermis <= 5) {
            this.niveauQ = NiveauQ.SENIOR;
        } else {
            this.niveauQ = NiveauQ.EXPERT;
        }
    }
}
