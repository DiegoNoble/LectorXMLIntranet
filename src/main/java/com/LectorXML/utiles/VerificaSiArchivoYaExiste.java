/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LectorXML.utiles;

import java.io.File;

/**
 *
 * @author Diego
 */
public class VerificaSiArchivoYaExiste {

    public Boolean verificar(File AnalizarArchivo, String rutaProcesados) {

        Boolean existe = false;

        File directorioProcesados = new File(rutaProcesados);
        File[] archivosProcesados = directorioProcesados.listFiles();
        for (File archivoProcesado : archivosProcesados) {
            if (AnalizarArchivo.getName().equals(archivoProcesado.getName())) {
                existe = true;
            }
        }
        return existe;

    }
}
