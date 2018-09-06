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
public class RootHotelOva {

    @XStreamAlias("Table")
    @XStreamImplicit
    private List<HotelOva> listTable;

    public List<HotelOva> getListTable() {
        return listTable;
    }

    public void setListTable(List<HotelOva> listTable) {
        this.listTable = listTable;
    }

}
