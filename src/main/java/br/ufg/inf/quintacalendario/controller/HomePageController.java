package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.view.console.TelaInicialConsole;

/**
 * Controller responsible for intercepting home page entity operations
 *
 * @author Hyago Souza
 */
public class HomePageController {

    /**
     * Redirects the user according to the desired option
     *
     * @param choice user choice on screen
     */
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
                TelaInicialConsole.finishProgramExecution();
                break;
            default:
                break;
        }
    }

}
