package com.molevi.notification_service.feign;

import com.molevi.notification_service.dto.ReservaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${service.name}", url = "${service.url}")
public interface NotificationFeignClient {

    @PostMapping("/notification")
    void notificaClient(@RequestBody ReservaDto body);
}
