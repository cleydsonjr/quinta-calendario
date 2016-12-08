package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.controller.TelaInicialController;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

import java.io.PrintStream;

public class TelaInicialConsole extends AbstractTelaCabecalho implements TelaInicial {

    public TelaInicialConsole(PrintStream output) {
        super(output);
    }

    public static void mensagemSaida() {
        System.out.println(" - Finalizando programa - ");
    }

    @Override
    public void exibaOpcoes() {
        exibaCabecalho();
        int opcao = new EntradaConsole().pergunteInteiro(desenharOpcoes());
        new TelaInicialController().redirect(opcao);
    }

    @Override
    public int pergunteOpcao() {
        return 0;
    }

    public String desenharOpcoes() {
        StringBuilder tela = new StringBuilder();
        tela.append("Bem vindo \n")
                .append("Selecione uma opção  \n")
                .append("1 - Menu Eventos 	  \n")
                .append("2 - Menu Regional 	  \n")
                .append("3 - Menu Categoria   \n")
                .append("4 - Menu Instituto   \n")
                .append("5 - Logar **Opção em desenvolvimento**\n")
                .append("6 - Sair 			  \n");
        return tela.toString();
    }
}
