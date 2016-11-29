/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.quintacalendario.controller;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.TelaInicialConsole;
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
    }
    
}
