package com.LectorXML.visita.traductor;

import com.LectorXML.utiles.MeDateConverter;
import com.LectorXML.visita.beans.RootVisitaMel;
import com.LectorXML.visita.beans.VisitaMel;
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

public class TraductorXmlVisitaMel {

    public List<RootVisitaMel> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootVisitaMel> roots = new ArrayList();
        log.append("\n Leyendo archivos XML: ");
        try {
            File directorio = new File(ruta);
            if (directorio.listFiles() != null) {
                File[] archivos = directorio.listFiles();
                for (File archivo : archivos) {
                    //System.out.println(archivo.getName());
                    log.append("\n" + archivo.toString());
                    log.setCaretPosition(log.getDocument().getLength());

                    FileReader reader = new FileReader(new File(archivo.toString()));

                    XStream xStream = new XStream();
                    xStream.processAnnotations(RootVisitaMel.class);
                    xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                    RootVisitaMel root = (RootVisitaMel) xStream.fromXML(reader);
                    roots.add(root);
                    
                    log.append("\n Inicia lectura datos visitas");
                    for (VisitaMel dato : root.getListTable()) {
                        dato.setNombre_Archivo(archivo.getName());
                    }
                    reader.close();
                    Path origen = Paths.get(archivo.getPath());
                    Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                    Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    log.append("\n Lectura datos visitas Melincue finalizada correctamente, cantidad datos leidos: " + root.getListTable().size());
                }
            } else {
                log.append("\n No se encontraron archivos XML Visitas de Melincue ");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

}
