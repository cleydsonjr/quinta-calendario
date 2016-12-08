package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.view.console.TelaUsuarioConsole;
import org.hibernate.SessionFactory;


public class UsuarioController {
    private TelaUsuarioConsole tela;
    private SessionFactory sessionFactory;

    public UsuarioController() {
        tela = new TelaUsuarioConsole(System.out);
        sessionFactory = Application.getInstance().getSessionFactory();
    }

    public void exibaOpcoes() {
        getTela().exibaOpcoes();
    }

    public TelaUsuarioConsole getTela() {
        return tela;
    }
}
