/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.bingo.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author dnoble
 */
@XStreamAlias("Bingo")
//@Entity
//@Table(name = "bingo_Stf")
public class BingoStf implements Serializable {

    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date FechaProcesoFin;

    private Double SaldoDeCaja;

    public Date getFechaProcesoFin() {
        return FechaProcesoFin;
    }

    public void setFechaProcesoFin(Date FechaProcesoFin) {
        this.FechaProcesoFin = FechaProcesoFin;
    }

    public Double getSaldoDeCaja() {
        return SaldoDeCaja;
    }

    public void setSaldoDeCaja(Double SaldoDeCaja) {
        this.SaldoDeCaja = SaldoDeCaja;
    }

}
