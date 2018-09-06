/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.maq.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author dnoble
 */
@XStreamAlias("Root")
public class RootXmlMaqStf {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<MaqStf> listTable;

    public List<MaqStf> getListTable() {
        return listTable;
    }

    public void setListTable(List<MaqStf> listTable) {
        this.listTable = listTable;
    }

}
