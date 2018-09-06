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
//@Table(name = "Hotel_Stf")
public class HotelStf implements Serializable {

    // @XStreamConverter(DateConverter.class)
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Desde;
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Hasta;
    private Double PaxComplimentary;
    private Double PaxPagos;
    private Double PaxTotal;
    private Double RoomRevenue;
    private Double RoomComplimentary;
    private Double Extras;
    private Double SUM;
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

    public Double getPaxComplimentary() {
        return PaxComplimentary;
    }

    public void setPaxComplimentary(Double PaxComplimentary) {
        this.PaxComplimentary = PaxComplimentary;
    }

    public Double getPaxPagos() {
        return PaxPagos;
    }

    public void setPaxPagos(Double PaxPagos) {
        this.PaxPagos = PaxPagos;
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

    public Double getExtras() {
        return Extras;
    }

    public void setExtras(Double Extras) {
        this.Extras = Extras;
    }

    public Double getSUM() {
        return SUM;
    }

    public void setSUM(Double SUM) {
        this.SUM = SUM;
    }

}
