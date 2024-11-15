package com.bookride.dto;

import java.time.LocalDateTime;

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
    private LocalDateTime availabilityStart;
    private LocalDateTime availabilityEnd;
    private VehicleDto vehicle;
}
