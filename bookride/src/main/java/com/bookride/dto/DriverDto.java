package com.bookride.dto;

import com.bookride.model.Enum.Status;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DriverDto {
    private int id;
    private String firstName;
    private String lastName;
    private Status status;
}
