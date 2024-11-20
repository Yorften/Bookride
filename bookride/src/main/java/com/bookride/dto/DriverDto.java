package com.bookride.dto;

import java.time.LocalDateTime;

import com.bookride.model.Enum.NiveauQ;
import com.bookride.model.Enum.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime availabilityStart;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime availabilityEnd;
    private VehicleDto vehicle;
    private int dureePermis;
    private NiveauQ niveauQ;
}
