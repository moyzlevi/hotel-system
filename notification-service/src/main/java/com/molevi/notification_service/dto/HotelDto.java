package com.molevi.notification_service.dto;

public record HotelDto(
        Long id,
        int numeroQuartos,
        String localizacao,
        double precoDiaria,
        String comodidades,
        double mediaAvaliacao
) {}
