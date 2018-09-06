/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.utiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class LeeProperties {

    private String usr;
    private String psw;
    private String driver;
    private String url;
    private String ruta_xml_maq_riv;
    private String ruta_xml_maq_riv_procesados;
    private String ruta_xml_caja_riv;
    private String ruta_xml_caja_riv_procesados;
    private String ruta_xml_visita_riv;
    private String ruta_xml_visita_riv_procesados;
    private String ruta_xml_hostel_riv;
    private String ruta_xml_hostel_riv_procesados;
    
    private String ruta_xml_maq_sal;
    private String ruta_xml_maq_sal_procesados;
    private String ruta_xml_visita_sal;
    private String ruta_xml_visita_sal_procesados;
    private String ruta_xml_hostel_sal;
    private String ruta_xml_hostel_sal_procesados;
    
    private String ruta_xml_maq_stf;
    private String ruta_xml_maq_stf_procesados;
    private String ruta_xml_caja_stf;
    private String ruta_xml_caja_stf_procesados;
    private String ruta_xml_visita_stf;
    private String ruta_xml_visita_stf_procesados;
    private String ruta_xml_hostel_stf;
    private String ruta_xml_hostel_stf_procesados;
    private String ruta_xml_bingo_stf;
    private String ruta_xml_bingo_stf_procesados;
    
    private String ruta_xml_maq_mel;
    private String ruta_xml_maq_mel_procesados;
    private String ruta_xml_caja_mel;
    private String ruta_xml_caja_mel_procesados;
    private String ruta_xml_visita_mel;
    private String ruta_xml_visita_mel_procesados;
    private String ruta_xml_hostel_mel;
    private String ruta_xml_hostel_mel_procesados;
    private String ruta_xml_bingo_mel;
    private String ruta_xml_bingo_mel_procesados;
    
    private String ruta_xml_maq_ova;
    private String ruta_xml_maq_ova_procesados;
    private String ruta_xml_caja_ova;
    private String ruta_xml_caja_ova_procesados;
    private String ruta_xml_visita_ova;
    private String ruta_xml_visita_ova_procesados;
    private String ruta_xml_hostel_ova;
    private String ruta_xml_hostel_ova_procesados;
    private String ruta_xml_bingo_ova;
    private String ruta_xml_bingo_ova_procesados;
    
    private Properties props;

    public LeeProperties() {
        getProperties();
    }

    private void getProperties() {
        try {
            props = new Properties();
            InputStream datos = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            props.load(datos);
            this.setUsr(props.getProperty("jdbc.user"));
            this.setPsw(props.getProperty("jdbc.pass"));
            this.setDriver(props.getProperty("jdbc.driver"));
            this.setUrl(props.getProperty("jdbc.url"));
            
            this.setRuta_xml_maq_riv(props.getProperty(              "ruta.xml.maq.riv.entrada"));
            this.setRuta_xml_maq_riv_procesados(props.getProperty(   "ruta.xml.maq.riv.procesados"));
            this.setRuta_xml_caja_riv(props.getProperty(             "ruta.xml.caja.riv.entrada"));
            this.setRuta_xml_caja_riv_procesados(props.getProperty(  "ruta.xml.caja.riv.procesados"));
            this.setRuta_xml_visita_riv(props.getProperty(           "ruta.xml.visita.riv.entrada"));
            this.setRuta_xml_visita_riv_procesados(props.getProperty("ruta.xml.visita.riv.procesados"));
            this.setRuta_xml_hostel_riv(props.getProperty(           "ruta.xml.hostel.riv.entrada"));
            this.setRuta_xml_hostel_riv_procesados(props.getProperty("ruta.xml.hostel.riv.procesados"));
            
            this.setRuta_xml_maq_sal(props.getProperty(              "ruta.xml.maq.sal.entrada"));
            this.setRuta_xml_maq_sal_procesados(props.getProperty(   "ruta.xml.maq.sal.procesados"));
            this.setRuta_xml_visita_sal(props.getProperty(           "ruta.xml.visita.sal.entrada"));
            this.setRuta_xml_visita_sal_procesados(props.getProperty("ruta.xml.visita.sal.procesados"));
            this.setRuta_xml_hostel_sal(props.getProperty(           "ruta.xml.hostel.sal.entrada"));
            this.setRuta_xml_hostel_sal_procesados(props.getProperty("ruta.xml.hostel.sal.procesados"));
            
            this.setRuta_xml_maq_mel(props.getProperty(              "ruta.xml.maq.mel.entrada"));
            this.setRuta_xml_maq_mel_procesados(props.getProperty(   "ruta.xml.maq.mel.procesados"));
            this.setRuta_xml_caja_mel(props.getProperty(             "ruta.xml.caja.mel.entrada"));
            this.setRuta_xml_caja_mel_procesados(props.getProperty(  "ruta.xml.caja.mel.procesados"));
            this.setRuta_xml_visita_mel(props.getProperty(           "ruta.xml.visita.mel.entrada"));
            this.setRuta_xml_visita_mel_procesados(props.getProperty("ruta.xml.visita.mel.procesados"));
            this.setRuta_xml_hostel_mel(props.getProperty(           "ruta.xml.hostel.mel.entrada"));
            this.setRuta_xml_hostel_mel_procesados(props.getProperty("ruta.xml.hostel.mel.procesados"));
            this.setRuta_xml_bingo_mel(props.getProperty(           "ruta.xml.bingo.mel.entrada"));
            this.setRuta_xml_bingo_mel_procesados(props.getProperty("ruta.xml.bingo.mel.procesados"));
            
            this.setRuta_xml_maq_stf(props.getProperty(              "ruta.xml.maq.stf.entrada"));
            this.setRuta_xml_maq_stf_procesados(props.getProperty(   "ruta.xml.maq.stf.procesados"));
            this.setRuta_xml_caja_stf(props.getProperty(             "ruta.xml.caja.stf.entrada"));
            this.setRuta_xml_caja_stf_procesados(props.getProperty(  "ruta.xml.caja.stf.procesados"));
            this.setRuta_xml_visita_stf(props.getProperty(           "ruta.xml.visita.stf.entrada"));
            this.setRuta_xml_visita_stf_procesados(props.getProperty("ruta.xml.visita.stf.procesados"));
            this.setRuta_xml_hostel_stf(props.getProperty(           "ruta.xml.hostel.stf.entrada"));
            this.setRuta_xml_hostel_stf_procesados(props.getProperty("ruta.xml.hostel.stf.procesados"));
            this.setRuta_xml_bingo_stf(props.getProperty(           "ruta.xml.bingo.stf.entrada"));
            this.setRuta_xml_bingo_stf_procesados(props.getProperty("ruta.xml.bingo.stf.procesados"));
            
            this.setRuta_xml_maq_ova(props.getProperty(              "ruta.xml.maq.ova.entrada"));
            this.setRuta_xml_maq_ova_procesados(props.getProperty(   "ruta.xml.maq.ova.procesados"));
            this.setRuta_xml_caja_ova(props.getProperty(             "ruta.xml.caja.ova.entrada"));
            this.setRuta_xml_caja_ova_procesados(props.getProperty(  "ruta.xml.caja.ova.procesados"));
            this.setRuta_xml_visita_ova(props.getProperty(           "ruta.xml.visita.ova.entrada"));
            this.setRuta_xml_visita_ova_procesados(props.getProperty("ruta.xml.visita.ova.procesados"));
            this.setRuta_xml_hostel_ova(props.getProperty(           "ruta.xml.hostel.ova.entrada"));
            this.setRuta_xml_hostel_ova_procesados(props.getProperty("ruta.xml.hostel.ova.procesados"));
            this.setRuta_xml_bingo_ova(props.getProperty(           "ruta.xml.bingo.ova.entrada"));
            this.setRuta_xml_bingo_ova_procesados(props.getProperty("ruta.xml.bingo.ova.procesados"));
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(LeeProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUsr() {

        return usr;
    }

    private void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPsw() {

        return psw;
    }

    private void setPsw(String psw) {
        this.psw = psw;
    }

    public String getDriver() {

        return driver;
    }

    private void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {

        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public String getRuta_xml_maq_riv() {
        return ruta_xml_maq_riv;
    }

    public void setRuta_xml_maq_riv(String ruta_xml_maq_riv) {
        this.ruta_xml_maq_riv = ruta_xml_maq_riv;
    }

    public String getRuta_xml_maq_sal() {
        return ruta_xml_maq_sal;
    }

    public void setRuta_xml_maq_sal(String ruta_xml_maq_sal) {
        this.ruta_xml_maq_sal = ruta_xml_maq_sal;
    }

    public String getRuta_xml_maq_stf() {
        return ruta_xml_maq_stf;
    }

    public void setRuta_xml_maq_stf(String ruta_xml_maq_stf) {
        this.ruta_xml_maq_stf = ruta_xml_maq_stf;
    }

    public String getRuta_xml_maq_mel() {
        return ruta_xml_maq_mel;
    }

    public void setRuta_xml_maq_mel(String ruta_xml_maq_mel) {
        this.ruta_xml_maq_mel = ruta_xml_maq_mel;
    }

    public String getRuta_xml_maq_ova() {
        return ruta_xml_maq_ova;
    }

    public void setRuta_xml_maq_ova(String ruta_xml_maq_ova) {
        this.ruta_xml_maq_ova = ruta_xml_maq_ova;
    }

    public String getRuta_xml_maq_riv_procesados() {
        return ruta_xml_maq_riv_procesados;
    }

    public void setRuta_xml_maq_riv_procesados(String ruta_xml_maq_riv_procesados) {
        this.ruta_xml_maq_riv_procesados = ruta_xml_maq_riv_procesados;
    }

    public String getRuta_xml_maq_sal_procesados() {
        return ruta_xml_maq_sal_procesados;
    }

    public void setRuta_xml_maq_sal_procesados(String ruta_xml_maq_sal_procesados) {
        this.ruta_xml_maq_sal_procesados = ruta_xml_maq_sal_procesados;
    }

    public String getRuta_xml_maq_stf_procesados() {
        return ruta_xml_maq_stf_procesados;
    }

    public void setRuta_xml_maq_stf_procesados(String ruta_xml_maq_stf_procesados) {
        this.ruta_xml_maq_stf_procesados = ruta_xml_maq_stf_procesados;
    }

    public String getRuta_xml_maq_mel_procesados() {
        return ruta_xml_maq_mel_procesados;
    }

    public void setRuta_xml_maq_mel_procesados(String ruta_xml_maq_mel_procesados) {
        this.ruta_xml_maq_mel_procesados = ruta_xml_maq_mel_procesados;
    }

    public String getRuta_xml_maq_ova_procesados() {
        return ruta_xml_maq_ova_procesados;
    }

    public void setRuta_xml_maq_ova_procesados(String ruta_xml_maq_ova_procesados) {
        this.ruta_xml_maq_ova_procesados = ruta_xml_maq_ova_procesados;
    }

    public String getRuta_xml_caja_riv() {
        return ruta_xml_caja_riv;
    }

    public void setRuta_xml_caja_riv(String ruta_xml_caja_riv) {
        this.ruta_xml_caja_riv = ruta_xml_caja_riv;
    }

    public String getRuta_xml_caja_riv_procesados() {
        return ruta_xml_caja_riv_procesados;
    }

    public void setRuta_xml_caja_riv_procesados(String ruta_xml_caja_riv_procesados) {
        this.ruta_xml_caja_riv_procesados = ruta_xml_caja_riv_procesados;
    }

    public String getRuta_xml_caja_mel() {
        return ruta_xml_caja_mel;
    }

    public void setRuta_xml_caja_mel(String ruta_xml_caja_mel) {
        this.ruta_xml_caja_mel = ruta_xml_caja_mel;
    }

    public String getRuta_xml_caja_mel_procesados() {
        return ruta_xml_caja_mel_procesados;
    }

    public void setRuta_xml_caja_mel_procesados(String ruta_xml_caja_mel_procesados) {
        this.ruta_xml_caja_mel_procesados = ruta_xml_caja_mel_procesados;
    }

    public String getRuta_xml_caja_stf() {
        return ruta_xml_caja_stf;
    }

    public void setRuta_xml_caja_stf(String ruta_xml_caja_stf) {
        this.ruta_xml_caja_stf = ruta_xml_caja_stf;
    }

    public String getRuta_xml_caja_stf_procesados() {
        return ruta_xml_caja_stf_procesados;
    }

    public void setRuta_xml_caja_stf_procesados(String ruta_xml_caja_stf_procesados) {
        this.ruta_xml_caja_stf_procesados = ruta_xml_caja_stf_procesados;
    }

    public String getRuta_xml_caja_ova() {
        return ruta_xml_caja_ova;
    }

    public void setRuta_xml_caja_ova(String ruta_xml_caja_ova) {
        this.ruta_xml_caja_ova = ruta_xml_caja_ova;
    }

    public String getRuta_xml_caja_ova_procesados() {
        return ruta_xml_caja_ova_procesados;
    }

    public void setRuta_xml_caja_ova_procesados(String ruta_xml_caja_ova_procesados) {
        this.ruta_xml_caja_ova_procesados = ruta_xml_caja_ova_procesados;
    }

    public String getRuta_xml_visita_riv() {
        return ruta_xml_visita_riv;
    }

    public void setRuta_xml_visita_riv(String ruta_xml_visita_riv) {
        this.ruta_xml_visita_riv = ruta_xml_visita_riv;
    }

    public String getRuta_xml_visita_riv_procesados() {
        return ruta_xml_visita_riv_procesados;
    }

    public void setRuta_xml_visita_riv_procesados(String ruta_xml_visita_riv_procesados) {
        this.ruta_xml_visita_riv_procesados = ruta_xml_visita_riv_procesados;
    }

    public String getRuta_xml_visita_sal() {
        return ruta_xml_visita_sal;
    }

    public void setRuta_xml_visita_sal(String ruta_xml_visita_sal) {
        this.ruta_xml_visita_sal = ruta_xml_visita_sal;
    }

    public String getRuta_xml_visita_sal_procesados() {
        return ruta_xml_visita_sal_procesados;
    }

    public void setRuta_xml_visita_sal_procesados(String ruta_xml_visita_sal_procesados) {
        this.ruta_xml_visita_sal_procesados = ruta_xml_visita_sal_procesados;
    }

    public String getRuta_xml_visita_stf() {
        return ruta_xml_visita_stf;
    }

    public void setRuta_xml_visita_stf(String ruta_xml_visita_stf) {
        this.ruta_xml_visita_stf = ruta_xml_visita_stf;
    }

    public String getRuta_xml_visita_stf_procesados() {
        return ruta_xml_visita_stf_procesados;
    }

    public void setRuta_xml_visita_stf_procesados(String ruta_xml_visita_stf_procesados) {
        this.ruta_xml_visita_stf_procesados = ruta_xml_visita_stf_procesados;
    }

    public String getRuta_xml_visita_mel() {
        return ruta_xml_visita_mel;
    }

    public void setRuta_xml_visita_mel(String ruta_xml_visita_mel) {
        this.ruta_xml_visita_mel = ruta_xml_visita_mel;
    }

    public String getRuta_xml_visita_mel_procesados() {
        return ruta_xml_visita_mel_procesados;
    }

    public void setRuta_xml_visita_mel_procesados(String ruta_xml_visita_mel_procesados) {
        this.ruta_xml_visita_mel_procesados = ruta_xml_visita_mel_procesados;
    }

    public String getRuta_xml_visita_ova() {
        return ruta_xml_visita_ova;
    }

    public void setRuta_xml_visita_ova(String ruta_xml_visita_ova) {
        this.ruta_xml_visita_ova = ruta_xml_visita_ova;
    }

    public String getRuta_xml_visita_ova_procesados() {
        return ruta_xml_visita_ova_procesados;
    }

    public void setRuta_xml_visita_ova_procesados(String ruta_xml_visita_ova_procesados) {
        this.ruta_xml_visita_ova_procesados = ruta_xml_visita_ova_procesados;
    }

    public String getRuta_xml_hostel_riv() {
        return ruta_xml_hostel_riv;
    }

    public void setRuta_xml_hostel_riv(String ruta_xml_hostel_riv) {
        this.ruta_xml_hostel_riv = ruta_xml_hostel_riv;
    }

    public String getRuta_xml_hostel_riv_procesados() {
        return ruta_xml_hostel_riv_procesados;
    }

    public void setRuta_xml_hostel_riv_procesados(String ruta_xml_hostel_riv_procesados) {
        this.ruta_xml_hostel_riv_procesados = ruta_xml_hostel_riv_procesados;
    }

    public String getRuta_xml_hostel_sal() {
        return ruta_xml_hostel_sal;
    }

    public void setRuta_xml_hostel_sal(String ruta_xml_hostel_sal) {
        this.ruta_xml_hostel_sal = ruta_xml_hostel_sal;
    }

    public String getRuta_xml_hostel_sal_procesados() {
        return ruta_xml_hostel_sal_procesados;
    }

    public void setRuta_xml_hostel_sal_procesados(String ruta_xml_hostel_sal_procesados) {
        this.ruta_xml_hostel_sal_procesados = ruta_xml_hostel_sal_procesados;
    }

    public String getRuta_xml_hostel_stf() {
        return ruta_xml_hostel_stf;
    }

    public void setRuta_xml_hostel_stf(String ruta_xml_hostel_stf) {
        this.ruta_xml_hostel_stf = ruta_xml_hostel_stf;
    }

    public String getRuta_xml_hostel_stf_procesados() {
        return ruta_xml_hostel_stf_procesados;
    }

    public void setRuta_xml_hostel_stf_procesados(String ruta_xml_hostel_stf_procesados) {
        this.ruta_xml_hostel_stf_procesados = ruta_xml_hostel_stf_procesados;
    }

    public String getRuta_xml_hostel_mel() {
        return ruta_xml_hostel_mel;
    }

    public void setRuta_xml_hostel_mel(String ruta_xml_hostel_mel) {
        this.ruta_xml_hostel_mel = ruta_xml_hostel_mel;
    }

    public String getRuta_xml_hostel_mel_procesados() {
        return ruta_xml_hostel_mel_procesados;
    }

    public void setRuta_xml_hostel_mel_procesados(String ruta_xml_hostel_mel_procesados) {
        this.ruta_xml_hostel_mel_procesados = ruta_xml_hostel_mel_procesados;
    }

    public String getRuta_xml_hostel_ova() {
        return ruta_xml_hostel_ova;
    }

    public void setRuta_xml_hostel_ova(String ruta_xml_hostel_ova) {
        this.ruta_xml_hostel_ova = ruta_xml_hostel_ova;
    }

    public String getRuta_xml_hostel_ova_procesados() {
        return ruta_xml_hostel_ova_procesados;
    }

    public void setRuta_xml_hostel_ova_procesados(String ruta_xml_hostel_ova_procesados) {
        this.ruta_xml_hostel_ova_procesados = ruta_xml_hostel_ova_procesados;
    }

    public String getRuta_xml_bingo_stf() {
        return ruta_xml_bingo_stf;
    }

    public void setRuta_xml_bingo_stf(String ruta_xml_bingo_stf) {
        this.ruta_xml_bingo_stf = ruta_xml_bingo_stf;
    }

    public String getRuta_xml_bingo_stf_procesados() {
        return ruta_xml_bingo_stf_procesados;
    }

    public void setRuta_xml_bingo_stf_procesados(String ruta_xml_bingo_stf_procesados) {
        this.ruta_xml_bingo_stf_procesados = ruta_xml_bingo_stf_procesados;
    }

    public String getRuta_xml_bingo_mel() {
        return ruta_xml_bingo_mel;
    }

    public void setRuta_xml_bingo_mel(String ruta_xml_bingo_mel) {
        this.ruta_xml_bingo_mel = ruta_xml_bingo_mel;
    }

    public String getRuta_xml_bingo_mel_procesados() {
        return ruta_xml_bingo_mel_procesados;
    }

    public void setRuta_xml_bingo_mel_procesados(String ruta_xml_bingo_mel_procesados) {
        this.ruta_xml_bingo_mel_procesados = ruta_xml_bingo_mel_procesados;
    }

    public String getRuta_xml_bingo_ova() {
        return ruta_xml_bingo_ova;
    }

    public void setRuta_xml_bingo_ova(String ruta_xml_bingo_ova) {
        this.ruta_xml_bingo_ova = ruta_xml_bingo_ova;
    }

    public String getRuta_xml_bingo_ova_procesados() {
        return ruta_xml_bingo_ova_procesados;
    }

    public void setRuta_xml_bingo_ova_procesados(String ruta_xml_bingo_ova_procesados) {
        this.ruta_xml_bingo_ova_procesados = ruta_xml_bingo_ova_procesados;
    }

    

    
}
