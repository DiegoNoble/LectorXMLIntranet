package com.LectorXML.maq.traductor;

import com.LectorXML.maq.beans.MaqMel;
import com.LectorXML.maq.beans.RootXmlMaqMel;
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

public class TraductorXmlMaqMel {

    public List<RootXmlMaqMel> leerXML(JTextArea log, String ruta,String rutaProcesados) {
        List<RootXmlMaqMel> roots = new ArrayList();
        log.append("\n --------------------------------");
        log.append("\n Leyendo archivos XML Melincue");
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
                    xStream.processAnnotations(RootXmlMaqMel.class);
                    xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                    RootXmlMaqMel root = (RootXmlMaqMel) xStream.fromXML(reader);
                    roots.add(root);

                    log.append("\n Inicia lectura datos slots");
                    for (MaqMel slot : root.getListTable()) {
                        slot.setNombre_Archivo(archivo.getName());
                    }
                    reader.close();
                    //Mueve los archivos a la carpeta Procesados
                    Path origen = Paths.get(archivo.getPath());
                    Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                    Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    log.append("\n Lectura datos slots Melincue finalizada correctamente, cantidad slots leidos: " + root.getListTable().size());
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
