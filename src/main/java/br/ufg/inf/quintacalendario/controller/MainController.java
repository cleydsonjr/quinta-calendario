/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.quintacalendario.controller;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.CadastroEvento;
import br.ufg.inf.quintacalendario.view.console.TelaInicialConsole;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;
/**
 *
 * @author fer
 */
public class MainController {
    
    private void cabecalho() {
       TelaInicial telaInicial = new TelaInicialConsole(System.out);
       telaInicial.exibaCabecalho();
    }
    
    public void telaInicial() {
        this.cabecalho();
        TelaInicial opcoes = new TelaInicialConsole(System.out);
        opcoes.exibaOpcoes();
        this.redirecionaTela();
    }
    
    private int capturaOpcao() {
        EntradaConsole opcaoUsuario = new EntradaConsole();
        int opcao = opcaoUsuario.pergunteInteiro("Selecione uma das opcoes :");
        return opcao;
    }
    
    private void redirecionaTela() {
        switch (capturaOpcao()) {
            case 1:
                //Cadastrar um evento
                CadastroEvento evento = new CadastroEvento();
                evento.index();
                break;
            case 2:
                //Pesquisar Eventos por Data
                System.out.println("SUCESSO!");
                break;
            case 3:
                //Pesquisar Eventos por conteúdo
                System.out.println("SUCESSO!");
                break;
            case 0:
                //Sair da aplicação
                System.out.println("SUCESSO!");
                break;
            
        }
    }
}
