package com.LectorXML.caja.traductor;

import com.LectorXML.caja.beans.CajaRiv;
import com.LectorXML.caja.beans.RootCajaRiv;
import com.LectorXML.daos.DaoCaja;
import com.LectorXML.utiles.MeDateConverter;
import com.LectorXML.utiles.VerificaSiArchivoYaExiste;
import java.io.File;
import java.io.FileReader;

import com.thoughtworks.xstream.XStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class TraductorXmlCajaRiv {

    DaoCaja daoCaja;

    public List<RootCajaRiv> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootCajaRiv> roots = new ArrayList();
        log.append("\n Leyendo archivos XML: ");
        try {
            File directorio = new File(ruta);
            if (directorio.listFiles() != null) {
                File[] archivos = directorio.listFiles();
                for (File archivo : archivos) {

                    if (new VerificaSiArchivoYaExiste().verificar(archivo, rutaProcesados) == false) {
                        //System.out.println(archivo.getName());
                        log.append("\n" + archivo.toString());
                        log.setCaretPosition(log.getDocument().getLength());

                        FileReader reader = new FileReader(new File(archivo.toString()));

                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootCajaRiv.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootCajaRiv root = (RootCajaRiv) xStream.fromXML(reader);
                        roots.add(root);

                        log.append("\n Inicia lectura datos caja");
                        for (CajaRiv dato : root.getListTable()) {
                            dato.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        log.append("\n Lectura datos caja Santa Fe finalizada correctamente, cantidad datos leidos: " + root.getListTable().size());
                    } else {
                        log.append("\n Archivo " + archivo.getName() + " ya procesado anteriormente");
                    }
                    Path origen = Paths.get(archivo.getPath());
                    Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                    Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                }
            } else {
                log.append("\n No se encontraron archivos XML Caja de Melincue: ");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

}
