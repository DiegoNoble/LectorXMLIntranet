package com.LectorXML.caja.traductor;

import com.LectorXML.caja.beans.CajaOva;
import com.LectorXML.caja.beans.RootCajaOva;
import com.LectorXML.daos.DaoCaja;
import com.LectorXML.utiles.MeDateConverter;
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

public class TraductorXmlCajaOva {

    DaoCaja daoCaja;

    public List<RootCajaOva> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootCajaOva> roots = new ArrayList();
        log.append("\n Leyendo archivos XML: ");
        try {
            File directorio = new File(ruta);
            if (directorio.listFiles() != null) {
                File[] archivos = directorio.listFiles();
                for (File archivo : archivos) {
                    daoCaja = new DaoCaja(log);
                    List BuscaPor = daoCaja.BuscaPor(CajaOva.class, "Nombre_Archivo", archivo.getName());
                    if (BuscaPor.isEmpty()) {
                        //System.out.println(archivo.getName());
                        log.append("\n" + archivo.toString());
                        log.setCaretPosition(log.getDocument().getLength());

                        FileReader reader = new FileReader(new File(archivo.toString()));

                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootCajaOva.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootCajaOva root = (RootCajaOva) xStream.fromXML(reader);
                        roots.add(root);
                        for (CajaOva dato : root.getListTable()) {
                            dato.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        log.append("\n Lectura datos caja Ovalle finalizada correctamente, cantidad datos leidos: " + root.getListTable().size());
                    } else {
                        log.append("\n Archivo " + archivo.getName() + " ya procesado anteriormente");
                    }
                    Path origen = Paths.get(archivo.getPath());
                    Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                    Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);

                }
            } else {
                log.append("\n No se encontraron archivos XML Caja de Ovalle");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

}
