package com.LectorXML.maq.traductor;

import com.LectorXML.daos.DaoMaquinas;
import com.LectorXML.maq.beans.MaqRiv;
import com.LectorXML.maq.beans.RootXmlMaqRiv;
import com.LectorXML.utiles.MeDateConverter;
import java.io.File;
import java.io.FileReader;

import com.thoughtworks.xstream.XStream;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextArea;

public class TraductorXmlMaqRiv {

    DaoMaquinas daoMaquinas;
    File directorio;

    public List<RootXmlMaqRiv> leerXML(JTextArea log, String ruta, String rutaProcesados, JTextArea txtLog) {
        List<RootXmlMaqRiv> roots = new ArrayList();

        log.append("\n --------------------------------");
        log.append("\n Leyendo archivos XML Rivera: ");

        directorio = new File(ruta);

        daoMaquinas = new DaoMaquinas(txtLog);
        Date ultimoFechaProcesada = daoMaquinas.findByMaxDateRiv();

        SimpleDateFormat formatoDesde = new SimpleDateFormat("yyyyMMdd");
        Calendar ayer = Calendar.getInstance();
        ayer.add(Calendar.DAY_OF_YEAR, -1);

        Calendar ultimaFechaProcesado = Calendar.getInstance();
        ultimaFechaProcesado.setTime(ultimoFechaProcesada);
        ultimaFechaProcesado.add(Calendar.DAY_OF_YEAR, 1);

        long end = ayer.getTimeInMillis();
        long start = ultimaFechaProcesado.getTimeInMillis();
        long dias = TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));

        Calendar fechaProcesar = ultimaFechaProcesado;

        for (int i = 0; i <= dias; i++) {

            String nombreArchivo = formatoDesde.format(fechaProcesar.getTime());
            String nombreArchivoCasino = "01-" + nombreArchivo + "MAQ.xml";
            String nombreArchivoSarandi = "02-" + nombreArchivo + "MAQ.xml";

            try {
                if (directorio.listFiles() != null) {
                    log.append("\n Inicia lectura datos slots");
                    File[] archivosCasino = directorio.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.equals(nombreArchivoCasino);
                        }
                    });
                    File[] archivosSarandi = directorio.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.equals(nombreArchivoSarandi);
                        }
                    });

                    for (File archivo : archivosCasino) {

                        log.append("\n\n" + archivo.toString());
                        log.setCaretPosition(log.getDocument().getLength());

                        FileReader reader = new FileReader(new File(archivo.toString()));

                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootXmlMaqRiv.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootXmlMaqRiv root = (RootXmlMaqRiv) xStream.fromXML(reader);
                        roots.add(root);
                        
                        for (MaqRiv slot : root.getListTable()) {
                            slot.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        //Mueve los archivos a la carpeta Procesados
                        Path origen = Paths.get(archivo.getPath());
                        Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);

                    }

                    for (File archivo : archivosSarandi) {

                        log.append("\n\n" + archivo.toString());
                        log.setCaretPosition(log.getDocument().getLength());

                        FileReader reader = new FileReader(new File(archivo.toString()));

                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootXmlMaqRiv.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootXmlMaqRiv root = (RootXmlMaqRiv) xStream.fromXML(reader);
                        roots.add(root);
                        for (MaqRiv slot : root.getListTable()) {
                            slot.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        //Mueve los archivos a la carpeta Procesados
                        Path origen = Paths.get(archivo.getPath());
                        Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);

                    }

                    log.append("\n Lectura datos slots Rivera finalizada correctamente");
                } else {
                    log.append("\n No se encontraron archivos XML de Rivera");
                }

                fechaProcesar.add(Calendar.DAY_OF_YEAR, 1);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return roots;
    }
}
