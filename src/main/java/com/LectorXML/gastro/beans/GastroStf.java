/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.gastro.beans;

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
//@Table(name = "Gastro_Stf")
public class GastroStf implements Serializable {

    // @XStreamConverter(DateConverter.class)
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Desde;
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Hasta;
    private String Unidad;
    @Id
    private String Sector;
    @Id
    private String Descripcion;
    private Double Valor;
    private String TipoValor;
    private String Nombre_Archivo;

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

    public String getUnidad() {
        return Unidad;
    }

    public void setUnidad(String Unidad) {
        this.Unidad = Unidad;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String Sector) {
        this.Sector = Sector;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    public String getTipoValor() {
        return TipoValor;
    }

    public void setTipoValor(String TipoValor) {
        this.TipoValor = TipoValor;
    }

  

    public String getNombre_Archivo() {
        return Nombre_Archivo;
    }

    public void setNombre_Archivo(String Nombre_Archivo) {
        this.Nombre_Archivo = Nombre_Archivo;
    }

}
