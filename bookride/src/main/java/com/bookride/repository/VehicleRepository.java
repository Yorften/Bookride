package com.bookride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookride.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
}
