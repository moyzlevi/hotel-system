package com.molevi.hotel_service.service;

import com.molevi.hotel_service.controller.dto.request.AlterarStatusRequest;
import com.molevi.hotel_service.controller.dto.request.ReservarQuartoRequest;
import com.molevi.hotel_service.domain.HotelDomain;
import com.molevi.hotel_service.domain.ReservaDomain;
import com.molevi.hotel_service.entity.HotelEntity;
import com.molevi.hotel_service.exception.HotelNotFoundException;
import com.molevi.hotel_service.exception.NotEnoughRoomsException;
import com.molevi.hotel_service.exception.ReservaNotFoundException;
import com.molevi.hotel_service.repository.HotelRepository;
import com.molevi.hotel_service.repository.ReservaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public record PesquisaHotelService(HotelRepository hotelRepository,
                                   ReservaRepository reservaRepository) {

    public List<HotelEntity> procuraPorParametros(Date dataCheckin,
                                                 Date dataCheckout,
                                                 String localizacao,
                                                 BigDecimal numeroQuartos,
                                                 Integer minReservas) {

        return hotelRepository.findHoteisDisponiveis(
                dataCheckin,
                dataCheckout,
                localizacao,
                numeroQuartos)
                .stream()
                .filter(hotelView -> hotelView.getTotalReservations() >= minReservas)
                .map(HotelEntity::new)
                .toList();
    }

    public List<HotelEntity> comparaHoteis(String sortParam, int page, int size) {

        return hotelRepository.findAll(PageRequest.of(page, size, Sort.by(sortParam).ascending()))
                .stream()
                .map(HotelEntity::new)
                .toList();
    }
}
