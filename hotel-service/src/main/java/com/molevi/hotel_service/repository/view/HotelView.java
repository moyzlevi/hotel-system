package com.molevi.hotel_service.repository.view;

import java.math.BigDecimal;

public interface HotelView {
    Integer getNumeroQuartos();
    String getLocalizacao();
    BigDecimal getPrecoDiaria();
    String getComodidades();
    BigDecimal getMediaAvaliacao();
    Integer getTotalReservations();
    Integer setNumeroQuartos(Integer numeroQuartos);
    String setLocalizacao(String localizacao);
    BigDecimal setPrecoDiaria(BigDecimal precoDiaria);
    String setComodidades(String comodidades);
    BigDecimal setMediaAvaliacao(BigDecimal mediaAvaliacao);
    Integer setTotalReservations(Integer totalReservations);
}
