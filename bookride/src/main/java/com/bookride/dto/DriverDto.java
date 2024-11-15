package com.bookride.dto;

import java.time.LocalTime;

import com.bookride.model.Enum.Status;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Status status;
    private LocalTime disponibiliteDebut;
    private LocalTime disponibiliteFin;
}
