package com.bookride.dto;

import java.time.LocalDateTime;

import com.bookride.model.Enum.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endTime;
    private String depatureAddress;
    private String destinationAddress;
    private Double price;
    private Double distance;
    private ReservationStatus reservationStatus;
    private VehicleDto vehicle;
}
