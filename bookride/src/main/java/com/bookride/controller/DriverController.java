package com.bookride.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bookride.dto.DriverDto;
import com.bookride.model.Driver;
import com.bookride.service.DriverService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/chauffeurs")
@RestController
@Validated
@RequiredArgsConstructor
public class DriverController {
    
    private final DriverService driverService;

    @GetMapping("/list")
    public List<DriverDto> getAllDrivers(){
        List<DriverDto> driver = driverService.getAll();
        return driver;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable Long id){
        DriverDto driver = driverService.getById(id);
        return new ResponseEntity<>(driver,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DriverDto> createDriver(@RequestBody @Valid Driver driver ){
        DriverDto driverDto = driverService.create(driver);
        return new ResponseEntity<>(driverDto,HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DriverDto> updateDriver(@PathVariable Long id , @RequestBody @Valid Driver driver ){
        DriverDto driverDto = driverService.update(id, driver);
        return new ResponseEntity<>(driverDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DriverDto> deleteDriver(@PathVariable Long id){
        driverService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/analytics")
    public ResponseEntity<List<Object[]>> getDriverAnalytics() {
        List<Object[]> analytics = driverService.getAvailabilityAnalysis();
        return new ResponseEntity<>(analytics, HttpStatus.OK);
    }
}
