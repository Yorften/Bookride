package com.bookride.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookride.dao.VehicleDao;
import com.bookride.dto.VehicleAnalyticsDto;
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
    private VehicleDao vehicleDao;

    @Autowired
    private VehicleMapper vehicleMapper;

    public VehicleDto getById(Long id) {
        Vehicle driver = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        return vehicleMapper.toDto(driver);
    }

    public List<VehicleDto> getAll() {
        return vehicleRepository.findAll().stream()
                .map(vehicleMapper::toDto)
                .collect(Collectors.toList());
    }

    public Map<String, VehicleAnalyticsDto> getAnalytics() {
        Map<String, VehicleAnalyticsDto> analyticsMap = new HashMap<>();

        List<Object[]> mileageAnaltytics = vehicleDao.getAverageMileageByVehicleType();
        List<Object[]> usageRateAnaltytics = vehicleDao.getAverageUsageRateByVehicleType();
        List<Object[]> fleetAnaltytics = vehicleDao.getFleetStatusByVehicleType();

        analyticsMap.put("SEDAN",
                VehicleAnalyticsDto.builder().averageMileage((Double) mileageAnaltytics.get(0)[1]).usageRate((Double) usageRateAnaltytics.get(0)[1]).fleetStatus((long) fleetAnaltytics.get(0)[1]).build());
        analyticsMap.put("MINIBUS",
                VehicleAnalyticsDto.builder().averageMileage((Double) mileageAnaltytics.get(1)[1]).usageRate((Double) usageRateAnaltytics.get(1)[1]).fleetStatus((long) fleetAnaltytics.get(1)[1]).build());
        analyticsMap.put("VAN",
                VehicleAnalyticsDto.builder().averageMileage((Double) mileageAnaltytics.get(2)[1]).usageRate((Double) usageRateAnaltytics.get(2)[1]).fleetStatus((long) fleetAnaltytics.get(2)[1]).build());
        return analyticsMap;
    }

    public VehicleDto create(Vehicle vehicle) {
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toDto(savedVehicle);
    }

    public VehicleDto update(Long id, Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));

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
