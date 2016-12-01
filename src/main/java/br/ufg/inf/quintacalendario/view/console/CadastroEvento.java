/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

/**
 *
 * @author fer
 */
public class CadastroEvento extends AbstractTelaCabecalho {
    private String dataInicial;
    private String dataFinal;
    private String instituto;
    private String regional;
    private String nomeEvento;
    public void index() {
        this.exibaCabecalho();
    }
    
    private void telaCadastro() {
        EntradaConsole evento = new EntradaConsole();
        this.nomeEvento = evento.pergunteString("Insira o nome do Evento", true);
        this.instituto  = evento.pergunteString("Insira o instituto que o "
        + "evento pertence", true);
        this.dataInicial = evento.pergunteString("Insira a data de ínicio do"
                + " evento", true);
        this.dataFinal = evento.pergunteString("Insira a data do término do "
                + "evento", true);
        this.regional = evento.pergunteString("Insira a regional do evento"
        + "", true);
    }
}
