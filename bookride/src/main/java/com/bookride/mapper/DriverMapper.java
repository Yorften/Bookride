package com.bookride.mapper;

import com.bookride.dto.DriverDto;
import com.bookride.model.Driver;

public interface  DriverMapper {
    DriverDto toDto(Driver driver);
    Driver toEntity(DriverDto driverDto);
}
