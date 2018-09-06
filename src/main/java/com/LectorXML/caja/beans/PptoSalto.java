/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.caja.beans;

import java.util.Date;

/**
 *
 * @author dnoble
 */

public class PptoSalto {

    private String fecha;

    private Double publico;

    //MAQUINAS
    private Double coin_in_video;
    private Double coin_in_ruleta;
    private Double coin_in_total;

    private Double win_video;
    private Double win_ruleta;
    private Double win_maq_total;

    private Double promotickets;

    private Double win_neto_video;
    private Double win_neto_ruleta;
    private Double win_neto_total;

    private Integer cantidad_video;
    private Integer cantidad_ruleta;
    private Integer cantidad_maq_total;

    private Integer wmd_video;
    private Integer wmd_ruleta;
    private Integer wmd_total;

    private Double retencion_video;
    private Double retencion_ruleta;
    private Double retencion_maq_total;

    //MESAS
    private Double drop_ra;
    private Double drop_bj;
    private Double drop_mpb;
    private Double drop_psd;
    private Double drop_pkh;
    private Double drop_craps;
    private Double drop_total;

    private Double win_ra;
    private Double win_bj;
    private Double win_mpb;
    private Double win_psd;
    private Double win_pkh;
    private Double win_craps;
    private Double win_mesas_total;

    private Integer cantidad_ra;
    private Integer cantidad_bj;
    private Integer cantidad_mpb;
    private Integer cantidad_psd;
    private Integer cantidad_pkh;
    private Integer cantidad_craps;
    private Integer cantidad_mesas_total;

    private Double retencion_ra;
    private Double retencion_bj;
    private Double retencion_mpb;
    private Double retencion_psd;
    private Double retencion_pkh;
    private Double retencion_craps;
    private Double retencion_mesas_total;

    //GASTRONOMIA
    private Double vivopub_fact;
    private Double vivopub_cortesia;

    private Double drinks_fact;
    private Double drinks_cortesia;

    private Double recova_fact;
    private Double recova_cortesia;

    private Double otros_fact;
    private Double otros_cortesia;

    private Double facturacion;
    private Double cortesia;
    private Double total;

    //HOTEL
    private Integer hab;
    private Integer hab_complimentary;
    private Integer hab_no_complimentary;
    private Integer hab_ocupadas_total;

    private Double ocupacion;

    private Double fact_complimentary;
    private Double fact_no_complimentary;
    private Double fact_otros;

    private Double total_ingresos_hotel;
    private Double fact_x_hab;

    public PptoSalto() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getPublico() {
        return publico;
    }

    public void setPublico(Double publico) {
        this.publico = publico;
    }

    public Double getCoin_in_video() {
        return coin_in_video;
    }

    public void setCoin_in_video(Double coin_in_video) {
        this.coin_in_video = coin_in_video;
    }

    public Double getCoin_in_ruleta() {
        return coin_in_ruleta;
    }

    public void setCoin_in_ruleta(Double coin_in_ruleta) {
        this.coin_in_ruleta = coin_in_ruleta;
    }

    public Double getCoin_in_total() {
        return coin_in_total;
    }

    public void setCoin_in_total(Double coin_in_total) {
        this.coin_in_total = coin_in_total;
    }

    public Double getWin_video() {
        return win_video;
    }

    public void setWin_video(Double win_video) {
        this.win_video = win_video;
    }

    public Double getWin_ruleta() {
        return win_ruleta;
    }

    public void setWin_ruleta(Double win_ruleta) {
        this.win_ruleta = win_ruleta;
    }

    public Double getWin_maq_total() {
        return win_maq_total;
    }

    public void setWin_maq_total(Double win_maq_total) {
        this.win_maq_total = win_maq_total;
    }

    public Double getPromotickets() {
        return promotickets;
    }

    public void setPromotickets(Double promotickets) {
        this.promotickets = promotickets;
    }

    public Double getWin_neto_video() {
        return win_neto_video;
    }

    public void setWin_neto_video(Double win_neto_video) {
        this.win_neto_video = win_neto_video;
    }

    public Double getWin_neto_ruleta() {
        return win_neto_ruleta;
    }

    public void setWin_neto_ruleta(Double win_neto_ruleta) {
        this.win_neto_ruleta = win_neto_ruleta;
    }

    public Double getWin_neto_total() {
        return win_neto_total;
    }

    public void setWin_neto_total(Double win_neto_total) {
        this.win_neto_total = win_neto_total;
    }

    public Integer getCantidad_video() {
        return cantidad_video;
    }

    public void setCantidad_video(Integer cantidad_video) {
        this.cantidad_video = cantidad_video;
    }

    public Integer getCantidad_ruleta() {
        return cantidad_ruleta;
    }

    public void setCantidad_ruleta(Integer cantidad_ruleta) {
        this.cantidad_ruleta = cantidad_ruleta;
    }

    public Integer getCantidad_maq_total() {
        return cantidad_maq_total;
    }

    public void setCantidad_maq_total(Integer cantidad_maq_total) {
        this.cantidad_maq_total = cantidad_maq_total;
    }

    public Integer getWmd_video() {
        return wmd_video;
    }

    public void setWmd_video(Integer wmd_video) {
        this.wmd_video = wmd_video;
    }

    public Integer getWmd_ruleta() {
        return wmd_ruleta;
    }

    public void setWmd_ruleta(Integer wmd_ruleta) {
        this.wmd_ruleta = wmd_ruleta;
    }

    public Integer getWmd_total() {
        return wmd_total;
    }

    public void setWmd_total(Integer wmd_total) {
        this.wmd_total = wmd_total;
    }

    public Double getRetencion_video() {
        return retencion_video;
    }

    public void setRetencion_video(Double retencion_video) {
        this.retencion_video = retencion_video;
    }

    public Double getRetencion_ruleta() {
        return retencion_ruleta;
    }

    public void setRetencion_ruleta(Double retencion_ruleta) {
        this.retencion_ruleta = retencion_ruleta;
    }

    public Double getRetencion_maq_total() {
        return retencion_maq_total;
    }

    public void setRetencion_maq_total(Double retencion_maq_total) {
        this.retencion_maq_total = retencion_maq_total;
    }

    public Double getDrop_ra() {
        return drop_ra;
    }

    public void setDrop_ra(Double drop_ra) {
        this.drop_ra = drop_ra;
    }

    public Double getDrop_bj() {
        return drop_bj;
    }

    public void setDrop_bj(Double drop_bj) {
        this.drop_bj = drop_bj;
    }

    public Double getDrop_mpb() {
        return drop_mpb;
    }

    public void setDrop_mpb(Double drop_mpb) {
        this.drop_mpb = drop_mpb;
    }

    public Double getDrop_psd() {
        return drop_psd;
    }

    public void setDrop_psd(Double drop_psd) {
        this.drop_psd = drop_psd;
    }

    public Double getDrop_pkh() {
        return drop_pkh;
    }

    public void setDrop_pkh(Double drop_pkh) {
        this.drop_pkh = drop_pkh;
    }

    public Double getDrop_craps() {
        return drop_craps;
    }

    public void setDrop_craps(Double drop_craps) {
        this.drop_craps = drop_craps;
    }

    public Double getDrop_total() {
        return drop_total;
    }

    public void setDrop_total(Double drop_total) {
        this.drop_total = drop_total;
    }

    public Double getWin_ra() {
        return win_ra;
    }

    public void setWin_ra(Double win_ra) {
        this.win_ra = win_ra;
    }

    public Double getWin_bj() {
        return win_bj;
    }

    public void setWin_bj(Double win_bj) {
        this.win_bj = win_bj;
    }

    public Double getWin_mpb() {
        return win_mpb;
    }

    public void setWin_mpb(Double win_mpb) {
        this.win_mpb = win_mpb;
    }

    public Double getWin_psd() {
        return win_psd;
    }

    public void setWin_psd(Double win_psd) {
        this.win_psd = win_psd;
    }

    public Double getWin_pkh() {
        return win_pkh;
    }

    public void setWin_pkh(Double win_pkh) {
        this.win_pkh = win_pkh;
    }

    public Double getWin_craps() {
        return win_craps;
    }

    public void setWin_craps(Double win_craps) {
        this.win_craps = win_craps;
    }

    public Double getWin_mesas_total() {
        return win_mesas_total;
    }

    public void setWin_mesas_total(Double win_mesas_total) {
        this.win_mesas_total = win_mesas_total;
    }

    public Integer getCantidad_ra() {
        return cantidad_ra;
    }

    public void setCantidad_ra(Integer cantidad_ra) {
        this.cantidad_ra = cantidad_ra;
    }

    public Integer getCantidad_bj() {
        return cantidad_bj;
    }

    public void setCantidad_bj(Integer cantidad_bj) {
        this.cantidad_bj = cantidad_bj;
    }

    public Integer getCantidad_mpb() {
        return cantidad_mpb;
    }

    public void setCantidad_mpb(Integer cantidad_mpb) {
        this.cantidad_mpb = cantidad_mpb;
    }

    public Integer getCantidad_psd() {
        return cantidad_psd;
    }

    public void setCantidad_psd(Integer cantidad_psd) {
        this.cantidad_psd = cantidad_psd;
    }

    public Integer getCantidad_pkh() {
        return cantidad_pkh;
    }

    public void setCantidad_pkh(Integer cantidad_pkh) {
        this.cantidad_pkh = cantidad_pkh;
    }

    public Integer getCantidad_craps() {
        return cantidad_craps;
    }

    public void setCantidad_craps(Integer cantidad_craps) {
        this.cantidad_craps = cantidad_craps;
    }

    public Integer getCantidad_mesas_total() {
        return cantidad_mesas_total;
    }

    public void setCantidad_mesas_total(Integer cantidad_mesas_total) {
        this.cantidad_mesas_total = cantidad_mesas_total;
    }

    public Double getRetencion_ra() {
        return retencion_ra;
    }

    public void setRetencion_ra(Double retencion_ra) {
        this.retencion_ra = retencion_ra;
    }

    public Double getRetencion_bj() {
        return retencion_bj;
    }

    public void setRetencion_bj(Double retencion_bj) {
        this.retencion_bj = retencion_bj;
    }

    public Double getRetencion_mpb() {
        return retencion_mpb;
    }

    public void setRetencion_mpb(Double retencion_mpb) {
        this.retencion_mpb = retencion_mpb;
    }

    public Double getRetencion_psd() {
        return retencion_psd;
    }

    public void setRetencion_psd(Double retencion_psd) {
        this.retencion_psd = retencion_psd;
    }

    public Double getRetencion_pkh() {
        return retencion_pkh;
    }

    public void setRetencion_pkh(Double retencion_pkh) {
        this.retencion_pkh = retencion_pkh;
    }

    public Double getRetencion_craps() {
        return retencion_craps;
    }

    public void setRetencion_craps(Double retencion_craps) {
        this.retencion_craps = retencion_craps;
    }

    public Double getRetencion_mesas_total() {
        return retencion_mesas_total;
    }

    public void setRetencion_mesas_total(Double retencion_mesas_total) {
        this.retencion_mesas_total = retencion_mesas_total;
    }

    public Double getVivopub_fact() {
        return vivopub_fact;
    }

    public void setVivopub_fact(Double vivopub_fact) {
        this.vivopub_fact = vivopub_fact;
    }

    public Double getVivopub_cortesia() {
        return vivopub_cortesia;
    }

    public void setVivopub_cortesia(Double vivopub_cortesia) {
        this.vivopub_cortesia = vivopub_cortesia;
    }

    public Double getDrinks_fact() {
        return drinks_fact;
    }

    public void setDrinks_fact(Double drinks_fact) {
        this.drinks_fact = drinks_fact;
    }

    public Double getDrinks_cortesia() {
        return drinks_cortesia;
    }

    public void setDrinks_cortesia(Double drinks_cortesia) {
        this.drinks_cortesia = drinks_cortesia;
    }

    public Double getRecova_fact() {
        return recova_fact;
    }

    public void setRecova_fact(Double recova_fact) {
        this.recova_fact = recova_fact;
    }

    public Double getRecova_cortesia() {
        return recova_cortesia;
    }

    public void setRecova_cortesia(Double recova_cortesia) {
        this.recova_cortesia = recova_cortesia;
    }

    public Double getOtros_fact() {
        return otros_fact;
    }

    public void setOtros_fact(Double otros_fact) {
        this.otros_fact = otros_fact;
    }

    public Double getOtros_cortesia() {
        return otros_cortesia;
    }

    public void setOtros_cortesia(Double otros_cortesia) {
        this.otros_cortesia = otros_cortesia;
    }

    public Double getFacturacion() {
        return facturacion;
    }

    public void setFacturacion(Double facturacion) {
        this.facturacion = facturacion;
    }

    public Double getCortesia() {
        return cortesia;
    }

    public void setCortesia(Double cortesia) {
        this.cortesia = cortesia;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getHab() {
        return hab;
    }

    public void setHab(Integer hab) {
        this.hab = hab;
    }

    public Integer getHab_complimentary() {
        return hab_complimentary;
    }

    public void setHab_complimentary(Integer hab_complimentary) {
        this.hab_complimentary = hab_complimentary;
    }

    public Integer getHab_no_complimentary() {
        return hab_no_complimentary;
    }

    public void setHab_no_complimentary(Integer hab_no_complimentary) {
        this.hab_no_complimentary = hab_no_complimentary;
    }

    public Integer getHab_ocupadas_total() {
        return hab_ocupadas_total;
    }

    public void setHab_ocupadas_total(Integer hab_ocupadas_total) {
        this.hab_ocupadas_total = hab_ocupadas_total;
    }

    public Double getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Double ocupacion) {
        this.ocupacion = ocupacion;
    }

    public Double getFact_complimentary() {
        return fact_complimentary;
    }

    public void setFact_complimentary(Double fact_complimentary) {
        this.fact_complimentary = fact_complimentary;
    }

    public Double getFact_no_complimentary() {
        return fact_no_complimentary;
    }

    public void setFact_no_complimentary(Double fact_no_complimentary) {
        this.fact_no_complimentary = fact_no_complimentary;
    }

    public Double getFact_otros() {
        return fact_otros;
    }

    public void setFact_otros(Double fact_otros) {
        this.fact_otros = fact_otros;
    }

    public Double getTotal_ingresos_hotel() {
        return total_ingresos_hotel;
    }

    public void setTotal_ingresos_hotel(Double total_ingresos_hotel) {
        this.total_ingresos_hotel = total_ingresos_hotel;
    }

    public Double getFact_x_hab() {
        return fact_x_hab;
    }

    public void setFact_x_hab(Double fact_x_hab) {
        this.fact_x_hab = fact_x_hab;
    }

    @Override
    public String toString() {
        return "PptoSalto{" + "fecha=" + fecha + ", publico=" + publico + ", coin_in_video=" + coin_in_video + ", coin_in_ruleta=" + coin_in_ruleta + ", coin_in_total=" + coin_in_total + '}' + "\n";
    }

}
