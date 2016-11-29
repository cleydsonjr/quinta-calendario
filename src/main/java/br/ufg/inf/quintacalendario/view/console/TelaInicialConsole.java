package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.view.TelaInicial;

import java.io.PrintStream;

public class TelaInicialConsole extends AbstractTelaCabecalho implements TelaInicial {

    public TelaInicialConsole(PrintStream output) {
        super(output);
    }

    @Override
    public void exibaOpcoes() {

    }

    @Override
    public int pergunteOpcao() {
        return 0;
    }

}
