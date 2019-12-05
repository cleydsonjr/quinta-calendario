package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.view.console.UserScreenConsole;
import org.hibernate.SessionFactory;


class UserController {

    private UserScreenConsole userScreen;
    private SessionFactory sessionFactory;

    UserController() {
        userScreen = new UserScreenConsole(System.out);
        sessionFactory = Application.getInstance().getSessionFactory();
    }

    void showHisOptions() {
        getUserScreen().showOptions();
    }

    private UserScreenConsole getUserScreen() {
        return userScreen;
    }

    public void setUserScreen(UserScreenConsole userScreen) {
        this.userScreen = userScreen;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
