package com.bookride.dto;

import java.time.LocalTime;

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
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime availabilityStart;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime availabilityEnd;
    private VehicleDto vehicle;
}
