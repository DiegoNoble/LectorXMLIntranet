/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.tareas;

import com.LectorXML.daos.DaoCaja;
import com.LectorXML.daos.DaoGastro;
import com.LectorXML.daos.DaoHotel;
import com.LectorXML.maq.beans.MaqSal;
import com.LectorXML.maq.beans.RootXmlMaqSal;
import com.LectorXML.daos.DaoMaquinas;
import com.LectorXML.daos.DaoVisita;
import com.LectorXML.gastro.beans.GastroSal;
import com.LectorXML.gastro.beans.RootGastroSal;
import com.LectorXML.gastro.traductor.TraductorXMLGastroSal;
import com.LectorXML.hotel.beans.HotelSal;
import com.LectorXML.hotel.beans.RootHotelSal;
import com.LectorXML.hotel.traductor.TraductorXMLHotelSal;
import com.LectorXML.maq.traductor.TraductorXmlMaqSal;
import com.LectorXML.utiles.LeeProperties;
import com.LectorXML.visita.beans.RootVisitaSal;
import com.LectorXML.visita.beans.VisitaSal;
import com.LectorXML.visita.traductor.TraductorXmlVisitaSal;
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
public class LeerXMLSal implements Job {

    private List<MaqSal> listSlotSal;
    private DaoMaquinas daoMaquinas;
    private LeeProperties parametros;
    public List<VisitaSal> listVisitaSal;
    public DaoVisita daoVisita;
    private List<HotelSal> listHotelSal;
    private DaoHotel daoHotel;
    private List<GastroSal> listGastroSal;
    private DaoGastro daoGastro;

    private JCheckBox cbMaqSal;
    private JCheckBox cbHotelSal;
    private JCheckBox cbGastroSal;
    private JCheckBox cbVisitasSal;

    JTextArea txtLog;
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat formatoVisualizaFecha = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        try {
            SchedulerContext schedulerContext = arg0.getScheduler().getContext();
            this.txtLog = (JTextArea) schedulerContext.get("txtLog");
            this.cbMaqSal = (JCheckBox) schedulerContext.get("cbMaqSal");
            this.cbHotelSal = (JCheckBox) schedulerContext.get("cbHotelSal");
            this.cbGastroSal = (JCheckBox) schedulerContext.get("cbGastroSal");
            this.cbVisitasSal = (JCheckBox) schedulerContext.get("cbVisitasSal");
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
            
            if (cbMaqSal.isSelected()) {
                LeerXMLMaqSalto();
            }
           
            if (cbHotelSal.isSelected()) {
                LeerXMLHotelSalto();
            }
            if (cbGastroSal.isSelected()) {
                LeerXMLGastroSalto();
            }
            if (cbVisitasSal.isSelected()) {
                LeerXMLVisitaSalto();
            }

        } catch (Exception ex) {
            txtLog.append("Error: " + ex);
        }

    }

    public void LeerXMLMaqSalto() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqSal>.
        txtLog.append("\n Tarea lectura XML Maq Salto inicia: " + formato.format(new Date()));
        parametros = new LeeProperties();
        listSlotSal = new ArrayList<>();
        for (RootXmlMaqSal root : new TraductorXmlMaqSal().leerXML(txtLog, parametros.getRuta_xml_maq_sal(), parametros.getRuta_xml_maq_sal_procesados())) {
            listSlotSal.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Salto---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (MaqSal slot : listSlotSal) {

            daoMaquinas = new DaoMaquinas(txtLog);
            if (daoMaquinas.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nSalto Slot " + slot.getNumero_Maquina() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nSalto Slot " + slot.getNumero_Maquina() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }

   

    public void LeerXMLVisitaSalto() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqMel>.
        parametros = new LeeProperties();
        listVisitaSal = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Visitas Salto inicia: " + formato.format(new Date()));

        for (RootVisitaSal root : new TraductorXmlVisitaSal().leerXML(txtLog, parametros.getRuta_xml_visita_sal(), parametros.getRuta_xml_visita_sal_procesados())) {
            listVisitaSal.addAll(root.getListTable());
        }
        txtLog.append("\n\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Salto----------------");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (VisitaSal slot : listVisitaSal) {

            daoVisita = new DaoVisita(txtLog);
            if (daoVisita.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nSalto visitas hora " + slot.getHora() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nSalto visitas hora " + slot.getHora() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLHotelSalto() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqSal>.
        parametros = new LeeProperties();
        listHotelSal = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Hotel Salto inicia: " + formato.format(new Date()));
        for (RootHotelSal root : new TraductorXMLHotelSal().leerXML(txtLog, parametros.getRuta_xml_hostel_sal(), parametros.getRuta_xml_hostel_sal_procesados())) {
            listHotelSal.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Salto---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (HotelSal hotel : listHotelSal) {

            daoHotel = new DaoHotel(txtLog);
            if (daoHotel.save(hotel) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nSalto hotel " + hotel.getDesde() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nSalto hotel " + hotel.getDesde() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLGastroSalto() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqSal>.
        parametros = new LeeProperties();
        listGastroSal = new ArrayList<>();
        txtLog.append("\nTarea lectura XML Gastronomía Salto inicia: " + formato.format(new Date()));
        for (RootGastroSal root : new TraductorXMLGastroSal().leerXML(txtLog, parametros.getRuta_xml_hostel_sal(), parametros.getRuta_xml_hostel_sal_procesados())) {
            listGastroSal.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Salto---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (GastroSal Gastro : listGastroSal) {

            daoGastro = new DaoGastro(txtLog);
            if (daoGastro.save(Gastro) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nSalto Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nSalto Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }

    public void setTxtLog(JTextArea txtLog) {
        this.txtLog = txtLog;
    }

}
