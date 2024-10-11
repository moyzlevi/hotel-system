package com.molevi.hotel_service.controller.dto.response;

import java.math.BigDecimal;

public record SearchResponse(int numeroQuartos,
                             String localizacao,
                             BigDecimal precoDiaria,
                             String comodidades,
                             BigDecimal mediaAvaliacao,
                             Integer totalReservas) {
}
