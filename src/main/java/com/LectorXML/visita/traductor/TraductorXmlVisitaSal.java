package com.LectorXML.visita.traductor;

import com.LectorXML.utiles.MeDateConverter;
import com.LectorXML.utiles.VerificaSiArchivoYaExiste;
import com.LectorXML.visita.beans.RootVisitaSal;
import com.LectorXML.visita.beans.VisitaSal;
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

public class TraductorXmlVisitaSal {

    public List<RootVisitaSal> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootVisitaSal> roots = new ArrayList();
        log.append("\n Leyendo archivos XML: ");
        try {
            File directorio = new File(ruta);
            if (directorio.listFiles() != null) {
                File[] archivos = directorio.listFiles();
                for (File archivo : archivos) {
                    //System.out.println(archivo.getName());
                    log.append("\n" + archivo.toString());
                    if (new VerificaSiArchivoYaExiste().verificar(archivo, rutaProcesados) == false) {
                        log.setCaretPosition(log.getDocument().getLength());

                        FileReader reader = new FileReader(new File(archivo.toString()));

                        XStream xStream = new XStream();
                        xStream.processAnnotations(RootVisitaSal.class);
                        xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                        RootVisitaSal root = (RootVisitaSal) xStream.fromXML(reader);
                        roots.add(root);

                        log.append("\n Inicia lectura datos visitas");
                        for (VisitaSal dato : root.getListTable()) {
                            dato.setNombre_Archivo(archivo.getName());
                        }
                        reader.close();
                        Path origen = Paths.get(archivo.getPath());
                        Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        log.append("\n Archivo " + archivo.getName() + " ya fue procesado anteriormente");
                    }
                }
            } else {
                log.append("\n No se encontraron archivos XML Visitas de Salto ");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

}
