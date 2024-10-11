package com.molevi.notification_service.dto;

import java.util.Date;

public record ReservaDto(
        Long id,
        HotelDto hotel,
        String nomeHospede,
        String contato,
        String detalhesPagamento,
        Date inicioReserva,
        Date fimReserva,
        String status
) {}

