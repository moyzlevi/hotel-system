package com.molevi.hotel_service.controller;

import com.molevi.hotel_service.controller.dto.request.AlterarStatusRequest;
import com.molevi.hotel_service.controller.dto.request.ReservarQuartoRequest;
import com.molevi.hotel_service.controller.dto.response.SearchResponse;
import com.molevi.hotel_service.entity.HotelEntity;
import com.molevi.hotel_service.exception.HotelNotFoundException;
import com.molevi.hotel_service.exception.NotEnoughRoomsException;
import com.molevi.hotel_service.exception.ReservaNotFoundException;
import com.molevi.hotel_service.service.PesquisaHotelService;
import com.molevi.hotel_service.service.ReservaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public record HotelController(PesquisaHotelService pesquisaHotelService,
                              ReservaService reservaService) {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    @GetMapping("/hotel")
    public ResponseEntity<List<SearchResponse>> procuraHotelPorParams(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataCheckin,
                                                                     @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataCheckout,
                                                                     @RequestParam(required = false) String localizacao,
                                                                     @RequestParam(required = false) BigDecimal numeroQuartos,
                                                                     @RequestParam(required = false, defaultValue = "0") Integer minReservas,
                                                                     @RequestParam Map<String, String> params) {

        if(params.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        var resposta = pesquisaHotelService.procuraPorParametros(dataCheckin, dataCheckout, localizacao, numeroQuartos, minReservas)
                .stream().map(HotelEntity::out).toList();

        logger.info("[HOTEL-CONTROLLER] - Hotel pesquisado com sucesso!");
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/comparar-hotel")
    public ResponseEntity<List<SearchResponse>> compararHoteis(@RequestParam() String sortParam,
                                                               @RequestParam(defaultValue = "0") Integer page,
                                                               @RequestParam(defaultValue = "5") Integer size) {
        var resposta = pesquisaHotelService.comparaHoteis(sortParam, page, size)
                .stream().map(HotelEntity::out).toList();

        logger.info("[HOTEL-CONTROLLER] - Hotel comparado com sucesso!");
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @PostMapping("/reservar-quarto")
    public ResponseEntity<List<SearchResponse>> reservarQuarto(@RequestBody ReservarQuartoRequest request) {
        try {
            reservaService.reservarQuarto(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (HotelNotFoundException exception) {
            logger.warn("[HOTEL-CONTROLLER] - Uma tentativa de reserva de hotel inexistente foi feita!");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NotEnoughRoomsException exception) {
            logger.warn("[HOTEL-CONTROLLER] - Uma tentativa de reserva sem quartos suficientes foi feita!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update-status")
    public ResponseEntity alterarStatus(@RequestBody AlterarStatusRequest request) {
        try {
            reservaService.alterarStatus(request);
            logger.warn("[HOTEL-CONTROLLER] - Uma tentativa de alterar status de reserva foi feita!");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ReservaNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //endpoint para simular a chamada para outro servidor do servico de notificacao, ele deveria estar em uma api externa
    @PostMapping("/notification")
    public ResponseEntity notification() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
