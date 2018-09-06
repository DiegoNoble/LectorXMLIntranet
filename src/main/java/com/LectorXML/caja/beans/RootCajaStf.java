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
public class RootCajaStf {

    @XStreamAlias("datosdia")
    @XStreamImplicit
    private List<CajaStf> listTable;

    @XStreamOmitField()
    @XStreamAlias("xsd:schema")
    private OtroCajaStf otro;

    public OtroCajaStf getOtro() {
        return otro;
    }

    public void setOtro(OtroCajaStf otro) {
        this.otro = otro;
    }

    public List<CajaStf> getListTable() {
        return listTable;
    }

    public void setListTable(List<CajaStf> listTable) {
        this.listTable = listTable;
    }

}
