/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.fapes.analyzer.controle;

import java.io.File;

/**
 *
 * @author ancos
 */
public class DisplayDirectoryAndFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        displayIt(new File("T:\\DEPREV\\Projeto Sistema Mestra"));
    }

    public static void displayIt(File node) {

//        System.out.println(node.getAbsoluteFile());

        if (node.isDirectory()) {
        System.out.println(node.getAbsoluteFile());
    
            String[] subNode = node.list();
            for (String filename : subNode) {
                displayIt(new File(node, filename));
            }
        }
    }

}
