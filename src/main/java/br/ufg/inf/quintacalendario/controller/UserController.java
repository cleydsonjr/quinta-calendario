package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.view.console.UserScreenConsole;
import org.hibernate.SessionFactory;

/**
 * Controller responsible for intercepting user entity operations
 *
 * @author Hyago Souza
 */
class UserController extends AbstractController {

    private UserScreenConsole userScreen;

    /**
     * Constructor's class
     */
    UserController() {
        super(Application.getInstance().getSessionFactory());
        userScreen = new UserScreenConsole(System.out);
    }

    /**
     * Show category options on screen
     */
    void showHisOptions() {
        getUserScreen().showOptions();
    }

    public UserScreenConsole getUserScreen() {
        return userScreen;
    }

    public void setUserScreen(UserScreenConsole userScreen) {
        this.userScreen = userScreen;
    }

}
