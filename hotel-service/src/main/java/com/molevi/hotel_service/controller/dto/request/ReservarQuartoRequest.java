package com.molevi.hotel_service.controller.dto.request;

import java.util.Date;

public record ReservarQuartoRequest(Long idHotel,
                                    Date inicioReserva,
                                    Date fimReserva,
                                    String nome,
                                    String contato,
                                    String detalhesPagamento) {
}
