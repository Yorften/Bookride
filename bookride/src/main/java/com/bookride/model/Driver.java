package com.bookride.model;

import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.bookride.model.Enum.Status;

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
    private Status status;

    private LocalTime disponibiliteDebut;
    private LocalTime disponibiliteFin;

}
