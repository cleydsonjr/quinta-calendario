/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fernandohre.controller;
import java.util.Scanner;
/**
 *
 * @author aluno
 */

abstract class Controller {
    abstract void redireciona(int opcao);
    abstract void verificaData();
    /**
     * Faz a leitura de um número e retorna seu valor. 
     * 
     * @param none Não possui parâmetros para execução da leitura.
     * @return O valor da opcao que o usuário escolheu.
     */
    static public int ler() {
        Scanner ler = new Scanner(System.in);
        int v;
        v = ler.nextInt();
        return v;
    }
}
