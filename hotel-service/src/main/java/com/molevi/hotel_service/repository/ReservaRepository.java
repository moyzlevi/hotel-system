package com.molevi.hotel_service.repository;

import com.molevi.hotel_service.domain.HotelDomain;
import com.molevi.hotel_service.domain.ReservaDomain;
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
public interface ReservaRepository extends JpaRepository<ReservaDomain, Long> {
}
