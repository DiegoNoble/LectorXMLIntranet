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
@XStreamAlias("BALANCE")
//@Entity
//@Table(name = "bingo_ova")
public class BingoOva implements Serializable {

    @Id
    private Integer id;
   /* private CONCEPTOS CONCEPTOS;

    private CARTONES CARTONES;

    private String Sala;

    private String Sesion;

    public CONCEPTOS getCONCEPTOS ()
    {
        return CONCEPTOS;
    }

    public void setCONCEPTOS (CONCEPTOS CONCEPTOS)
    {
        this.CONCEPTOS = CONCEPTOS;
    }

    public CARTONES getCARTONES ()
    {
        return CARTONES;
    }

    public void setCARTONES (CARTONES CARTONES)
    {
        this.CARTONES = CARTONES;
    }

    public String getSala ()
    {
        return Sala;
    }

    public void setSala (String Sala)
    {
        this.Sala = Sala;
    }

    public String getSesion ()
    {
        return Sesion;
    }

    public void setSesion (String Sesion)
    {
        this.Sesion = Sesion;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CONCEPTOS = "+CONCEPTOS+", CARTONES = "+CARTONES+", Sala = "+Sala+", Sesion = "+Sesion+"]";
    }
 */
}
