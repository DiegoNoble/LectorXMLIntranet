/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.tareas;

import com.LectorXML.bingo.beans.BingoStf;
import com.LectorXML.bingo.traductor.TraductorXmlBingoStf;
import com.LectorXML.caja.beans.CajaStf;
import com.LectorXML.caja.beans.RootCajaStf;
import com.LectorXML.caja.traductor.TraductorXmlCajaStf;
import com.LectorXML.daos.DaoBingo;
import com.LectorXML.daos.DaoCaja;
import com.LectorXML.daos.DaoGastro;
import com.LectorXML.daos.DaoHotel;
import com.LectorXML.maq.beans.MaqStf;
import com.LectorXML.maq.beans.RootXmlMaqStf;
import com.LectorXML.daos.DaoMaquinas;
import com.LectorXML.daos.DaoVisita;
import com.LectorXML.gastro.beans.GastroStf;
import com.LectorXML.gastro.beans.RootGastroStf;
import com.LectorXML.gastro.traductor.TraductorXMLGastroStf;
import com.LectorXML.hotel.beans.HotelStf;
import com.LectorXML.hotel.beans.RootHotelStf;
import com.LectorXML.hotel.traductor.TraductorXMLHotelStf;
import com.LectorXML.maq.traductor.TraductorXmlMaqStf;
import com.LectorXML.utiles.LeeProperties;
import com.LectorXML.visita.beans.RootVisitaStf;
import com.LectorXML.visita.beans.VisitaStf;
import com.LectorXML.visita.traductor.TraductorXmlVisitaStf;
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
public class LeerXMLStf implements Job {

    private List<MaqStf> listSlotStf;
    private DaoMaquinas daoMaquinas;
    private LeeProperties parametros;
    private List<CajaStf> listCajaStf;
    private List<VisitaStf> listVisitaStf;
    private DaoVisita daoVisita;
    private List<HotelStf> listHotelStf;
    private DaoHotel daoHotel;
    private DaoCaja daoCaja;
    private List<GastroStf> listGastroStf;
    private DaoGastro daoGastro;
    private List<BingoStf> listBingoStf;
    private DaoBingo daoBingo;

    private JCheckBox cbMaqStf;
    private JCheckBox cbMesasStf;
    private JCheckBox cbHotelStf;
    private JCheckBox cbGastroStf;
    private JCheckBox cbVisitasStf;
    private JCheckBox cbBingoStf;

    JTextArea txtLog;
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat formatoVisualizaFecha = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        try {
            SchedulerContext schedulerContext = arg0.getScheduler().getContext();
            this.txtLog = (JTextArea) schedulerContext.get("txtLog");
            this.cbMaqStf = (JCheckBox) schedulerContext.get("cbMaqStf");
            this.cbMesasStf = (JCheckBox) schedulerContext.get("cbMesasStf");
            this.cbHotelStf = (JCheckBox) schedulerContext.get("cbHotelStf");
            this.cbGastroStf = (JCheckBox) schedulerContext.get("cbGastroStf");
            this.cbVisitasStf = (JCheckBox) schedulerContext.get("cbVisitasStf");
            this.cbBingoStf = (JCheckBox) schedulerContext.get("cbBingoStf");
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
            
            if (cbMaqStf.isSelected()) {
                LeerXMLMaqStafe();
            }
            if (cbMesasStf.isSelected()) {
                LeerXMLCajaStafe();
            }
            if (cbHotelStf.isSelected()) {
                LeerXMLHotelStafe();
            }
            if (cbGastroStf.isSelected()) {
                LeerXMLGastroStafe();
            }
            if (cbVisitasStf.isSelected()) {
                LeerXMLVisitaStafe();
            }
             if (cbBingoStf.isSelected()) {
                LeerXMLBingoStafe();
            }

        } catch (Exception ex) {
            txtLog.append("Error: " + ex);
        }

    }

    public void LeerXMLMaqStafe() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqStf>.
        txtLog.append("\nTarea lectura XML Maq Stafe inicia: " + formato.format(new Date()));
        parametros = new LeeProperties();
        listSlotStf = new ArrayList<>();
        for (RootXmlMaqStf root : new TraductorXmlMaqStf().leerXML(txtLog, parametros.getRuta_xml_maq_stf(), parametros.getRuta_xml_maq_stf_procesados())) {
            listSlotStf.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Stafe---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (MaqStf slot : listSlotStf) {

            daoMaquinas = new DaoMaquinas(txtLog);
            if (daoMaquinas.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe Slot " + slot.getNumero_Maquina() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe Slot " + slot.getNumero_Maquina() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }

    public void LeerXMLCajaStafe() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqStf>.
        parametros = new LeeProperties();
        listCajaStf = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Caja Stafe inicia: " + formato.format(new Date()));

        for (RootCajaStf root : new TraductorXmlCajaStf().leerXML(txtLog, parametros.getRuta_xml_caja_stf(), parametros.getRuta_xml_caja_stf_procesados())) {
            listCajaStf.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Stafe---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (CajaStf caja : listCajaStf) {

            daoCaja = new DaoCaja(txtLog);
            if (daoCaja.save(caja) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe Caja concepto " + caja.getConcepto() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe Caja concepto" + caja.getConcepto() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLVisitaStafe() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqStf>.
        parametros = new LeeProperties();
        listVisitaStf = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Visitas Stafe inicia: " + formato.format(new Date()));

        for (RootVisitaStf root : new TraductorXmlVisitaStf().leerXML(txtLog, parametros.getRuta_xml_visita_stf(), parametros.getRuta_xml_visita_stf_procesados())) {
            listVisitaStf.addAll(root.getListTable());
        }
        txtLog.append("\n\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Stafe----------------");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (VisitaStf slot : listVisitaStf) {

            daoVisita = new DaoVisita(txtLog);
            if (daoVisita.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe visitas hora " + slot.getHora() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe visitas hora " + slot.getHora() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLHotelStafe() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqStf>.
        parametros = new LeeProperties();
        listHotelStf = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Hotel Stafe inicia: " + formato.format(new Date()));
        for (RootHotelStf root : new TraductorXMLHotelStf().leerXML(txtLog, parametros.getRuta_xml_hostel_stf(), parametros.getRuta_xml_hostel_stf_procesados())) {
            listHotelStf.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Stafe---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (HotelStf hotel : listHotelStf) {

            daoHotel = new DaoHotel(txtLog);
            if (daoHotel.save(hotel) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe hotel " + hotel.getDesde() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe hotel " + hotel.getDesde() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLGastroStafe() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqStf>.
        parametros = new LeeProperties();
        listGastroStf = new ArrayList<>();
        txtLog.append("\nTarea lectura XML Gastronomía Stafe inicia: " + formato.format(new Date()));
        for (RootGastroStf root : new TraductorXMLGastroStf().leerXML(txtLog, parametros.getRuta_xml_hostel_stf(), parametros.getRuta_xml_hostel_stf_procesados())) {
            listGastroStf.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Stafe---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (GastroStf Gastro : listGastroStf) {

            daoGastro = new DaoGastro(txtLog);
            if (daoGastro.save(Gastro) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }
    
     public void LeerXMLBingoStafe() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqStf>.
        parametros = new LeeProperties();
        listBingoStf = new ArrayList<>();
        listBingoStf.addAll(new TraductorXmlBingoStf().leerXML(txtLog, parametros.getRuta_xml_bingo_stf(),parametros.getRuta_xml_bingo_stf_procesados()));

        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Stafe---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (BingoStf Bingo : listBingoStf) {

            daoBingo = new DaoBingo(txtLog);
            if (daoBingo.save(Bingo) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe Bingo concepto " + Bingo.getFechaProcesoFin()+ " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nStafe Bingo concepto" + Bingo.getFechaProcesoFin()+ " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void setTxtLog(JTextArea txtLog) {
        this.txtLog = txtLog;
    }

}
