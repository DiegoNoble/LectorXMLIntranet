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
public class RootCajaMel {

    @XStreamAlias("datosdia")
    @XStreamImplicit
    private List<CajaMel> listTable;

    @XStreamOmitField()
    @XStreamAlias("xsd:schema")
    private OtroCajaMel otro;

    public OtroCajaMel getOtro() {
        return otro;
    }

    public void setOtro(OtroCajaMel otro) {
        this.otro = otro;
    }

    public List<CajaMel> getListTable() {
        return listTable;
    }

    public void setListTable(List<CajaMel> listTable) {
        this.listTable = listTable;
    }

}
