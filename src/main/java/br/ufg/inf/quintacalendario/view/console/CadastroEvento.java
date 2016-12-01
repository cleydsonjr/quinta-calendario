/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.quintacalendario.view.console;

import java.io.PrintStream;

import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

/**
 *
 * @author fer
 */
public class CadastroEvento extends AbstractTelaCabecalho {
 
    public CadastroEvento(PrintStream output) {
            super(output);
    }

    public void index() {
        this.telaCadastro();
    }
    
    private void telaCadastro() {
        EntradaConsole evento = new EntradaConsole();
        //O método pergunteString, faz a pergunta e retorna a string escrita
        //pelo usuário
        evento.pergunteString("Insira o nome do Evento", true);
        //Retorna a resposta inteiro do usuário
        evento.pergunteInteiro("Insira o instituto que o "
        + "evento pertence");
        evento.pergunteString("Insira a data de ínicio do"
                + " evento", true);
        evento.pergunteString("Insira a data do término do "
                + "evento", true);
        evento.pergunteInteiro("Insira a regional do evento");
    }
    
    
}
