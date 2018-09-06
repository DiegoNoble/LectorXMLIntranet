package com.LectorXML.visita.traductor;

import com.LectorXML.utiles.MeDateConverter;
import com.LectorXML.utiles.VerificaSiArchivoYaExiste;
import com.LectorXML.visita.beans.RootVisitaRiv;
import com.LectorXML.visita.beans.VisitaRiv;
import java.io.File;
import java.io.FileReader;

import com.thoughtworks.xstream.XStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTextArea;

public class TraductorXmlVisitaRiv {

    public List<RootVisitaRiv> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<RootVisitaRiv> roots = new ArrayList();
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
                    xStream.processAnnotations(RootVisitaRiv.class);
                    xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                    RootVisitaRiv root = (RootVisitaRiv) xStream.fromXML(reader);
                    roots.add(root);

                    log.append("\n Inicia lectura datos visitas");

                    for (VisitaRiv dato : root.getListTable()) {
                        dato.setNombre_Archivo(archivo.getName());
                        dato.setSala(archivo.getName().substring(0, 2));
                        dato.setFecha_calendario(dato.getFecha());
                        if (dato.getHora() >= 0 && dato.getHora() <= 6) {
                            Calendar fecha_calendario = Calendar.getInstance();
                            fecha_calendario.setTime(dato.getFecha());
                            fecha_calendario.add(Calendar.DAY_OF_MONTH, 1);
                            dato.setFecha_calendario(fecha_calendario.getTime());
                        }
                    }
                    reader.close();
                    Path origen = Paths.get(archivo.getPath());
                    Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                    Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    log.append("\n Lectura datos visitas Rivera finalizada correctamente, cantidad datos leidos: " + root.getListTable().size());
               } else {
                        log.append("\n Archivo " + archivo.getName() + " ya fue procesado anteriormente");
                    }
                }
            } else {
                log.append("\n No se encontraron archivos XML Visitas de Rivera ");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

}
