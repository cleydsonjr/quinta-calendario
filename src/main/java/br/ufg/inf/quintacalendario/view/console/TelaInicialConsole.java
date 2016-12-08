package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.controller.TelaInicialController;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

import java.io.PrintStream;

public class TelaInicialConsole extends AbstractTelaCabecalho implements TelaInicial {

    public TelaInicialConsole(PrintStream output) {
        super(output);
    }

    @Override
    public void exibaOpcoes() {
    	exibaCabecalho();
        realizarLogin();
    	//int opcao = new EntradaConsole().pergunteInteiro(desenharOpcoesQuandoLogado());
    	//new TelaInicialController().redirect(opcao);
    }

    @Override
    public int pergunteOpcao() {
        return 0;
    }
    
    public String desenharOpcoesQuandoLogado(){
    	StringBuilder tela = new StringBuilder();
    	tela.append("Bem vindo \n")
    		.append("Selecione uma opção  \n")
    		.append("1 - Menu Eventos 	  \n")
    		.append("2 - Menu Regional 	  \n")
    		.append("3 - Menu Categoria   \n")
    		.append("4 - Menu Instituto   \n")
    		.append("5 - Logar 			  \n")
    		.append("6 - Sair 			  \n");
    	return tela.toString();
    }
    
    /**
     * Método que pergunta o login e a senha do usuário e retorna um vetor
     * com os dados que foram inseridos.
     * 
     * @return vetor com os dados do usuário, a primeira posição é o login,
     * a segunda é a senha.
     */
    public String[] realizarLogin() {
        exibaCabecalho();
        String[] dadosUsuario = new String[2];
        String usuario = new EntradaConsole().pergunteString("- Login: \n");
        String senha   = new EntradaConsole().pergunteString("- Senha: ");
        dadosUsuario[0] = usuario;
        dadosUsuario[1] = senha;
        return dadosUsuario;
    }
}
