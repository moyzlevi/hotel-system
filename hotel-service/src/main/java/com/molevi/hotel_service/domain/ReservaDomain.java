package com.molevi.hotel_service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name = "reservas_quartos")
public class ReservaDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false, foreignKey = @ForeignKey(name = "fk_hotel_id"))
    private HotelDomain hotel;

    @Column(name = "nome_hospede", nullable = false)
    private String nomeHospede;

    @Column(name = "contato")
    private String contato;

    @Column(name = "detalhesPagamento", columnDefinition = "TEXT")
    private String detalhesPagamento;

    @Column(name = "inicio_reserva", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inicioReserva;

    @Column(name = "fim_reserva", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fimReserva;

    @Column(name = "status", columnDefinition = "TEXT")
    private String status;

    public ReservaDomain(HotelDomain hotel, String nomeHospede, String contato, String detalhesPagamento, Date inicioReserva, Date fimReserva) {
        this.hotel = hotel;
        this.nomeHospede = nomeHospede;
        this.contato = contato;
        this.detalhesPagamento = detalhesPagamento;
        this.inicioReserva = inicioReserva;
        this.fimReserva = fimReserva;
    }

    public ReservaDomain() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HotelDomain getHotel() {
        return hotel;
    }

    public void setHotel(HotelDomain hotel) {
        this.hotel = hotel;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getDetalhesPagamento() {
        return detalhesPagamento;
    }

    public void setDetalhesPagamento(String detalhesPagamento) {
        this.detalhesPagamento = detalhesPagamento;
    }

    public Date getInicioReserva() {
        return inicioReserva;
    }

    public void setInicioReserva(Date inicioReserva) {
        this.inicioReserva = inicioReserva;
    }

    public Date getFimReserva() {
        return fimReserva;
    }

    public void setFimReserva(Date fimReserva) {
        this.fimReserva = fimReserva;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
