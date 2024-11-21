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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "vehicles")
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

    @NotNull
    @Positive
    private Integer mileage;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Pattern(regexp = "^ASS-[A-Z]{3}-[1-9]{3}$")
    private String insuranceNumber;

    @Builder.Default
    private boolean deleted = Boolean.FALSE;

    @OneToOne
    @JoinColumn(name = "driver_id", nullable = true)
    private Driver driver;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
    
}
