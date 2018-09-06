/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.tareas;

import com.LectorXML.caja.beans.CajaRiv;
import com.LectorXML.caja.beans.RootCajaRiv;
import com.LectorXML.caja.traductor.TraductorXmlCajaRiv;
import com.LectorXML.daos.DaoCaja;
import com.LectorXML.daos.DaoGastro;
import com.LectorXML.daos.DaoHotel;
import com.LectorXML.maq.beans.MaqRiv;
import com.LectorXML.maq.beans.RootXmlMaqRiv;
import com.LectorXML.daos.DaoMaquinas;
import com.LectorXML.daos.DaoVisita;
import com.LectorXML.gastro.beans.GastroRiv;
import com.LectorXML.gastro.beans.RootGastroRiv;
import com.LectorXML.gastro.traductor.TraductorXMLGastroRiv;
import com.LectorXML.hotel.beans.HotelRiv;
import com.LectorXML.hotel.beans.RootHotelRiv;
import com.LectorXML.hotel.traductor.TraductorXMLHotelRiv;
import com.LectorXML.maq.traductor.TraductorXmlMaqRiv;
import com.LectorXML.utiles.LeeProperties;
import com.LectorXML.visita.beans.RootVisitaRiv;
import com.LectorXML.visita.beans.VisitaRiv;
import com.LectorXML.visita.traductor.TraductorXmlVisitaRiv;
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
public class LeerXMLRiv implements Job {

    private List<MaqRiv> listSlotRiv;
    private DaoMaquinas daoMaquinas;
    private LeeProperties parametros;
    private List<CajaRiv> listCajaRiv;
    public List<VisitaRiv> listVisitaRiv;
    public DaoVisita daoVisita;
    private List<HotelRiv> listHotelRiv;
    private DaoHotel daoHotel;
    private DaoCaja daoCaja;
    private List<GastroRiv> listGastroRiv;
    private DaoGastro daoGastro;

    private JCheckBox cbMaqRiv;
    private JCheckBox cbMesasRiv;
    private JCheckBox cbHotelRiv;
    private JCheckBox cbGastroRiv;
    private JCheckBox cbVisitasRiv;

    JTextArea txtLog;
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat formatoVisualizaFecha = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        try {
            SchedulerContext schedulerContext = arg0.getScheduler().getContext();
            this.txtLog = (JTextArea) schedulerContext.get("txtLog");
            this.cbMaqRiv = (JCheckBox) schedulerContext.get("cbMaqRiv");
            this.cbMesasRiv = (JCheckBox) schedulerContext.get("cbMesasRiv");
            this.cbHotelRiv = (JCheckBox) schedulerContext.get("cbHotelRiv");
            this.cbGastroRiv = (JCheckBox) schedulerContext.get("cbGastroRiv");
            this.cbVisitasRiv = (JCheckBox) schedulerContext.get("cbVisitasRiv");
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
            
            if (cbMaqRiv.isSelected()) {
                LeerXMLMaqRivera();
            }
            if (cbMesasRiv.isSelected()) {
                LeerXMLCajaRivera();
            }
            if (cbHotelRiv.isSelected()) {
                LeerXMLHotelRivera();
            }
            if (cbGastroRiv.isSelected()) {
                LeerXMLGastroRivera();
            }
            if (cbVisitasRiv.isSelected()) {
                LeerXMLVisitaRivera();
            }

        } catch (Exception ex) {
            txtLog.append("Error: " + ex);
        }

    }

    public void LeerXMLMaqRivera() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqRiv>.
        txtLog.append("\nTarea lectura XML Maq Rivera inicia: " + formato.format(new Date()));
        parametros = new LeeProperties();
        listSlotRiv = new ArrayList<>();
        
        for (RootXmlMaqRiv root : new TraductorXmlMaqRiv().leerXML(txtLog, parametros.getRuta_xml_maq_riv(), parametros.getRuta_xml_maq_riv_procesados(),txtLog)) {
            listSlotRiv.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------RIVERA---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (MaqRiv slot : listSlotRiv) {

            daoMaquinas = new DaoMaquinas(txtLog);
            if (daoMaquinas.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera Slot " + slot.getNumero_Maquina() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera Slot " + slot.getNumero_Maquina() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }

    public void LeerXMLCajaRivera() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqRiv>.
        parametros = new LeeProperties();
        listCajaRiv = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Caja Rivera inicia: " + formato.format(new Date()));

        for (RootCajaRiv root : new TraductorXmlCajaRiv().leerXML(txtLog, parametros.getRuta_xml_caja_riv(), parametros.getRuta_xml_caja_riv_procesados())) {
            listCajaRiv.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------RIVERA---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (CajaRiv caja : listCajaRiv) {

            daoCaja = new DaoCaja(txtLog);
            if (daoCaja.save(caja) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera Caja concepto " + caja.getConcepto() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera Caja concepto" + caja.getConcepto() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLVisitaRivera() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqMel>.
        parametros = new LeeProperties();
        listVisitaRiv = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Visitas Rivera inicia: " + formato.format(new Date()));

        for (RootVisitaRiv root : new TraductorXmlVisitaRiv().leerXML(txtLog, parametros.getRuta_xml_visita_riv(), parametros.getRuta_xml_visita_riv_procesados())) {
            listVisitaRiv.addAll(root.getListTable());
        }
        txtLog.append("\n\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------RIVERA----------------");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (VisitaRiv slot : listVisitaRiv) {

            daoVisita = new DaoVisita(txtLog);
            if (daoVisita.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera visitas hora " + slot.getHora() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera visitas hora " + slot.getHora() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLHotelRivera() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqRiv>.
        parametros = new LeeProperties();
        listHotelRiv = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Hotel Rivera inicia: " + formato.format(new Date()));
        for (RootHotelRiv root : new TraductorXMLHotelRiv().leerXML(txtLog, parametros.getRuta_xml_hostel_riv(), parametros.getRuta_xml_hostel_riv_procesados())) {
            listHotelRiv.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------RIVERA---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (HotelRiv hotel : listHotelRiv) {

            daoHotel = new DaoHotel(txtLog);
            if (daoHotel.save(hotel) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera hotel " + hotel.getDesde() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera hotel " + hotel.getDesde() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLGastroRivera() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqRiv>.
        parametros = new LeeProperties();
        listGastroRiv = new ArrayList<>();
        txtLog.append("\nTarea lectura XML Gastronomía Rivera inicia: " + formato.format(new Date()));
        for (RootGastroRiv root : new TraductorXMLGastroRiv().leerXML(txtLog, parametros.getRuta_xml_hostel_riv(), parametros.getRuta_xml_hostel_riv_procesados())) {
            listGastroRiv.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------RIVERA---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (GastroRiv Gastro : listGastroRiv) {

            daoGastro = new DaoGastro(txtLog);
            if (daoGastro.save(Gastro) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nRivera Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }

    public void setTxtLog(JTextArea txtLog) {
        this.txtLog = txtLog;
    }

}
