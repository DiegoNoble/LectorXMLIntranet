/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.hotel.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author dnoble
 */
@XStreamAlias("Table")
//@Entity
//@Table(name = "Hotel_Sal")
public class HotelSal implements Serializable {

    // @XStreamConverter(DateConverter.class)
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Desde;
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Hasta;
    private Double Ocupacion;
    private Double HabOcupadas;
    private Double PaxTotal;
    private Double RoomRevenue;
    private Double RoomComplimentary;
    private Double ExtrasComplimentary;
    private Double GastronomiaComplimentary;
    private Double Frigobar;
    private Double Telefonia;
    private Double Lavanderia;
    private Double Spa;
    private Double AlquilerSalones;
    private Double Otros;
    private String Nombre_Archivo;

    public String getNombre_Archivo() {
        return Nombre_Archivo;
    }

    public void setNombre_Archivo(String Nombre_Archivo) {
        this.Nombre_Archivo = Nombre_Archivo;
    }

    public Date getDesde() {
        return Desde;
    }

    public void setDesde(Date Desde) {
        this.Desde = Desde;
    }

    public Date getHasta() {
        return Hasta;
    }

    public void setHasta(Date Hasta) {
        this.Hasta = Hasta;
    }

    public Double getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(Double Ocupacion) {
        this.Ocupacion = Ocupacion;
    }

    public Double getHabOcupadas() {
        return HabOcupadas;
    }

    public void setHabOcupadas(Double HabOcupadas) {
        this.HabOcupadas = HabOcupadas;
    }

    public Double getPaxTotal() {
        return PaxTotal;
    }

    public void setPaxTotal(Double PaxTotal) {
        this.PaxTotal = PaxTotal;
    }

    public Double getRoomRevenue() {
        return RoomRevenue;
    }

    public void setRoomRevenue(Double RoomRevenue) {
        this.RoomRevenue = RoomRevenue;
    }

    public Double getRoomComplimentary() {
        return RoomComplimentary;
    }

    public void setRoomComplimentary(Double RoomComplimentary) {
        this.RoomComplimentary = RoomComplimentary;
    }

    public Double getExtrasComplimentary() {
        return ExtrasComplimentary;
    }

    public void setExtrasComplimentary(Double ExtrasComplimentary) {
        this.ExtrasComplimentary = ExtrasComplimentary;
    }

    public Double getGastronomiaComplimentary() {
        return GastronomiaComplimentary;
    }

    public void setGastronomiaComplimentary(Double GastronomiaComplimentary) {
        this.GastronomiaComplimentary = GastronomiaComplimentary;
    }

    public Double getFrigobar() {
        return Frigobar;
    }

    public void setFrigobar(Double Frigobar) {
        this.Frigobar = Frigobar;
    }

    public Double getTelefonia() {
        return Telefonia;
    }

    public void setTelefonia(Double Telefonia) {
        this.Telefonia = Telefonia;
    }

    public Double getLavanderia() {
        return Lavanderia;
    }

    public void setLavanderia(Double Lavanderia) {
        this.Lavanderia = Lavanderia;
    }

    public Double getSpa() {
        return Spa;
    }

    public void setSpa(Double Spa) {
        this.Spa = Spa;
    }

    public Double getAlquilerSalones() {
        return AlquilerSalones;
    }

    public void setAlquilerSalones(Double AlquilerSalones) {
        this.AlquilerSalones = AlquilerSalones;
    }

    public Double getOtros() {
        return Otros;
    }

    public void setOtros(Double Otros) {
        this.Otros = Otros;
    }

}
