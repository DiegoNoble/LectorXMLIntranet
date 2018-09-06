package com.LectorXML.maq.traductor;

import com.LectorXML.maq.beans.MaqSal;
import com.LectorXML.maq.beans.RootXmlMaqSal;
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

public class TraductorXmlMaqSal {

    public List<RootXmlMaqSal> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootXmlMaqSal> roots = new ArrayList();
        log.append("\n --------------------------------");
        log.append("\n Leyendo archivos XML Salto");
        try {
            File directorio = new File(ruta);
            if (directorio.listFiles() != null) {
                File[] archivos = directorio.listFiles();
                for (File archivo : archivos) {
                    log.append("\n" + archivo.toString());
                    if (new VerificaSiArchivoYaExiste().verificar(archivo, rutaProcesados) == false) {
                        
                        log.setCaretPosition(log.getDocument().getLength());

                        FileReader reader = new FileReader(new File(archivo.toString()));
                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootXmlMaqSal.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootXmlMaqSal root = (RootXmlMaqSal) xStream.fromXML(reader);
                        roots.add(root);
                        log.append("\n Inicia lectura datos slots");
                        for (MaqSal slot : root.getListTable()) {
                            slot.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        //Mueve los archivos a la carpeta Procesados
                        Path origen = Paths.get(archivo.getPath());
                        Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                        log.append("\n Lectura datos slots Salto finalizada correctamente, cantidad slots leidos: " + root.getListTable().size());
                    } else {
                        log.append("\n Archivo " + archivo.getName() + " ya fue procesado anteriormente");
                    }
                }
            } else {
                log.append("\n No se encontraron archivos XML de Salto");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

}
