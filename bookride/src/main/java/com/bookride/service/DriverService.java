package com.bookride.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookride.dao.DriverDao;
import com.bookride.dto.DriverDto;
import com.bookride.mapper.DriverMapper;
import com.bookride.model.Driver;
import com.bookride.repository.DriverRepository;

@Service
public class DriverService {

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverMapper driverMapper;

    public DriverDto create(DriverDto driverDto) {
        Driver driver = driverMapper.toEntity(driverDto);
        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toDto(savedDriver);
    }

    public DriverDto update(Long id, DriverDto driverDto) {
        Optional<Driver> existingDriverOpt = driverRepository.findById(id);

        if (existingDriverOpt.isPresent()) {
            Driver existingDriver = existingDriverOpt.get();
            existingDriver.setFirstName(driverDto.getFirstName());
            existingDriver.setLastName(driverDto.getLastName());
            existingDriver.setStatus(driverDto.getStatus());

            Driver updatedDriver = driverRepository.save(existingDriver);
            return driverMapper.toDto(updatedDriver);
        } else {
            throw new RuntimeException("Driver not found with ID: " + id);
        }
    }

    public void delete(Long id) {
        driverRepository.deleteById(id);
    }

    public DriverDto getById(Long id) {
       Driver driver = driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        return driverMapper.toDto(driver);
    }

    public List<DriverDto> getAll() {
        return driverRepository.findAll().stream()
                .map(driverMapper::toDto)
                .collect(Collectors.toList());
    }

    public double getOccupationRate() {
        return driverDao.calculateOccupationRate();
    }

    public List<Object[]> getAvailabilityAnalysis() {
        return driverDao.analyzeAvailabilityPeriods();
    }

    public List<Object[]> getStatusDistribution() {
        return driverDao.getStatusDistribution();
    }
}

