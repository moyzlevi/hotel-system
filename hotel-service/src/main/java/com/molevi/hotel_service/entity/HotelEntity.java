package com.molevi.hotel_service.entity;

import com.molevi.hotel_service.controller.dto.response.SearchResponse;
import com.molevi.hotel_service.domain.HotelDomain;
import com.molevi.hotel_service.repository.view.HotelView;

import java.math.BigDecimal;

public class HotelEntity {

    private int numeroQuartos;
    private String localizacao;
    private BigDecimal precoDiaria;
    private String comodidades;
    private BigDecimal mediaAvaliacao;
    private Integer totalReservas;

    public HotelEntity(HotelView hotelView) {
        this.numeroQuartos = hotelView.getNumeroQuartos();
        this.localizacao = hotelView.getLocalizacao();
        this.precoDiaria = hotelView.getPrecoDiaria();
        this.comodidades = hotelView.getComodidades();
        this.mediaAvaliacao = hotelView.getMediaAvaliacao();
        this.totalReservas = hotelView.getTotalReservations();
    }

    public HotelEntity(HotelDomain hotelDomain) {
        this.numeroQuartos = hotelDomain.getNumeroQuartos();
        this.localizacao = hotelDomain.getLocalizacao();
        this.precoDiaria = hotelDomain.getPrecoDiaria();
        this.comodidades = hotelDomain.getComodidades();
        this.mediaAvaliacao = hotelDomain.getMediaAvaliacao();
    }

    public SearchResponse out() {
        return new SearchResponse(this.numeroQuartos,
                                  this.localizacao,
                                  this.precoDiaria,
                                  this.comodidades,
                                  this.mediaAvaliacao,
                                  this.totalReservas);
    }
}
