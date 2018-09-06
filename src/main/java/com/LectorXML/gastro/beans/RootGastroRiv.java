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
public class RootGastroRiv {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<GastroRiv> listTable;

    public List<GastroRiv> getListTable() {
        return listTable;
    }

    public void setListTable(List<GastroRiv> listTable) {
        this.listTable = listTable;
    }

}
