package com.bookride.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookride.dto.DriverDto;
import com.bookride.model.Driver;


@Mapper(componentModel = "spring")
public interface DriverMapper {
   
   Driver toEntity(DriverDto driverDto);

   @Mapping(target = "vehicle.driver", ignore = true)
   DriverDto toDto(Driver driver);
}
