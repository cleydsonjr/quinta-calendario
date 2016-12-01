package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.view.TelaInicial;
import java.io.IOException;
import java.io.InputStream;

import java.io.PrintStream;
import org.apache.commons.io.IOUtils;

public class TelaInicialConsole extends AbstractTelaCabecalho implements TelaInicial {
    private String conteudoOpcoes;
    
    private PrintStream output;
        
    private static final String CAMINHO_OPCOES = "/view/opcoes.txt";
    public TelaInicialConsole(PrintStream output) {
        super(output);
    }

    @Override
    public void exibaOpcoes() {
        this.carregueOpcoes();
        System.out.println(getConteudoOpcoes());
    }
    
    private void carregueOpcoes() {
        InputStream inputStream = this.getClass().getResourceAsStream(CAMINHO_OPCOES);
        try {
            this.conteudoOpcoes = IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException ignored) {
            this.conteudoOpcoes = "";
        }
    }

    @Override
    public int pergunteOpcao() {
        return 0;
    }
    
    private String getConteudoOpcoes() {
        return conteudoOpcoes;
    }

}
