package com.LectorXML.bingo.traductor;

import com.LectorXML.bingo.beans.BingoMel;
import com.LectorXML.utiles.MeDateConverter;
import java.io.File;
import java.io.FileReader;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class TraductorXmlBingoMel {

    public List<BingoMel> leerXML(JTextArea log, String ruta, String rutaProcesados) {
        List<BingoMel> listoBingo = new ArrayList();
        log.append("\n Leyendo archivos XML: ");
        try {
            File directorio = new File(ruta);
            if (directorio.listFiles() != null) {
                File[] archivos = directorio.listFiles();
                for (File archivo : archivos) {
                    //System.out.println(archivo.getName());
                    log.append("\n" + archivo.toString());
                    log.setCaretPosition(log.getDocument().getLength());

                    BufferedReader br = new BufferedReader(new FileReader(archivo));
                    String xml = br.readLine();
                    xml = xml.replace("$ ", "");
                    xml = xml.replace(".", "");
                    xml = xml.replace(",", ".");

                    //FileReader reader = new FileReader(new File(archivo.toString()));
                    XStream xStream = new XStream();
                    xStream.processAnnotations(BingoMel.class);
                    xStream.registerConverter(new MeDateConverter("dd/MM/yyyy"));
                    xStream.ignoreUnknownElements();
                    BingoMel bingo = (BingoMel) xStream.fromXML(xml);
                    //bingo.setNombre_Archivo(archivo.getName());
                    listoBingo.add(bingo);
                    br.close();
                    //reader.close();
                    Path origen = Paths.get(archivo.getPath());
                    Path destino = Paths.get(rutaProcesados + "\\" + archivo.getName());
                    Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    log.append("\n Lectura datos Bingo Melincue finalizada correctamente, cantidad datos leidos: ");
                }
            } else {
                log.append("\n No se encontraron archivos XML Bingo de Melincue");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listoBingo;
    }

}
