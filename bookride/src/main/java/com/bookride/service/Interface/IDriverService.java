package com.bookride.service.Interface;

import java.util.List;
import java.util.Optional;

import com.bookride.model.Driver;

public interface IDriverService {
    Driver createDriver(Driver driver);
    Driver updateDriver(Long id, Driver driver);
    void deleteDriver(Long id);
    Optional<Driver> getDriverById(Long id);
    List<Driver> getAllDrivers();
}
