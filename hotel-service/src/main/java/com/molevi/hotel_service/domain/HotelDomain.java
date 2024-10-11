package com.molevi.hotel_service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "hoteis")
public class HotelDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_quartos")
    private int numeroQuartos;

    @Column(name = "localizacao", columnDefinition = "TEXT")
    private String localizacao;

    @Column(name = "preco_diaria")
    private BigDecimal precoDiaria;

    @Column(name = "comodidades")
    private String comodidades;

    @Column(name = "media_avaliacao")
    private BigDecimal mediaAvaliacao;

    public HotelDomain(Long id, int numeroQuartos, String localizacao, BigDecimal precoDiaria, String comodidades, BigDecimal mediaAvaliacao) {
        this.id = id;
        this.numeroQuartos = numeroQuartos;
        this.localizacao = localizacao;
        this.precoDiaria = precoDiaria;
        this.comodidades = comodidades;
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(int numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public BigDecimal getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(BigDecimal precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public BigDecimal getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(BigDecimal mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }
}

