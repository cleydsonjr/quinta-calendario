package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.view.console.TelaInicialConsole;

public class TelaInicialController {

    public void redirect(int opcao) {
        switch (opcao) {
            case 1:
                new EventosController().exibaOpcoes();
                break;
            case 2:
                new RegionalController().exibaOpcoes();
                break;
            case 3:
                new CategoriaController().exibaOpcoes();
                break;
            case 4:
                new InstitutoController().exibaOpcoes();
                break;
            case 5:
                new UsuarioController().exibaOpcoes();
                break;
            case 6:
            	System.out.println("Opção em desenvolvimento");
            	new TelaInicialConsole(System.out).exibaOpcoes();
//                 TelaInicialConsole.mensagemSaida();
                break;
            default:
                break;
        }
    }

}
