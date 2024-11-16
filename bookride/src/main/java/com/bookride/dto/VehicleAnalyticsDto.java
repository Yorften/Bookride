package com.bookride.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VehicleAnalyticsDto {
    private Double averageMileage;
    private Double usageRate;
    private long fleetStatus;
}
