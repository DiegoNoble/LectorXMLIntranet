/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.hotel.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author dnoble
 */
@XStreamAlias("NewDataSet")
public class RootHotelSal {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<HotelSal> listTable;

    public List<HotelSal> getListTable() {
        return listTable;
    }

    public void setListTable(List<HotelSal> listTable) {
        this.listTable = listTable;
    }

}
