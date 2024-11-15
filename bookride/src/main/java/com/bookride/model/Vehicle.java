package com.bookride.model;

import com.bookride.model.Enum.Status;
import com.bookride.model.Enum.VehicleType;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Builder
@SQLDelete(sql = "UPDATE vehicles SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String model;

    @NotBlank
    private String registrationNumber;

    @NotBlank
    private int mileage;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
    
}
