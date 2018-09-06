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
public class RootVisitaStf {

    @XStreamAlias("registro")
    @XStreamImplicit
    private List<VisitaStf> listTable;

    public List<VisitaStf> getListTable() {
        return listTable;
    }

    public void setListTable(List<VisitaStf> listTable) {
        this.listTable = listTable;
    }

}
