package com.LectorXML.gastro.traductor;

import com.LectorXML.gastro.beans.GastroSal;
import com.LectorXML.gastro.beans.RootGastroSal;
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

public class TraductorXMLGastroSal {

    public List<RootGastroSal> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootGastroSal> roots = new ArrayList();
        log.append("\n Leyendo archivos XML: ");
        try {
            File directorio = new File(ruta);
            FilenameFilter filtroArchivos = new FilenameFilter() {
                @Override
                public boolean accept(File file, String name) {
                    if (name.contains("hostel")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            if (directorio.listFiles() != null) {
                File[] archivos = directorio.listFiles(filtroArchivos);
                for (File archivo : archivos) {
                    //System.out.println(archivo.getName());
                    log.append("\n" + archivo.toString());
                    if (new VerificaSiArchivoYaExiste().verificar(archivo, rutaProcesados) == false) {
                        log.setCaretPosition(log.getDocument().getLength());

                        FileReader reader = new FileReader(new File(archivo.toString()));

                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootGastroSal.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootGastroSal root = (RootGastroSal) xStream.fromXML(reader);
                        roots.add(root);
                        for (GastroSal dato : root.getListTable()) {
                            dato.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        //Mueve los archivos a la carpeta Procesados
                        Path origen = Paths.get(archivo.getPath());
                        Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);

                        log.append("\n Lectura datos Gastro Salto finalizada correctamente, cantidad datos leidos: " + root.getListTable().size());

                    } else {
                        log.append("\n Archivo " + archivo.getName() + " ya fue procesado anteriormente");
                    }
                }
            } else {
                log.append("\n No se encontraron archivos XML Gastro de Salto");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

}
