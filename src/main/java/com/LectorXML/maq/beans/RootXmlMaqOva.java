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
public class RootXmlMaqOva {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<MaqOva> listTable;

    public List<MaqOva> getListTable() {
        return listTable;
    }

    public void setListTable(List<MaqOva> listTable) {
        this.listTable = listTable;
    }

}
