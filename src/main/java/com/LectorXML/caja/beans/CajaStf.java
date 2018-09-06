/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.caja.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author dnoble
 */
@XStreamAlias("datosdia")
//@Entity
//@Table(name = "Caja_Stf")
public class CajaStf {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // @XStreamConverter(DateConverter.class)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechases;
    private String concepto;
    private Double propmesa;
    private Double propcaja;
    private Double propbing;
    private Double dropmesa;
    private Double dropefec;
    private Double dropdivi;
    private Double droptc;
    private Double nodroptc;
    private String mesa;
    private Double efectivo;
    private Double resultat;
    private String Nombre_Archivo;

    public Date getFechases() {
        return fechases;
    }

    public void setFechases(Date fechases) {
        this.fechases = fechases;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getPropmesa() {
        return propmesa;
    }

    public void setPropmesa(Double propmesa) {
        this.propmesa = propmesa;
    }

    public Double getPropcaja() {
        return propcaja;
    }

    public void setPropcaja(Double propcaja) {
        this.propcaja = propcaja;
    }

    public Double getPropbing() {
        return propbing;
    }

    public void setPropbing(Double propbing) {
        this.propbing = propbing;
    }

    public Double getDropmesa() {
        return dropmesa;
    }

    public void setDropmesa(Double dropmesa) {
        this.dropmesa = dropmesa;
    }

    public Double getDropefec() {
        return dropefec;
    }

    public void setDropefec(Double dropefec) {
        this.dropefec = dropefec;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public Double getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(Double efectivo) {
        this.efectivo = efectivo;
    }

    public Double getResultat() {
        return resultat;
    }

    public void setResultat(Double resultat) {
        this.resultat = resultat;
    }

    public Double getDropdivi() {
        return dropdivi;
    }

    public void setDropdivi(Double dropdivi) {
        this.dropdivi = dropdivi;
    }

    public String getNombre_Archivo() {
        return Nombre_Archivo;
    }

    public void setNombre_Archivo(String Nombre_Archivo) {
        this.Nombre_Archivo = Nombre_Archivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDroptc() {
        return droptc;
    }

    public void setDroptc(Double droptc) {
        this.droptc = droptc;
    }

    public Double getNodroptc() {
        return nodroptc;
    }

    public void setNodroptc(Double nodroptc) {
        this.nodroptc = nodroptc;
    }
    
    

}
