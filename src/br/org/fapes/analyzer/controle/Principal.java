package br.org.fapes.analyzer.controle;

import br.org.fapes.analyzer.visao.Janela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/**
 *
 * @author ancos
 */
public class Principal {

    private static Janela dialogo;
    private static File diretorio;
    private static ArrayList diretorios;
    private static int param;
    private static int contador = 0;

    public static void main(String[] args) {

        dialogo = new Janela();

        dialogo.getBtnLocalizarArquivo().addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                try {
                    param = Integer.parseInt(dialogo.getTxtParametro().getText().toString());
                    dialogo.getTxtAreaInfo().setText("");
                    dialogo.getTxtDiretorio().setText("");

                    selecionarArquivo();

                    dialogo.getTxtAreaInfo().append("R E S U L T A D O");
                    dialogo.getTxtAreaInfo().append("\n");

                    dialogo.getTxtAreaInfo().append("DIRETÓRIOS COM MAIS DE " + param + " CARACTERES:\n");

                    dialogo.getTxtAreaInfo().append("\n");

                    diretorios = new ArrayList();

                    //listarDiretorios(new File(diretorio.getCanonicalPath()));
                    displayFiles(new File(diretorio.getCanonicalPath()));
                    //listarResultado(diretorios);
                    dialogo.getTxtAreaInfo().append("\n");
                    dialogo.getTxtAreaInfo().append("TOTAL DE DIRETÓRIOS COM MAIS DE " + param + " CARACTERES: " + contador);
                    contador = 0;

                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        dialogo.setVisible(true);

    }

    public static void displayFiles(File node) {

        if (node.getPath().length() > param) {
            if (!node.isDirectory()) {
                dialogo.getTxtAreaInfo().append(node.getPath() + "\n");
                contador++;
            }
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                displayFiles(new File(node, filename));
            }
        }

    }

    public static void listarDiretorios(final File directory) throws IOException {

        if (directory.isDirectory()) {

            if (directory.getPath().length() > param) {
                try {
                    dialogo.getTxtAreaInfo().append(directory.getCanonicalPath() + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                contador++;
            }
            //            String[] subDirectory = directory.list();
            //            if (subDirectory != null) {
            //                for (String dir : subDirectory) {
            //                    try {
            //                        listarDiretorios(new File(directory + File.separator + dir));
            //                    } catch (IOException ex) {
            //                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            //                    }
            //                }
            //            }
            @SuppressWarnings("MismatchedReadAndWriteOfArray")
            File[] files = directory.listFiles();
            if (files != null) {
                for (File f : files) {
                    try {
                        System.out.println(f.getCanonicalFile().toString());
                        listarDiretorios(new File(directory + File.separator + f));
                    } catch (IOException e) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, e);
                    }
                }

            }

        }
    }

    private static void selecionarArquivo() throws IOException {

        JFileChooser fc = new JFileChooser("user.home");
        fc.setDialogTitle("SELECIONAR DIRETÓRIO");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fc.showDialog(null, "OK") == JFileChooser.APPROVE_OPTION) {
            diretorio = fc.getSelectedFile();
            dialogo.getTxtDiretorio().setText(diretorio.getAbsolutePath());
        }

    }

    private static void listarResultado(ArrayList dir) {

        for (int i = 0; i < dir.size(); ++i) {
            String arq = (String) dir.get(i);
            dialogo.getTxtAreaInfo().append(arq);
        }
        dialogo.getTxtAreaInfo().append("\n");
        dialogo.getTxtAreaInfo().append("TOTAL DE DIRETÓRIOS COM MAIS DE " + param + " CARACTERES: " + contador);

    }

}
