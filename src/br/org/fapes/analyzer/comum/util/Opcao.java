package br.org.fapes.analyzer.comum.util;

/**
 *
 * @author ancos
 * this is the second commit
 * 
 * 
 */
public enum Opcao {

    ENVIAR_MICRO("MICRO"),
    ENVIAR_IBM("IBM");

    private String opcao;
    
    Opcao(String opcao) {
        this.opcao = opcao;
    }

    public String getOpcao() {
        return opcao;
    }
    

}
