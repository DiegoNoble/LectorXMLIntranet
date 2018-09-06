/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.tareas;

import com.LectorXML.bingo.beans.BingoOva;
import com.LectorXML.caja.beans.CajaOva;
import com.LectorXML.caja.beans.RootCajaOva;
import com.LectorXML.caja.traductor.TraductorXmlCajaOva;
import com.LectorXML.daos.DaoBingo;
import com.LectorXML.daos.DaoCaja;
import com.LectorXML.daos.DaoGastro;
import com.LectorXML.daos.DaoHotel;
import com.LectorXML.maq.beans.MaqOva;
import com.LectorXML.maq.beans.RootXmlMaqOva;
import com.LectorXML.daos.DaoMaquinas;
import com.LectorXML.daos.DaoVisita;
import com.LectorXML.gastro.beans.GastroOva;
import com.LectorXML.gastro.beans.RootGastroOva;
import com.LectorXML.gastro.traductor.TraductorXMLGastroOva;
import com.LectorXML.hotel.beans.HotelOva;
import com.LectorXML.hotel.beans.RootHotelOva;
import com.LectorXML.hotel.traductor.TraductorXMLHotelOva;
import com.LectorXML.maq.traductor.TraductorXmlMaqOva;
import com.LectorXML.utiles.LeeProperties;
import com.LectorXML.visita.beans.RootVisitaOva;
import com.LectorXML.visita.beans.VisitaOva;
import com.LectorXML.visita.traductor.TraductorXmlVisitaOva;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;

/**
 *
 * @author Diego
 */
public class LeerXMLOva implements Job {

    private List<MaqOva> listSlotOva;
    private DaoMaquinas daoMaquinas;
    private LeeProperties parametros;
    private List<CajaOva> listCajaOva;
    private List<VisitaOva> listVisitaOva;
    private DaoVisita daoVisita;
    private List<HotelOva> listHotelOva;
    private DaoHotel daoHotel;
    private DaoCaja daoCaja;
    private List<GastroOva> listGastroOva;
    private DaoGastro daoGastro;
    private List<BingoOva> listBingoOva;
    private DaoBingo daoBingo;

    private JCheckBox cbMaqOva;
    private JCheckBox cbMesasOva;
    private JCheckBox cbHotelOva;
    private JCheckBox cbGastroOva;
    private JCheckBox cbVisitasOva;
    private JCheckBox cbBingoOva;

    JTextArea txtLog;
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat formatoVisualizaFecha = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        try {
            SchedulerContext schedulerContext = arg0.getScheduler().getContext();
            this.txtLog = (JTextArea) schedulerContext.get("txtLog");
            this.cbMaqOva = (JCheckBox) schedulerContext.get("cbMaqOva");
            this.cbMesasOva = (JCheckBox) schedulerContext.get("cbMesasOva");
            this.cbHotelOva = (JCheckBox) schedulerContext.get("cbHotelOva");
            this.cbGastroOva = (JCheckBox) schedulerContext.get("cbGastroOva");
            this.cbVisitasOva = (JCheckBox) schedulerContext.get("cbVisitasOva");
            this.cbBingoOva = (JCheckBox) schedulerContext.get("cbBingoOva");
            
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
            
            if (cbMaqOva.isSelected()) {
                LeerXMLMaqOvalle();
            }
            if (cbMesasOva.isSelected()) {
                LeerXMLCajaOvalle();
            }
            if (cbHotelOva.isSelected()) {
                LeerXMLHotelOvalle();
            }
            if (cbGastroOva.isSelected()) {
                LeerXMLGastroOvalle();
            }
            if (cbVisitasOva.isSelected()) {
                LeerXMLVisitaOvalle();
            }
            if (cbBingoOva.isSelected()) {
                LeerXMLBingoOvalle();
            }

        } catch (Exception ex) {
            txtLog.append("Error: " + ex);
        }

    }

    public void LeerXMLMaqOvalle() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqOva>.
        txtLog.append("\nTarea lectura XML Maq Ovalle inicia: " + formato.format(new Date()));
        parametros = new LeeProperties();
        listSlotOva = new ArrayList<>();
        for (RootXmlMaqOva root : new TraductorXmlMaqOva().leerXML(txtLog, parametros.getRuta_xml_maq_ova(), parametros.getRuta_xml_maq_ova_procesados())) {
            listSlotOva.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Ovalle---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (MaqOva slot : listSlotOva) {

            daoMaquinas = new DaoMaquinas(txtLog);
            if (daoMaquinas.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle Slot " + slot.getNumero_Maquina() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle Slot " + slot.getNumero_Maquina() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }

    public void LeerXMLCajaOvalle() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqOva>.
        parametros = new LeeProperties();
        listCajaOva = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Caja Ovalle inicia: " + formato.format(new Date()));

        for (RootCajaOva root : new TraductorXmlCajaOva().leerXML(txtLog, parametros.getRuta_xml_caja_ova(), parametros.getRuta_xml_caja_ova_procesados())) {
            listCajaOva.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Ovalle---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (CajaOva caja : listCajaOva) {

            daoCaja = new DaoCaja(txtLog);
            if (daoCaja.save(caja) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle Caja concepto " + caja.getConcepto() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle Caja concepto" + caja.getConcepto() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLVisitaOvalle() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqOva>.
        parametros = new LeeProperties();
        listVisitaOva = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Visitas Ovalle inicia: " + formato.format(new Date()));

        for (RootVisitaOva root : new TraductorXmlVisitaOva().leerXML(txtLog, parametros.getRuta_xml_visita_ova(), parametros.getRuta_xml_visita_ova_procesados())) {
            listVisitaOva.addAll(root.getListTable());
        }
        txtLog.append("\n\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Ovalle----------------");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (VisitaOva slot : listVisitaOva) {

            daoVisita = new DaoVisita(txtLog);
            if (daoVisita.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle visitas hora " + slot.getHora() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle visitas hora " + slot.getHora() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLHotelOvalle() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqOva>.
        parametros = new LeeProperties();
        listHotelOva = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Hotel Ovalle inicia: " + formato.format(new Date()));
        for (RootHotelOva root : new TraductorXMLHotelOva().leerXML(txtLog, parametros.getRuta_xml_hostel_ova(), parametros.getRuta_xml_hostel_ova_procesados())) {
            listHotelOva.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Ovalle---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (HotelOva hotel : listHotelOva) {

            daoHotel = new DaoHotel(txtLog);
            if (daoHotel.save(hotel) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle hotel " + hotel.getDesde() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle hotel " + hotel.getDesde() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLGastroOvalle() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqOva>.
        parametros = new LeeProperties();
        listGastroOva = new ArrayList<>();
        txtLog.append("\nTarea lectura XML Gastronomía Ovalle inicia: " + formato.format(new Date()));
        for (RootGastroOva root : new TraductorXMLGastroOva().leerXML(txtLog, parametros.getRuta_xml_hostel_ova(), parametros.getRuta_xml_hostel_ova_procesados())) {
            listGastroOva.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Ovalle---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (GastroOva Gastro : listGastroOva) {

            daoGastro = new DaoGastro(txtLog);
            if (daoGastro.save(Gastro) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }
    
     public void LeerXMLBingoOvalle() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqOva>.
       /* parametros = new LeeProperties();
        listBingoOva = new ArrayList<>();
        listBingoOva.addAll(new TraductorXmlBingoOva().leerXML(txtLog, parametros.getRuta_xml_bingo_ova(),parametros.getRuta_xml_bingo_ova_procesados()));

        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Ovalle---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (BingoOva Bingo : listBingoOva) {

            daoBingo = new DaoBingo(txtLog);
            if (daoBingo.save(Bingo) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle Bingo concepto " + Bingo.getFechaProcesoFin()+ " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nOvalle Bingo concepto" + Bingo.getFechaProcesoFin()+ " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }*/
    }

    public void setTxtLog(JTextArea txtLog) {
        this.txtLog = txtLog;
    }

}
