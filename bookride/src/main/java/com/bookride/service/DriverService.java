package com.bookride.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public DriverDto create(Driver driver) {
        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toDto(savedDriver);
    }

    public DriverDto update(Long id, Driver driver) {
        Optional<Driver> existingDriverOpt = driverRepository.findById(id);

        if (existingDriverOpt.isPresent()) {
            Driver existingDriver = existingDriverOpt.get();
            existingDriver.setFirstName(driver.getFirstName());
            existingDriver.setLastName(driver.getLastName());
            existingDriver.setStatus(driver.getStatus());
            existingDriver.setAvailabilityStart(driver.getAvailabilityStart());
            existingDriver.setAvailabilityEnd(driver.getAvailabilityEnd());
            existingDriver.setVehicle(driver.getVehicle());

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
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
        return driverMapper.toDto(driver);
    }

    public List<DriverDto> getAll() {
        return driverRepository.findAll().stream()
                .map(driverMapper::toDto)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getAnalytics() {
        double occupationRate = driverDao.calculateOccupationRate();

        // Récupérer l'analyse des plages horaires de disponibilité
        List<Object[]> availabilityAnalysis = driverDao.analyzeAvailabilityPeriods();

        // Récupérer la répartition des statuts
        List<Object[]> statusDistribution = driverDao.getStatusDistribution();

        // Construire l'objet JSON de réponse
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("occupationRate", occupationRate);

        // Traiter l'analyse des plages horaires de disponibilité
        List<Map<String, Object>> availabilityList = new ArrayList<>();
        for (Object[] record : availabilityAnalysis) {
            Map<String, Object> availabilityData = new HashMap<>();
            availabilityData.put("start", record[0]); // Disponibilité début
            availabilityData.put("end", record[1]); // Disponibilité fin
            availabilityData.put("driverCount", record[2]); // Nombre de chauffeurs
            availabilityList.add(availabilityData);
        }
        analytics.put("availabilityAnalysis", availabilityList);

        // Traiter la répartition des statuts
        List<Map<String, Object>> statusList = new ArrayList<>();
        for (Object[] record : statusDistribution) {
            Map<String, Object> statusData = new HashMap<>();
            statusData.put("status", record[0]); // Statut du chauffeur
            statusData.put("count", record[1]); // Nombre de chauffeurs dans ce statut
            statusList.add(statusData);
        }
        analytics.put("statusDistribution", statusList);

        return analytics;

    }
}
