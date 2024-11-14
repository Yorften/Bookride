package com.bookride.mapper;

import org.mapstruct.Mapper;

import com.bookride.dto.DriverDto;
import com.bookride.model.Driver;


@Mapper(componentModel = "spring")
public interface DriverMapper {

   Driver toEntity(DriverDto driverDto);
   DriverDto toDto(Driver driver);
}
