package com.molevi.hotel_service.repository;

import com.molevi.hotel_service.domain.HotelDomain;
import com.molevi.hotel_service.repository.view.HotelView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelDomain, Long> {

    @Query(value = "SELECT hotel.numero_quartos, hotel.localizacao, hotel.preco_diaria, hotel.comodidades, hotel.media_avaliacao" +
               "  , (SELECT COUNT(rqAll.id) as totalReservations FROM reservas_quartos rqAll " +
                    "WHERE rqAll.hotel_id = hotel.id AND ((rqAll.inicio_reserva < :dataCheckout AND rqAll.fim_reserva > :dataCheckin)) " +
                    " OR ((cast(:dataCheckout as date) IS NULL AND cast(:dataCheckin as date) IS NULL))) " +
            "FROM hoteis hotel " +
            " where (:numeroQuartos is null or hotel.numero_quartos = :numeroQuartos)" +
            " and (cast(:localizacao as TEXT) is null or hotel.localizacao = cast(:localizacao as TEXT))"
    , nativeQuery = true)
    List<HotelView> findHoteisDisponiveis(@Param("dataCheckin") Date dataCheckin,
                                              @Param("dataCheckout") Date dataCheckout,
                                              @Param("localizacao") String localizacao,
                                              @Param("numeroQuartos") BigDecimal numeroQuartos);

    @Query(value = "SELECT COUNT(rqAll.id) as totalReservations FROM reservas_quartos rqAll, hoteis hotel " +
                   "WHERE rqAll.hotel_id = hotel.id AND (rqAll.inicio_reserva < :dataCheckout AND rqAll.fim_reserva > :dataCheckin)" +
                   " AND rqAll.hotel_id = :hotelId"
            , nativeQuery = true)
    Integer totalReservasPorPeriodo(@Param("hotelId") Long hotelId,
                                                       @Param("dataCheckin") Date dataCheckin,
                                                       @Param("dataCheckout") Date dataCheckout);

    Page<HotelDomain> findAll(Pageable pageable);
}
