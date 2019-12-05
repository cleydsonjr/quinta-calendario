package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.view.console.TelaInicialConsole;

public class HomePageController {

    public void redirectScreenBy(Integer choice) {
        switch (choice) {
            case 1:
                new EventController().showHisOptions();
                break;
            case 2:
                new RegionalController().showHisOptions();
                break;
            case 3:
                new CategoryController().showHisOptions();
                break;
            case 4:
                new InstituteController().showHisOptions();
                break;
            case 5:
                new UserController().showHisOptions();
                break;
            case 6:
                TelaInicialConsole.mensagemSaida();
                break;
            default:
                break;
        }
    }

}
