package br.org.fapes.analyzer.comum.util;

/**
 *
 * @author ancos
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
