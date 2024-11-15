package com.bookride.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime availabilityStart;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime availabilityEnd;

    @OneToOne(mappedBy = "driver")
    private Vehicle vehicle;

}
