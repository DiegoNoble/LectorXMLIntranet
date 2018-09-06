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
public class RootCajaRiv {

    @XStreamAlias("datosdia")
    @XStreamImplicit
    private List<CajaRiv> listTable;

    @XStreamOmitField()
    @XStreamAlias("xsd:schema")
    private OtroCajaRiv otro;

    public OtroCajaRiv getOtro() {
        return otro;
    }

    public void setOtro(OtroCajaRiv otro) {
        this.otro = otro;
    }
    
    

    public List<CajaRiv> getListTable() {
        return listTable;
    }

    public void setListTable(List<CajaRiv> listTable) {
        this.listTable = listTable;
    }

}
