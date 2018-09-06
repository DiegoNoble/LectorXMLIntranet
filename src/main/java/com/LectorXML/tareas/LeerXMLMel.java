/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.tareas;

import com.LectorXML.bingo.beans.BingoMel;
import com.LectorXML.bingo.traductor.TraductorXmlBingoMel;
import com.LectorXML.caja.beans.CajaMel;
import com.LectorXML.caja.beans.RootCajaMel;
import com.LectorXML.caja.traductor.TraductorXmlCajaMel;
import com.LectorXML.daos.DaoBingo;
import com.LectorXML.daos.DaoCaja;
import com.LectorXML.daos.DaoGastro;
import com.LectorXML.daos.DaoHotel;
import com.LectorXML.maq.beans.MaqMel;
import com.LectorXML.maq.beans.RootXmlMaqMel;
import com.LectorXML.daos.DaoMaquinas;
import com.LectorXML.daos.DaoVisita;
import com.LectorXML.gastro.beans.GastroMel;
import com.LectorXML.gastro.beans.RootGastroMel;
import com.LectorXML.gastro.traductor.TraductorXMLGastroMel;
import com.LectorXML.hotel.beans.HotelMel;
import com.LectorXML.hotel.beans.RootHotelMel;
import com.LectorXML.hotel.traductor.TraductorXMLHotelMel;
import com.LectorXML.maq.traductor.TraductorXmlMaqMel;
import com.LectorXML.utiles.LeeProperties;
import com.LectorXML.visita.beans.RootVisitaMel;
import com.LectorXML.visita.beans.VisitaMel;
import com.LectorXML.visita.traductor.TraductorXmlVisitaMel;
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
public class LeerXMLMel implements Job {

    private List<MaqMel> listSlotMel;
    private DaoMaquinas daoMaquinas;
    private LeeProperties parametros;
    private List<CajaMel> listCajaMel;
    private List<VisitaMel> listVisitaMel;
    private DaoVisita daoVisita;
    private List<HotelMel> listHotelMel;
    private DaoHotel daoHotel;
    private DaoCaja daoCaja;
    private List<GastroMel> listGastroMel;
    private DaoGastro daoGastro;
    private List<BingoMel> listBingoMel;
    private DaoBingo daoBingo;

    private JCheckBox cbMaqMel;
    private JCheckBox cbMesasMel;
    private JCheckBox cbHotelMel;
    private JCheckBox cbGastroMel;
    private JCheckBox cbVisitasMel;
    private JCheckBox cbBingoMel;

    JTextArea txtLog;
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat formatoVisualizaFecha = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        try {
            SchedulerContext schedulerContext = arg0.getScheduler().getContext();
            this.txtLog = (JTextArea) schedulerContext.get("txtLog");
            this.cbMaqMel = (JCheckBox) schedulerContext.get("cbMaqMel");
            this.cbMesasMel = (JCheckBox) schedulerContext.get("cbMesasMel");
            this.cbHotelMel = (JCheckBox) schedulerContext.get("cbHotelMel");
            this.cbGastroMel = (JCheckBox) schedulerContext.get("cbGastroMel");
            this.cbVisitasMel = (JCheckBox) schedulerContext.get("cbVisitasMel");
            this.cbBingoMel = (JCheckBox) schedulerContext.get("cbBingoMel");
            
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
            
            if (cbMaqMel.isSelected()) {
                LeerXMLMaqMelincue();
            }
            if (cbMesasMel.isSelected()) {
                LeerXMLCajaMelincue();
            }
            if (cbHotelMel.isSelected()) {
                LeerXMLHotelMelincue();
            }
            if (cbGastroMel.isSelected()) {
                LeerXMLGastroMelincue();
            }
            if (cbVisitasMel.isSelected()) {
                LeerXMLVisitaMelincue();
            }
            if (cbBingoMel.isSelected()) {
                LeerXMLBingoMelincue();
            }

        } catch (Exception ex) {
            txtLog.append("Error: " + ex);
        }

    }

    public void LeerXMLMaqMelincue() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqMel>.
        txtLog.append("\nTarea lectura XML Maq Melincue inicia: " + formato.format(new Date()));
        parametros = new LeeProperties();
        listSlotMel = new ArrayList<>();
        for (RootXmlMaqMel root : new TraductorXmlMaqMel().leerXML(txtLog, parametros.getRuta_xml_maq_mel(), parametros.getRuta_xml_maq_mel_procesados())) {
            listSlotMel.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Melincue---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (MaqMel slot : listSlotMel) {

            daoMaquinas = new DaoMaquinas(txtLog);
            if (daoMaquinas.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue Slot " + slot.getNumero_Maquina() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue Slot " + slot.getNumero_Maquina() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }

    public void LeerXMLCajaMelincue() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqMel>.
        parametros = new LeeProperties();
        listCajaMel = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Caja Melincue inicia: " + formato.format(new Date()));

        for (RootCajaMel root : new TraductorXmlCajaMel().leerXML(txtLog, parametros.getRuta_xml_caja_mel(), parametros.getRuta_xml_caja_mel_procesados())) {
            listCajaMel.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Melincue---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (CajaMel caja : listCajaMel) {

            daoCaja = new DaoCaja(txtLog);
            if (daoCaja.save(caja) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue Caja concepto " + caja.getConcepto() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue Caja concepto" + caja.getConcepto() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLVisitaMelincue() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqMel>.
        parametros = new LeeProperties();
        listVisitaMel = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Visitas Melincue inicia: " + formato.format(new Date()));

        for (RootVisitaMel root : new TraductorXmlVisitaMel().leerXML(txtLog, parametros.getRuta_xml_visita_mel(), parametros.getRuta_xml_visita_mel_procesados())) {
            listVisitaMel.addAll(root.getListTable());
        }
        txtLog.append("\n\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Melincue----------------");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (VisitaMel slot : listVisitaMel) {

            daoVisita = new DaoVisita(txtLog);
            if (daoVisita.save(slot) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue visitas hora " + slot.getHora() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue visitas hora " + slot.getHora() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLHotelMelincue() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqMel>.
        parametros = new LeeProperties();
        listHotelMel = new ArrayList<>();

        txtLog.append("\nTarea lectura XML Hotel Melincue inicia: " + formato.format(new Date()));
        for (RootHotelMel root : new TraductorXMLHotelMel().leerXML(txtLog, parametros.getRuta_xml_hostel_mel(), parametros.getRuta_xml_hostel_mel_procesados())) {
            listHotelMel.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Melincue---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (HotelMel hotel : listHotelMel) {

            daoHotel = new DaoHotel(txtLog);
            if (daoHotel.save(hotel) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue hotel " + hotel.getDesde() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue hotel " + hotel.getDesde() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void LeerXMLGastroMelincue() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqMel>.
        parametros = new LeeProperties();
        listGastroMel = new ArrayList<>();
        txtLog.append("\nTarea lectura XML Gastronomía Melincue inicia: " + formato.format(new Date()));
        for (RootGastroMel root : new TraductorXMLGastroMel().leerXML(txtLog, parametros.getRuta_xml_hostel_mel(), parametros.getRuta_xml_hostel_mel_procesados())) {
            listGastroMel.addAll(root.getListTable());
        }
        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------Melincue---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (GastroMel Gastro : listGastroMel) {

            daoGastro = new DaoGastro(txtLog);
            if (daoGastro.save(Gastro) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue Gastro " + Gastro.getSector() + ", " + Gastro.getDescripcion() + " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }

        }
    }
    
     public void LeerXMLBingoMelincue() {

        //Lee archivos XML contenidos en la carpeta indicada en los parametros del sistema y almacena en memoria los datos 
        //dentro del ArrayList<DatosXmlMaqMel>.
        parametros = new LeeProperties();
        listBingoMel = new ArrayList<>();
        listBingoMel.addAll(new TraductorXmlBingoMel().leerXML(txtLog, parametros.getRuta_xml_bingo_mel(),parametros.getRuta_xml_bingo_mel_procesados()));

        txtLog.append("\nInicio proceso registro en BD");
        txtLog.append("\n\n----------------MELINCUE---------------- ");
        //Inicia el proceso de guardado en la base de datos, recorre uno a uno los objetos del array.
        for (BingoMel Bingo : listBingoMel) {

            daoBingo = new DaoBingo(txtLog);
            if (daoBingo.save(Bingo) == true) {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue Bingo concepto " + Bingo.getFechaProcesoFin()+ " OK!");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            } else {
                txtLog.append("\n----------------------------");
                txtLog.append("\nMelincue Bingo concepto" + Bingo.getFechaProcesoFin()+ " error! ");
                txtLog.setCaretPosition(txtLog.getDocument().getLength());
            }
        }
    }

    public void setTxtLog(JTextArea txtLog) {
        this.txtLog = txtLog;
    }

}
