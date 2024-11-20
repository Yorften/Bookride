package com.bookride.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bookride.dto.DriverDto;
import com.bookride.model.Driver;
import com.bookride.model.Enum.NiveauQ;
import com.bookride.service.DriverService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/drivers")
@RestController
@Validated
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/list")
    public List<DriverDto> getAllDrivers() {
        return driverService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable Long id) {
        DriverDto driver = driverService.getById(id);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DriverDto> createDriver(@RequestBody @Valid Driver driver) {
        DriverDto driverDto = driverService.create(driver);
        return new ResponseEntity<>(driverDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DriverDto> updateDriver(@PathVariable Long id, @RequestBody @Valid Driver driver) {
        DriverDto driverDto = driverService.update(id, driver);
        return new ResponseEntity<>(driverDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getDriverAnalytics() {
        Map<String, Object> analytics = driverService.getAnalytics();
        return ResponseEntity.ok(analytics);
    }
   
   
    @GetMapping("/qualifications/{niveau}")
    public ResponseEntity<List<DriverDto>> getDriversByQualification(@PathVariable NiveauQ niveau) {
        List<DriverDto> drivers = driverService.getDriversByQualification(niveau);
        return ResponseEntity.ok(drivers);
    }
}
