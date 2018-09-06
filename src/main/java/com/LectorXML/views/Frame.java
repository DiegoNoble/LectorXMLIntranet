/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.views;

import com.LectorXML.daos.DaoCaja;
import com.LectorXML.tareas.LeerXMLRiv;
import com.LectorXML.tareas.LeerXMLSal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JSpinner;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Frame extends java.awt.Frame {

    Calendar horaMinutoEjecucionRivera = Calendar.getInstance();
    Calendar horaMinutoEjecucionSalto = Calendar.getInstance();
    Calendar horaMinutoEjecucionMelincue = Calendar.getInstance();
    Calendar horaMinutoEjecucionOvalle = Calendar.getInstance();
    Calendar horaMinutoEjecucionStaFe = Calendar.getInstance();

    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

    SimpleDateFormat formatoVisualizaFecha = new SimpleDateFormat("dd/MM/yyyy");
    

    Scheduler scheduler;

    SchedulerFactory shedFact = new StdSchedulerFactory();
    static boolean reloj = false;

    public Frame() {
        new DaoCaja(txtLog);
        initComponents();
        dpDesde.setFormats(formatoVisualizaFecha);
        Calendar ayer = Calendar.getInstance();
        ayer.add(Calendar.DAY_OF_YEAR, -1);
        dpDesde.setDate(ayer.getTime());
        lblMelincue.setVisible(false);
        lblOvalle.setVisible(false);
        lblStafe.setVisible(false);
        HoraEjecucionMelincue.setVisible(false);
        HoraEjecucionOvalle.setVisible(false);
        HoraEjecucionStaFe.setVisible(false);

        cbMaqMel.setVisible(false);
        cbVisitasMel.setVisible(false);
        cbHotelMel.setVisible(false);
        cbGastroMel.setVisible(false);
        cbMesasMel.setVisible(false);
        cbBingoMel.setVisible(false);

        cbMaqStf.setVisible(false);
        cbVisitasStf.setVisible(false);
        cbHotelStf.setVisible(false);
        cbGastroStf.setVisible(false);
        cbMesasStf.setVisible(false);
        cbBingoStf.setVisible(false);

        cbMaqOva.setVisible(false);
        cbVisitasOva.setVisible(false);
        cbHotelOva.setVisible(false);
        cbGastroOva.setVisible(false);
        cbMesasOva.setVisible(false);
        cbBingoOva.setVisible(false);

        HoraEjecucionRivera.setEditor(new JSpinner.DateEditor(HoraEjecucionRivera, "HH:mm"));
        HoraEjecucionSalto.setEditor(new JSpinner.DateEditor(HoraEjecucionSalto, "HH:mm"));
        HoraEjecucionMelincue.setEditor(new JSpinner.DateEditor(HoraEjecucionMelincue, "HH:mm"));
        HoraEjecucionOvalle.setEditor(new JSpinner.DateEditor(HoraEjecucionOvalle, "HH:mm"));
        HoraEjecucionStaFe.setEditor(new JSpinner.DateEditor(HoraEjecucionStaFe, "HH:mm"));
        setVisible(true);

        Thread hora = new Thread() {
            public void run() {
                while (!reloj) {
                    lblHora.setText(formatoHora.format(new Date()));
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        hora.start();
    }

    void IniciaLectura() {
        try {
            HoraEjecucionRivera.setEnabled(false);
            HoraEjecucionSalto.setEnabled(false);
            HoraEjecucionMelincue.setEnabled(false);
            HoraEjecucionStaFe.setEnabled(false);
            HoraEjecucionOvalle.setEnabled(false);

            scheduler = shedFact.getScheduler();
            scheduler.getContext().put("txtLog", txtLog);
            scheduler.getContext().put("cbMaqRiv", cbMaqRiv);
            scheduler.getContext().put("cbMesasRiv", cbMesasRiv);
            scheduler.getContext().put("cbVisitasRiv", cbVisitasRiv);
            scheduler.getContext().put("cbHotelRiv", cbHotelRiv);
            scheduler.getContext().put("cbGastroRiv", cbGastroRiv);

            scheduler.getContext().put("cbMaqSal", cbMaqSal);
            scheduler.getContext().put("cbVisitasSal", cbVisitasSal);
            scheduler.getContext().put("cbHotelSal", cbHotelSal);
            scheduler.getContext().put("cbGastroSal", cbGastroSal);

            /*scheduler.getContext().put("cbMaqMel", cbMaqMel);
            scheduler.getContext().put("cbVisitasMel", cbVisitasMel);
            scheduler.getContext().put("cbHotelMel", cbHotelMel);
            scheduler.getContext().put("cbGastroMel", cbGastroMel);
            scheduler.getContext().put("cbMesasMel", cbMesasMel);
            scheduler.getContext().put("cbBingoMel", cbBingoMel);

            scheduler.getContext().put("cbMaqStf", cbMaqStf);
            scheduler.getContext().put("cbVisitasStf", cbVisitasStf);
            scheduler.getContext().put("cbHotelStf", cbHotelStf);
            scheduler.getContext().put("cbGastroStf", cbGastroStf);
            scheduler.getContext().put("cbMesasStf", cbMesasStf);
            scheduler.getContext().put("cbBingoStf", cbBingoStf);

            scheduler.getContext().put("cbMaqOva", cbMaqOva);
            scheduler.getContext().put("cbVisitasOva", cbVisitasOva);
            scheduler.getContext().put("cbHotelOva", cbHotelOva);
            scheduler.getContext().put("cbGastroOva", cbGastroOva);
            scheduler.getContext().put("cbMesasOva", cbMesasOva);
            scheduler.getContext().put("cbBingoOva", cbBingoOva);*/

            txtLog.setCaretPosition(txtLog.getDocument().getLength());

            //TAREA RIVERA
            horaMinutoEjecucionRivera.setTime((Date) HoraEjecucionRivera.getValue());
            txtLog.append("\n Leer XML Rivera inicia hora: " + formatoHora.format(horaMinutoEjecucionRivera.getTime()));
            JobDetail jobLeerXmlRiv = JobBuilder.newJob(LeerXMLRiv.class).build();
            Trigger trgLeerXmlRiv = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(horaMinutoEjecucionRivera.get(Calendar.HOUR_OF_DAY), horaMinutoEjecucionRivera.get(Calendar.MINUTE)))
                    .build();

            scheduler.scheduleJob(jobLeerXmlRiv, trgLeerXmlRiv);

            //TAREA SALTO
            horaMinutoEjecucionSalto.setTime((Date) HoraEjecucionSalto.getValue());
            txtLog.append("\n Leer XML Salto inicia hora: " + formatoHora.format(horaMinutoEjecucionSalto.getTime()));
            JobDetail jobLeerXmlSal = JobBuilder.newJob(LeerXMLSal.class).build();
            Trigger trgLeerXmlSal = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(horaMinutoEjecucionSalto.get(Calendar.HOUR_OF_DAY), horaMinutoEjecucionSalto.get(Calendar.MINUTE)))
                    .build();

            scheduler.scheduleJob(jobLeerXmlSal, trgLeerXmlSal);

            //TAREA MELINCUE
            /*
            horaMinutoEjecucionMelincue.setTime((Date) HoraEjecucionMelincue.getValue());
            txtLog.append("\n Leer XML Melincue inicia hora: " + formatoHora.format(horaMinutoEjecucionMelincue.getTime()));
            JobDetail jobLeerXmlMel = JobBuilder.newJob(LeerXMLMel.class).build();
            Trigger trgLeerXmlMel = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(horaMinutoEjecucionMelincue.get(Calendar.HOUR_OF_DAY), horaMinutoEjecucionMelincue.get(Calendar.MINUTE)))
                    .build();

            scheduler.scheduleJob(jobLeerXmlMel, trgLeerXmlMel);

            //TAREA SANTA FE
            horaMinutoEjecucionStaFe.setTime((Date) HoraEjecucionStaFe.getValue());
            txtLog.append("\n Leer XML Santa Fe inicia hora: " + formatoHora.format(horaMinutoEjecucionStaFe.getTime()));
            JobDetail jobLeerXmlStf = JobBuilder.newJob(LeerXMLStf.class).build();
            Trigger trgLeerXmlStf = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(horaMinutoEjecucionStaFe.get(Calendar.HOUR_OF_DAY), horaMinutoEjecucionStaFe.get(Calendar.MINUTE)))
                    .build();

            scheduler.scheduleJob(jobLeerXmlStf, trgLeerXmlStf);

            //TAREA OVALLE
            horaMinutoEjecucionOvalle.setTime((Date) HoraEjecucionOvalle.getValue());
            txtLog.append("\n Leer XML Ovalle inicia hora: " + formatoHora.format(horaMinutoEjecucionOvalle.getTime()));
            JobDetail jobLeerXmlOva = JobBuilder.newJob(LeerXMLOva.class).build();
            Trigger trgLeerXmlOva = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(horaMinutoEjecucionOvalle.get(Calendar.HOUR_OF_DAY), horaMinutoEjecucionOvalle.get(Calendar.MINUTE)))
                    .build();

            scheduler.scheduleJob(jobLeerXmlOva, trgLeerXmlOva);
             */
            scheduler.start();

        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void detener() {
        try {
            HoraEjecucionRivera.setEnabled(true);
            HoraEjecucionSalto.setEnabled(true);
            HoraEjecucionMelincue.setEnabled(true);
            HoraEjecucionStaFe.setEnabled(true);
            HoraEjecucionOvalle.setEnabled(true);

            scheduler.shutdown();
            txtLog.append("\n Se detiene la agenda a la hora: " + formatoHora.format(new Date()));
            txtLog.append("\n------------------------------------------------");
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
        } catch (SchedulerException ex) {
            txtLog.append("Error " + ex);
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        HoraEjecucionSalto = new javax.swing.JSpinner();
        lblMelincue = new javax.swing.JLabel();
        HoraEjecucionMelincue = new javax.swing.JSpinner();
        lblStafe = new javax.swing.JLabel();
        HoraEjecucionStaFe = new javax.swing.JSpinner();
        lblOvalle = new javax.swing.JLabel();
        HoraEjecucionOvalle = new javax.swing.JSpinner();
        lblHora = new javax.swing.JLabel();
        HoraEjecucionRivera = new javax.swing.JSpinner();
        btnIniciar = new javax.swing.JToggleButton();
        jLabel10 = new javax.swing.JLabel();
        cbMaqRiv = new javax.swing.JCheckBox();
        cbVisitasRiv = new javax.swing.JCheckBox();
        cbMesasRiv = new javax.swing.JCheckBox();
        cbHotelRiv = new javax.swing.JCheckBox();
        cbGastroRiv = new javax.swing.JCheckBox();
        cbMaqStf = new javax.swing.JCheckBox();
        cbVisitasStf = new javax.swing.JCheckBox();
        cbMesasStf = new javax.swing.JCheckBox();
        cbHotelStf = new javax.swing.JCheckBox();
        cbGastroStf = new javax.swing.JCheckBox();
        cbMaqOva = new javax.swing.JCheckBox();
        cbVisitasOva = new javax.swing.JCheckBox();
        cbMesasOva = new javax.swing.JCheckBox();
        cbHotelOva = new javax.swing.JCheckBox();
        cbGastroOva = new javax.swing.JCheckBox();
        cbMaqMel = new javax.swing.JCheckBox();
        cbVisitasMel = new javax.swing.JCheckBox();
        cbMesasMel = new javax.swing.JCheckBox();
        cbHotelMel = new javax.swing.JCheckBox();
        cbGastroMel = new javax.swing.JCheckBox();
        cbMaqSal = new javax.swing.JCheckBox();
        cbVisitasSal = new javax.swing.JCheckBox();
        cbBingoOva = new javax.swing.JCheckBox();
        cbHotelSal = new javax.swing.JCheckBox();
        cbGastroSal = new javax.swing.JCheckBox();
        cbBingoStf = new javax.swing.JCheckBox();
        cbBingoMel = new javax.swing.JCheckBox();
        dpDesde = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        pbar = new javax.swing.JProgressBar();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new java.awt.GridBagLayout());

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane2.setViewportView(txtLog);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane2, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jPanel1, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Salto iniciar a la hora:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jLabel5, gridBagConstraints);

        HoraEjecucionSalto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        HoraEjecucionSalto.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(HoraEjecucionSalto, gridBagConstraints);

        lblMelincue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMelincue.setText("Melincue iniciar a la hora:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(lblMelincue, gridBagConstraints);

        HoraEjecucionMelincue.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        HoraEjecucionMelincue.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(HoraEjecucionMelincue, gridBagConstraints);

        lblStafe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStafe.setText("Santa Fe iniciar a la hora:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(lblStafe, gridBagConstraints);

        HoraEjecucionStaFe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        HoraEjecucionStaFe.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(HoraEjecucionStaFe, gridBagConstraints);

        lblOvalle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOvalle.setText("Ovalle iniciar a la hora:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(lblOvalle, gridBagConstraints);

        HoraEjecucionOvalle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        HoraEjecucionOvalle.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(HoraEjecucionOvalle, gridBagConstraints);

        lblHora.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblHora.setForeground(new java.awt.Color(153, 0, 0));
        lblHora.setText("hora");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(lblHora, gridBagConstraints);

        HoraEjecucionRivera.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        HoraEjecucionRivera.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(HoraEjecucionRivera, gridBagConstraints);

        btnIniciar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnIniciar.setText("INICIAR");
        btnIniciar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnIniciarItemStateChanged(evt);
            }
        });
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(btnIniciar, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Rivera iniciar a la hora:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jLabel10, gridBagConstraints);

        cbMaqRiv.setText("Maquinas");
        cbMaqRiv.setToolTipText("");
        cbMaqRiv.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        jPanel5.add(cbMaqRiv, gridBagConstraints);

        cbVisitasRiv.setText("Público");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel5.add(cbVisitasRiv, gridBagConstraints);

        cbMesasRiv.setText("Mesas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        jPanel5.add(cbMesasRiv, gridBagConstraints);

        cbHotelRiv.setText("Hoteleria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        jPanel5.add(cbHotelRiv, gridBagConstraints);

        cbGastroRiv.setText("Gastronimía");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        jPanel5.add(cbGastroRiv, gridBagConstraints);

        cbMaqStf.setText("Maquinas");
        cbMaqStf.setToolTipText("");
        cbMaqStf.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        jPanel5.add(cbMaqStf, gridBagConstraints);

        cbVisitasStf.setText("Público");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        jPanel5.add(cbVisitasStf, gridBagConstraints);

        cbMesasStf.setText("Mesas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        jPanel5.add(cbMesasStf, gridBagConstraints);

        cbHotelStf.setText("Hoteleria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        jPanel5.add(cbHotelStf, gridBagConstraints);

        cbGastroStf.setText("Gastronimía");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        jPanel5.add(cbGastroStf, gridBagConstraints);

        cbMaqOva.setText("Maquinas");
        cbMaqOva.setToolTipText("");
        cbMaqOva.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel5.add(cbMaqOva, gridBagConstraints);

        cbVisitasOva.setText("Público");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        jPanel5.add(cbVisitasOva, gridBagConstraints);

        cbMesasOva.setText("Mesas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        jPanel5.add(cbMesasOva, gridBagConstraints);

        cbHotelOva.setText("Hoteleria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        jPanel5.add(cbHotelOva, gridBagConstraints);

        cbGastroOva.setText("Gastronimía");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        jPanel5.add(cbGastroOva, gridBagConstraints);

        cbMaqMel.setText("Maquinas");
        cbMaqMel.setToolTipText("");
        cbMaqMel.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        jPanel5.add(cbMaqMel, gridBagConstraints);

        cbVisitasMel.setText("Público");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        jPanel5.add(cbVisitasMel, gridBagConstraints);

        cbMesasMel.setText("Mesas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        jPanel5.add(cbMesasMel, gridBagConstraints);

        cbHotelMel.setText("Hoteleria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        jPanel5.add(cbHotelMel, gridBagConstraints);

        cbGastroMel.setText("Gastronimía");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        jPanel5.add(cbGastroMel, gridBagConstraints);

        cbMaqSal.setText("Maquinas");
        cbMaqSal.setToolTipText("");
        cbMaqSal.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel5.add(cbMaqSal, gridBagConstraints);

        cbVisitasSal.setText("Público");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel5.add(cbVisitasSal, gridBagConstraints);

        cbBingoOva.setText("Bingo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 4;
        jPanel5.add(cbBingoOva, gridBagConstraints);

        cbHotelSal.setText("Hoteleria");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        jPanel5.add(cbHotelSal, gridBagConstraints);

        cbGastroSal.setText("Gastronimía");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        jPanel5.add(cbGastroSal, gridBagConstraints);

        cbBingoStf.setText("Bingo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 5;
        jPanel5.add(cbBingoStf, gridBagConstraints);

        cbBingoMel.setText("Bingo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 3;
        jPanel5.add(cbBingoMel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(dpDesde, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(pbar, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void btnIniciarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnIniciarItemStateChanged
        int state = evt.getStateChange();
        if (state == evt.SELECTED) {
            IniciaLectura();
        } else {
            detener();
        }
    }//GEN-LAST:event_btnIniciarItemStateChanged

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner HoraEjecucionMelincue;
    private javax.swing.JSpinner HoraEjecucionOvalle;
    private javax.swing.JSpinner HoraEjecucionRivera;
    private javax.swing.JSpinner HoraEjecucionSalto;
    private javax.swing.JSpinner HoraEjecucionStaFe;
    private javax.swing.JToggleButton btnIniciar;
    private javax.swing.JCheckBox cbBingoMel;
    private javax.swing.JCheckBox cbBingoOva;
    private javax.swing.JCheckBox cbBingoStf;
    private javax.swing.JCheckBox cbGastroMel;
    private javax.swing.JCheckBox cbGastroOva;
    private javax.swing.JCheckBox cbGastroRiv;
    private javax.swing.JCheckBox cbGastroSal;
    private javax.swing.JCheckBox cbGastroStf;
    private javax.swing.JCheckBox cbHotelMel;
    private javax.swing.JCheckBox cbHotelOva;
    private javax.swing.JCheckBox cbHotelRiv;
    private javax.swing.JCheckBox cbHotelSal;
    private javax.swing.JCheckBox cbHotelStf;
    private javax.swing.JCheckBox cbMaqMel;
    private javax.swing.JCheckBox cbMaqOva;
    private javax.swing.JCheckBox cbMaqRiv;
    private javax.swing.JCheckBox cbMaqSal;
    private javax.swing.JCheckBox cbMaqStf;
    private javax.swing.JCheckBox cbMesasMel;
    private javax.swing.JCheckBox cbMesasOva;
    private javax.swing.JCheckBox cbMesasRiv;
    private javax.swing.JCheckBox cbMesasStf;
    private javax.swing.JCheckBox cbVisitasMel;
    private javax.swing.JCheckBox cbVisitasOva;
    private javax.swing.JCheckBox cbVisitasRiv;
    private javax.swing.JCheckBox cbVisitasSal;
    private javax.swing.JCheckBox cbVisitasStf;
    private org.jdesktop.swingx.JXDatePicker dpDesde;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblMelincue;
    private javax.swing.JLabel lblOvalle;
    private javax.swing.JLabel lblStafe;
    private javax.swing.JProgressBar pbar;
    public javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables

}
