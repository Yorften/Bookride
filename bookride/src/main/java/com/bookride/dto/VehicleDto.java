package com.bookride.dto;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.bookride.model.Enum.Status;
import com.bookride.model.Enum.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDto {
    private Long id;
    private String model;
    private String registrationNumber;
    private int mileage;
    private Status status;
    private VehicleType vehicleType;
    private DriverDto driver;
    @Pattern(regexp = "^ASS-[A-Z]{3}-[1-9]{3}$")
    private String insuranceNumber;
    private List<ReservationDto> reservations;
}
