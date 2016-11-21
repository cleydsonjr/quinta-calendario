/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.fernandohre.view;

import com.github.fernandohre.controller.PaginaInicialController;



/**
 *
 * @author aluno
 */
public class PaginaInicial extends View {
    
    @Override
    public void tela() {
        System.out.println("=================================================");
        System.out.println("# CALENDÁRIO UFG - QUINTA na QUINTA             #");
        System.out.println("=================================================");
        System.out.println("                                 Usuário: Anônimo");
        System.out.println("Opções: ");
        System.out.println("-------------------------------------------------");
        System.out.println("\t1. Listar Eventos por data.");
        System.out.println("\t2. Buscar Eventos por conteúdo.");
        System.out.println("\t3. Realizar Login.");
        System.out.println("\n\n 0. Encerrar Calendário");
        PaginaInicialController controlador = new PaginaInicialController();
        controlador.redireciona(PaginaInicialController.ler());
    }
    
}
