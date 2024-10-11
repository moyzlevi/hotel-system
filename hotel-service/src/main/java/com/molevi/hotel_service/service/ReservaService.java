package com.molevi.hotel_service.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.molevi.hotel_service.util.ObjectToJson.toJson;

@Service
public record ReservaService(HotelRepository hotelRepository,
                             ReservaRepository reservaRepository,
                             ProducerSenderService producerSenderService,
                             @Value("${topic.notification}") String notificationTopic) {

    public void reservarQuarto(ReservarQuartoRequest request) {
        HotelDomain hotel = hotelRepository.findById(request.idHotel()).orElseThrow(HotelNotFoundException::new);

        Integer totalReservas = hotelRepository.totalReservasPorPeriodo(hotel.getId(), request.inicioReserva(), request.fimReserva());

        if(totalReservas>= hotel.getNumeroQuartos()) throw new NotEnoughRoomsException();

        reservaRepository.save(new ReservaDomain(hotel,
                                                request.nome(),
                                                request.contato(),
                                                request.detalhesPagamento(),
                                                request.inicioReserva(),
                                                request.fimReserva()));
    }

    public void alterarStatus(AlterarStatusRequest request) {
        var reserva = reservaRepository.findById(request.reservaId())
                .orElseThrow(ReservaNotFoundException::new);

        reserva.setStatus(request.status());

        ReservaDomain savedReserva = reservaRepository.save(reserva);

        producerSenderService.send(notificationTopic, toJson(savedReserva));
    }
}


