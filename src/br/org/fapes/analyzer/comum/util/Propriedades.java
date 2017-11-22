/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.fapes.analyzer.comum.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author ancos
 */
public class Propriedades {

    public static String getPropriedade(String valor) throws FileNotFoundException {

        String retorno = null;
        Properties properties = new Properties();
        
        try {
            FileInputStream fis = new FileInputStream(new File("config/ftpbndes.properties"));
            properties.load(fis);
            retorno = properties.getProperty(valor);
            fis.close();

            return retorno;

        } catch (IOException e) {
            return null;
        }


    }
}
