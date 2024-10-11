package com.molevi.notification_service.consumer;

import com.molevi.notification_service.dto.ReservaDto;
import com.molevi.notification_service.service.NotificaClienteService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.molevi.notification_service.util.JsonUtils.fromJson;

@Service
public record RequestPaymentListener(NotificaClienteService notificaClienteService) {

    @KafkaListener(topics = "${topic.notification}")
    public void listen(String notificationReserva) {
        ReservaDto reservaDto = fromJson(notificationReserva, ReservaDto.class);

        this.notificaClienteService.notificaCliente(reservaDto);
    }
}
