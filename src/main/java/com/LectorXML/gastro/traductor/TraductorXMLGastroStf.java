package com.LectorXML.gastro.traductor;

import com.LectorXML.gastro.beans.GastroStf;
import com.LectorXML.gastro.beans.RootGastroStf;
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

public class TraductorXMLGastroStf {

    public List<RootGastroStf> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootGastroStf> roots = new ArrayList();
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
                    String nombre = archivo.getName();
                    int indexOf = nombre.indexOf("hostel");
                    if (indexOf != -1) {
                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootGastroStf.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootGastroStf root = (RootGastroStf) xStream.fromXML(reader);
                        roots.add(root);
                        for (GastroStf dato : root.getListTable()) {
                            dato.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        //Mueve los archivos a la carpeta Procesados
                        Path origen = Paths.get(archivo.getPath());
                        Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                        Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            } else {
                log.append("\n No se encontraron archivos XML Gastro de Santa Fe");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

}
