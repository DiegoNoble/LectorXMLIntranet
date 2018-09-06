/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.visita.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author dnoble
 */
@XStreamAlias("datos")
public class RootVisitaRiv {

    @XStreamAlias("registro")
    @XStreamImplicit
    private List<VisitaRiv> listTable;

   
    public List<VisitaRiv> getListTable() {
        return listTable;
    }

    public void setListTable(List<VisitaRiv> listTable) {
        this.listTable = listTable;
    }

}
