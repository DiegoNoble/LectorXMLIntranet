package com.LectorXML.visita.traductor;

import com.LectorXML.utiles.MeDateConverter;
import com.LectorXML.visita.beans.RootVisitaStf;
import com.LectorXML.visita.beans.VisitaStf;
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

public class TraductorXmlVisitaStf {

    public List<RootVisitaStf> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootVisitaStf> roots = new ArrayList();
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
                    xStream.processAnnotations(RootVisitaStf.class);
                    xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                    RootVisitaStf root = (RootVisitaStf) xStream.fromXML(reader);
                    roots.add(root);
                    
                    log.append("\n Inicia lectura datos visitas");
                    for (VisitaStf dato : root.getListTable()) {
                        dato.setNombre_Archivo(archivo.getName());
                    }
                    reader.close();
                    Path origen = Paths.get(archivo.getPath());
                    Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                    Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    log.append("\n Lectura datos visitas Santa Fe finalizada correctamente, cantidad datos leidos: " + root.getListTable().size());
                }
            } else {
                log.append("\n No se encontraron archivos XML Visitas de Santa Fe ");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

}
