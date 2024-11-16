package com.bookride.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookride.dto.VehicleAnalyticsDto;
import com.bookride.dto.VehicleDto;
import com.bookride.model.Vehicle;
import com.bookride.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * REST controller for managing Vehicle entities.
 * Handles HTTP requests and routes them to the appropriate service methods.
 */
@RestController // Marks this class as a RESTful controller.
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * Handles POST requests to save a new vehicle.
     * 
     * @param vehicle the vehicle entity to be saved
     * @return the saved vehicle entity
     */
    @Operation(summary = "Create a new vehicle", description = "Saves a new vehicle entity to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public VehicleDto saveVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.create(vehicle);
    }

    /**
     * Handles GET requests to fetch the list of all vehicles.
     * 
     * @return a list of vehicle entities
     */
    @Operation(summary = "Get all vehicles", description = "Fetches a list of all vehicles in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vehicles"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<VehicleDto> fetchVehicleList() {
        return vehicleService.getAll();
    }

    /**
     * Handles GET requests to fetch the list of all vehicles.
     * 
     * @return a list of vehicle entities
     */
    @Operation(summary = "Get a vehicle by ID", description = "Fetches a vehicle entity by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the vehicle"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public VehicleDto getVehicle(
            @Parameter(description = "ID of the vehicle to be retrieved") @PathVariable("id") Long vehicleId) {
        return vehicleService.getById(vehicleId);
    }

    /**
     * Handles GET requests to fetch the vehicles analytics.
     * 
     * @return analytics of vehicles
     */
    @Operation(summary = "Get vehicles analytics", description = "Fetches analytics about vehicles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the vehicle"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/analytics")
    public Map<String, VehicleAnalyticsDto> getVehicleAnalytics() {
        return vehicleService.getAnalytics();
    }

    /**
     * Handles PUT requests to update an existing vehicle.
     * 
     * @param vehicle   the vehicle entity with updated information
     * @param vehicleId the ID of the vehicle to be updated
     * @return the updated vehicle entity
     */
    @Operation(summary = "Update an existing vehicle", description = "Updates a vehicle entity identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public VehicleDto updateVehicle(
            @Parameter(description = "Updated vehicle data") @RequestBody Vehicle vehicle,
            @Parameter(description = "ID of the vehicle to be updated") @PathVariable("id") Long vehicleId) {

        return vehicleService.update(vehicleId, vehicle);
    }

    /**
     * Handles DELETE requests to remove a vehicle by ID.
     * 
     * @param vehicleId the ID of the vehicle to be deleted
     * @return a success message
     */
    @Operation(summary = "Delete a vehicle by ID", description = "Deletes a vehicle entity identified by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public String deleteVehicleById(
            @Parameter(description = "ID of the vehicle to be deleted") @PathVariable("id") Long vehicleId) {
        vehicleService.delete(vehicleId);
        return "Deleted Successfully";
    }

}
