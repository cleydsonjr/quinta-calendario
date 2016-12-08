
package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;
import java.io.PrintStream;
import java.util.List;


public class TelaUsuarioConsole extends AbstractTelaCabecalho implements TelaInicial{
    
    private EntradaConsole entradaConsole;
    public TelaUsuarioConsole(PrintStream output) {
        super(output);
        setEntradaConsole(new EntradaConsole());
    }
    @Override
    public void exibaOpcoes() {
        exibaCabecalho();
        desenhaOpcoes();
        Integer opcao = getEntradaConsole().pergunteInteiro(desenhaOpcoes().toString());
        redirect(opcao);
    }
    
    private void redirect(Integer opcao) {
        switch (opcao) {
            case 1:
                //Listar usuários cadastrados    
                
                break;
            case 2:
                //Cadastrar um novo usuário   
                break;
            case 3:
                //Remover usuário    
                break;
            case 4:
                //Alterar dados do usuário    
                    
                break;
            default:
                    
                break;
        }
    }

    @Override
    public int pergunteOpcao() {
        return 0;
    }
    
    public void setEntradaConsole(EntradaConsole entradaConsole) {
        this.entradaConsole = entradaConsole;
    }
    
    public EntradaConsole getEntradaConsole() {
        return entradaConsole;
    }
    
    public String desenhaOpcoes() {
        StringBuilder tela = new StringBuilder();
        tela.append("1 - Listar usuários cadastrados \n")
                .append("2 - Cadastar um novo usuário \n")
                .append("3 - Remover usuário \n")
                .append("4 - Alterar dados de usuário");
        return tela.toString();
    }
    
}
