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
public class RootGastroMel {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<GastroMel> listTable;

    public List<GastroMel> getListTable() {
        return listTable;
    }

    public void setListTable(List<GastroMel> listTable) {
        this.listTable = listTable;
    }

}
