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
public class RootXmlMaqRiv {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<MaqRiv> listTable;

    public List<MaqRiv> getListTable() {
        return listTable;
    }

    public void setListTable(List<MaqRiv> listTable) {
        this.listTable = listTable;
    }

}
