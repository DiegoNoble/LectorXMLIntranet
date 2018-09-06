package com.LectorXML.hotel.traductor;

import com.LectorXML.hotel.beans.HotelSal;
import com.LectorXML.hotel.beans.RootHotelSal;
import com.LectorXML.utiles.MeDateConverter;
import com.LectorXML.utiles.VerificaSiArchivoYaExiste;
import java.io.File;
import java.io.FileReader;

import com.thoughtworks.xstream.XStream;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class TraductorXMLHotelSal {

    public List<RootHotelSal> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootHotelSal> roots = new ArrayList();
        log.append("\n Leyendo archivos XML: ");
        try {
            File directorio = new File(ruta);
            FilenameFilter filtroArchivos = new FilenameFilter() {
                @Override
                public boolean accept(File file, String name) {
                    if (name.contains("CMHotal")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            if (directorio.listFiles() != null) {
                File[] archivos = directorio.listFiles(filtroArchivos);
                for (File archivo : archivos) {
                    log.append("\n" + archivo.toString());
                    
                    if (new VerificaSiArchivoYaExiste().verificar(archivo, rutaProcesados) == false) {
                        
                        log.setCaretPosition(log.getDocument().getLength());

                        FileReader reader = new FileReader(new File(archivo.toString()));

                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootHotelSal.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootHotelSal root = (RootHotelSal) xStream.fromXML(reader);
                        roots.add(root);
                        for (HotelSal dato : root.getListTable()) {
                            dato.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        //Mueve los archivos a la carpeta Procesados
                        Path origen = Paths.get(archivo.getPath());
                        Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);

                        log.append("\n Lectura datos Hotel Salto finalizada correctamente, cantidad datos leidos: " + root.getListTable().size());
                    } else {
                        log.append("\n Archivo " + archivo.getName() + " ya fue procesado anteriormente");
                    }
                }
            } else {
                log.append("\n No se encontraron archivos XML Hotel de Salto");

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }
}
