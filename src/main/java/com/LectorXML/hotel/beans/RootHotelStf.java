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
public class RootHotelStf {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<HotelStf> listTable;

    public List<HotelStf> getListTable() {
        return listTable;
    }

    public void setListTable(List<HotelStf> listTable) {
        this.listTable = listTable;
    }

}
