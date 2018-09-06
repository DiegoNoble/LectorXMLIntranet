/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.utiles;

import com.LectorXML.caja.beans.PptoSalto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Diego
 */
public class LeerArchivoExcel {

    JTextArea txtlog;

    public LeerArchivoExcel(JTextArea txtlog) {
        this.txtlog = txtlog;
    }

    public static void Leer(String path) {
        List<PptoSalto> listaPptoSalto = new ArrayList<PptoSalto>();

        try {
            FileInputStream arquivo = new FileInputStream(new File(path));

            HSSFWorkbook workbook = null;
            try {
                workbook = new HSSFWorkbook(arquivo);
            } catch (IOException ex) {
                Logger.getLogger(LeerArchivoExcel.class.getName()).log(Level.SEVERE, null, ex);
            }

            HSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                PptoSalto pptoSalto = new PptoSalto();
                listaPptoSalto.add(pptoSalto);
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0:
                            pptoSalto.setFecha(cell.getStringCellValue());
                            break;
                        case 1:
                            String publicio = cell.getStringCellValue();
                            pptoSalto.setPublico(Double.valueOf(publicio));
                            break;
                        case 3:
                            pptoSalto.setCoin_in_video(cell.getNumericCellValue());
                            break;
                        case 4:
                            pptoSalto.setCoin_in_ruleta(cell.getNumericCellValue());
                            break;
                        case 5:
                            pptoSalto.setCoin_in_total(cell.getNumericCellValue());
                            break;

                    }
                }
            }
            arquivo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
        } catch (IOException ex) {
            Logger.getLogger(LeerArchivoExcel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listaPptoSalto.size() == 0) {
            System.out.println("Nenhum aluno encontrado!");
        } else {

            for (PptoSalto aluno : listaPptoSalto) {
                System.out.println(aluno.toString());

            }

        }

    }
}
