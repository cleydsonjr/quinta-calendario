/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fernandohre.controller;

/**
 *
 * @author aluno
 */
public class PaginaInicialController extends Controller {

    @Override
    public void redireciona(int opcao) {
        switch (opcao) {
            case 1:
                //Aqui deve listar os eventos por data, em ordem crescente.
                System.out.println("Teste, selecionado 1");
                break;
            case 2:
                //Deve-se chamar um método que será responsável por buscar
                // eventos por conteúdo
                System.out.println("Teste, selecionado 2");
                break;
            case 3:
                //Chamar view para realização de login
                System.out.println("Teste, selecionado 3");
                break;
            case 0:
                //Encerrar o programa
                break;
            default:
                System.out.println("Você não selecionou nenhuma das opções.");
        } 
    }

    @Override
    void verificaData() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
