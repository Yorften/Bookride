package com.bookride.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookride.dto.VehicleDto;
import com.bookride.model.Vehicle;


@Mapper(componentModel = "spring", uses = {DriverMapper.class, ReservationMapper.class})
public interface VehicleMapper {
   @Mapping(target = "deleted", ignore = true)
   Vehicle toEntity(VehicleDto vehicleDto);

   @Mapping(target = "reservations.vehicle", ignore = true)
   VehicleDto toDto(Vehicle vehicle);
}
