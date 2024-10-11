package com.molevi.notification_service.service;

import com.molevi.notification_service.dto.ReservaDto;
import com.molevi.notification_service.feign.NotificationFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public record NotificaClienteService(NotificationFeignClient notificationFeignClient) {
    private static final Logger logger = LoggerFactory.getLogger(NotificaClienteService.class);

    public void notificaCliente(ReservaDto reservaDto) {
        notificationFeignClient.notificaClient(reservaDto);
        logger.info("[NOTIFICA-CLIENTE-SERVICE] - Cliente notificado com sucesso");
    }
}
