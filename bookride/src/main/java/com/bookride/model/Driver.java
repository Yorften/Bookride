package com.bookride.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private Status status;

    @NotNull
    private LocalDateTime availabilityStart;

    @NotNull
    private LocalDateTime availabilityEnd;

    @OneToOne(mappedBy = "driver")
    private Vehicle vehicle;

}
