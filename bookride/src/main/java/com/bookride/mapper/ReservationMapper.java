package com.bookride.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bookride.dto.ReservationDto;
import com.bookride.model.Reservation;


@Mapper(componentModel = "spring", uses = {DriverMapper.class})
public interface ReservationMapper {
   @Mapping(target = "vehicle.deleted", ignore = true)
   Reservation toEntity(ReservationDto reservationDto);

   @Mapping(target = "vehicle.reservations", ignore = true)
   ReservationDto toDto(Reservation reservation);
}
