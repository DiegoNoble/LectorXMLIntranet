/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.caja.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.util.List;

/**
 *
 * @author dnoble
 */
@XStreamAlias("VFPData")
public class RootCajaOva {

    @XStreamAlias("datosdia")
    @XStreamImplicit
    private List<CajaOva> listTable;

    @XStreamOmitField()
    @XStreamAlias("xsd:schema")
    private OtroCajaOva otro;

    public OtroCajaOva getOtro() {
        return otro;
    }

    public void setOtro(OtroCajaOva otro) {
        this.otro = otro;
    }

    public List<CajaOva> getListTable() {
        return listTable;
    }

    public void setListTable(List<CajaOva> listTable) {
        this.listTable = listTable;
    }

}
