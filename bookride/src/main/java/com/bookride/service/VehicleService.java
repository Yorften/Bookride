package com.bookride.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookride.dto.VehicleDto;
import com.bookride.mapper.VehicleMapper;
import com.bookride.model.Vehicle;
import com.bookride.repository.VehicleRepository;

import liquibase.repackaged.org.apache.commons.lang3.StringUtils;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    public VehicleDto getById(Long id) {
        Vehicle driver = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
         return vehicleMapper.toDto(driver);
     }
 
     public List<VehicleDto> getAll() {
         return vehicleRepository.findAll().stream()
                 .map(vehicleMapper::toDto)
                 .collect(Collectors.toList());
     }

    public VehicleDto create(Vehicle vehicle) {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toDto(savedVehicle);
    }

    public VehicleDto update(Long id, Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));

            if (StringUtils.isNotBlank(vehicle.getModel())) {
                existingVehicle.setModel(vehicle.getModel());
            }
            if (StringUtils.isNotBlank(vehicle.getRegistrationNumber())) {
                existingVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
            }
            if (vehicle.getMileage() != null) {
                existingVehicle.setMileage(vehicle.getMileage());
            }
            if (vehicle.getStatus() != null) {
                existingVehicle.setStatus(vehicle.getStatus());
            }
            if (vehicle.getVehicleType() != null) {
                existingVehicle.setVehicleType(vehicle.getVehicleType());
            }
            if (vehicle.getDriver() != null) {
                existingVehicle.setDriver(vehicle.getDriver());
            }

            Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
            return vehicleMapper.toDto(updatedVehicle);
        
    }

    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }
    
}

