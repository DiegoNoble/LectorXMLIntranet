/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.gastro.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author dnoble
 */
@XStreamAlias("NewDataSet")
public class RootGastroOva {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<GastroOva> listTable;

    public List<GastroOva> getListTable() {
        return listTable;
    }

    public void setListTable(List<GastroOva> listTable) {
        this.listTable = listTable;
    }

}
