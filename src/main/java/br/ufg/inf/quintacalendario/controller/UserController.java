package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.view.console.UserScreenConsole;
import org.hibernate.SessionFactory;

/**
 * Controller responsible for intercepting user entity operations, extend AbstractController class
 *
 * @author Hyago Souza
 * @see AbstractController
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

    /**
     * Returns screen console of user entity
     *
     * @return user screen console
     * @see UserScreenConsole
     */
    public UserScreenConsole getUserScreen() {
        return userScreen;
    }

    /**
     * Attribute a user screen console to entity
     *
     * @param userScreen user screen console
     * @see UserScreenConsole
     */
    public void setUserScreen(UserScreenConsole userScreen) {
        this.userScreen = userScreen;
    }

}
