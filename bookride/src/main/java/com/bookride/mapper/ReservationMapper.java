package com.bookride.mapper;

import org.mapstruct.Mapper;

import com.bookride.dto.ReservationDto;
import com.bookride.model.Reservation;


@Mapper(componentModel = "spring")
public interface ReservationMapper {
   Reservation toEntity(ReservationDto reservationDto);
   ReservationDto toDto(Reservation reservation);
}
