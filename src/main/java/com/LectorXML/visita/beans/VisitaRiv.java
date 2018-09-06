/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.visita.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author dnoble
 */
@XStreamAlias("registro")
//@Entity
//@Table(name = "Visita_Riv")
public class VisitaRiv implements Serializable {

    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fecha_produccion")
    private Date fecha;
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_calendario;
    @Id
    private Integer hora;
    private Integer ingreso;
    private Integer egreso;
    private Integer stock;
    private Integer acumulado;
    @Id
    @Column(length = 2)
    private String sala;

    private String Nombre_Archivo;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha_calendario() {
        return fecha_calendario;
    }

    public void setFecha_calendario(Date fecha_calendario) {
        this.fecha_calendario = fecha_calendario;
    }
    
    
    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getIngreso() {
        return ingreso;
    }

    public void setIngreso(Integer ingreso) {
        this.ingreso = ingreso;
    }

    public Integer getEgreso() {
        return egreso;
    }

    public void setEgreso(Integer egreso) {
        this.egreso = egreso;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(Integer acumulado) {
        this.acumulado = acumulado;
    }

    public String getNombre_Archivo() {
        return Nombre_Archivo;
    }

    public void setNombre_Archivo(String Nombre_Archivo) {
        this.Nombre_Archivo = Nombre_Archivo;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

}
