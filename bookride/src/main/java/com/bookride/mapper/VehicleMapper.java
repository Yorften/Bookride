package com.bookride.mapper;

import org.mapstruct.Mapper;

import com.bookride.dto.VehicleDto;
import com.bookride.model.Vehicle;


@Mapper(componentModel = "spring")
public interface VehicleMapper {
   Vehicle toEntity(VehicleDto vehicleDto);
   VehicleDto toDto(Vehicle vehicle);
}
