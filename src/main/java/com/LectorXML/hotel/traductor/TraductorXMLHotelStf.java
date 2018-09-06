package com.LectorXML.hotel.traductor;

import com.LectorXML.hotel.beans.HotelStf;
import com.LectorXML.hotel.beans.RootHotelStf;
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

public class TraductorXMLHotelStf {

    public List<RootHotelStf> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootHotelStf> roots = new ArrayList();
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
                    int indexOf = nombre.indexOf("arion");
                    if (indexOf != -1) {
                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootHotelStf.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootHotelStf root = (RootHotelStf) xStream.fromXML(reader);
                        roots.add(root);
                        for (HotelStf dato : root.getListTable()) {
                            dato.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        //Mueve los archivos a la carpeta Procesados
                        Path origen = Paths.get(archivo.getPath());
                        Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                        Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);

                        log.append("\n Lectura datos Hotel Santa Fe finalizada correctamente, cantidad datos leidos: " + root.getListTable().size());
                    }
                }
            } else {
                log.append("\n No se encontraron archivos XML Hotel de Santa Fe");

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }
}
