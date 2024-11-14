package com.bookride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookride.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    
}
